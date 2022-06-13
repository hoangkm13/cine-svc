package com.cinema.service;

import com.cinema.entity.UserDTO;
import com.cinema.model.User;

public interface UserService {
    User findByUsername(String username);

    User createUser(UserDTO userDTO);

    void checkPermission(Long userId);
    User  findById(Long userId);
}
