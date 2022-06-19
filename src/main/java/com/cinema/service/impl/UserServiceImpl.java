package com.cinema.service.impl;

import com.cinema.constants.ErrorCode;
import com.cinema.controller.request.UpdateUserDTO;
import com.cinema.controller.request.UserDTO;
import com.cinema.entities.User;
import com.cinema.enums.Role;
import com.cinema.exception.CustomException;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
        user.setFullName(userDTO.getFullName());
        user.setGender(userDTO.getGender());
        user.setBirthOfDate(userDTO.getBirthOfDate());
        user.setMobile(userDTO.getMobile());
        user.setRole(Role.USER.getValue());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
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

    @Override
    public User preCheckUpdateUserInfo(UpdateUserDTO updateUserDTO, Long currentUserId, Long userId) throws CustomException {
        var existedUser = this.findById(currentUserId);

        if (!Objects.equals(existedUser.getRole(), Role.ADMIN.getValue())) {
            if (!Objects.equals(userId, existedUser.getId())) {
                throw new CustomException(ErrorCode.AUTHORIZATION_ERROR);
            }
        }
        return this.updateUser123(updateUserDTO, existedUser);
    }

    private User updateUser123(UpdateUserDTO updateUserDTO, User existedUser) {

        existedUser.setFullName(updateUserDTO.getFullName() != null ? updateUserDTO.getFullName() : existedUser.getFullName());
        existedUser.setGender(updateUserDTO.getGender() != null ? updateUserDTO.getGender() : existedUser.getGender());
        existedUser.setBirthOfDate(updateUserDTO.getBirthOfDate()!= null ? updateUserDTO.getBirthOfDate() : existedUser.getBirthOfDate());
        existedUser.setMobile(updateUserDTO.getMobile() != null ? updateUserDTO.getMobile() : existedUser.getMobile());
        existedUser.setEmail(updateUserDTO.getEmail() != null ? updateUserDTO.getEmail() : existedUser.getEmail());
        existedUser.setFirstName(updateUserDTO.getFirstName() != null ? updateUserDTO.getFirstName() : existedUser.getFirstName());
        existedUser.setLastName(updateUserDTO.getLastName() != null ? updateUserDTO.getLastName() : existedUser.getLastName());
        existedUser.setEmail(updateUserDTO.getEmail() != null ? updateUserDTO.getEmail() : existedUser.getEmail());

        this.userRepository.saveAndFlush(existedUser);

        return existedUser;
    }
}
