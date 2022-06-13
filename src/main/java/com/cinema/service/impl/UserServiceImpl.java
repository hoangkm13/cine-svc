package com.cinema.service.impl;

import com.cinema.entity.UserDTO;
import com.cinema.enums.Role;
import com.cinema.exception.BadRequestException;
import com.cinema.exception.NotFoundException;
import com.cinema.exception.UnauthorizedException;
import com.cinema.model.User;
import com.cinema.repository.UserRepository;
import com.cinema.service.UserService;
import com.cinema.util.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
    @Transactional(rollbackFor = Exception.class)
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
    @Transactional(rollbackFor = Exception.class)
    public User findByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new NotFoundException("Could not find user with username: " + username);
        }
        return optionalUser.get();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User createUser(UserDTO userDTO) {
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new BadRequestException("This username has been taken");
        }
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new BadRequestException("This email already exists");
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
    @Transactional(rollbackFor = Exception.class)
    public void checkPermission(Long userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = findByUsername(authentication.getName());
        if (!userId.equals(currentUser.getId()) && !authUtils.isAdmin()) {
            throw new UnauthorizedException("You don't have enough permison");
        }
    }

    @Override
    public User findById(Long userId) {
        var user = this.userRepository.findById(userId);
        if (user.isEmpty()){
            throw new NotFoundException("Không tìm thấy user");
        }

        return user.get();
    }
}
