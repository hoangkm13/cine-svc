package com.cinema.service.impl;

import com.cinema.CustomException;
import com.cinema.constants.ErrorCode;
import com.cinema.controller.request.UserDTO;
import com.cinema.entities.User;
import com.cinema.enums.Role;
import com.cinema.repository.UserRepository;
import com.cinema.service.UserService;
import com.cinema.util.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthUtils authUtils;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        User user = optionalUser.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
        authorities.add(authority);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public User findByUsername(String username) throws CustomException {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new CustomException(ErrorCode.USER_NOT_EXIST);
        }
        return optionalUser.get();
    }

    @Override
    public User createUser(UserDTO userDTO) throws CustomException {
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new CustomException(ErrorCode.USERNAME_EXIST);
        }
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new CustomException(ErrorCode.EMAIL_EXIST);
        }

        User user = new User();
        user.setId(null);
        user.setRole(Role.USER.getValue());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void checkPermission(Long userId) throws CustomException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = findByUsername(authentication.getName());
        if (!userId.equals(currentUser.getId()) && !authUtils.isAdmin()) {
            throw new CustomException(ErrorCode.AUTHORIZATION_ERROR);
        }
    }

    @Override
    public User findById(Long userId) throws CustomException {
        var user = this.userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new CustomException(ErrorCode.USER_NOT_EXIST);
        }

        return user.get();
    }
}
