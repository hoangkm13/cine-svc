package com.cinema.service;

import com.cinema.controller.request.UpdateUserDTO;
import com.cinema.exception.CustomException;
import com.cinema.controller.request.UserDTO;
import com.cinema.entities.User;

public interface UserService {
    User findByUsername(String username) throws CustomException;

    User createUser(UserDTO userDTO) throws CustomException;

    void checkPermission(Long userId) throws CustomException;
    User  findById(Long userId) throws CustomException;
    User preCheckUpdateUserInfo(UpdateUserDTO updateUserDTO, Long currentUserId, Long userId) throws CustomException;
}
