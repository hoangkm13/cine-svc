package com.cinema.controller;

import com.cinema.controller.request.UpdateUserDTO;
import com.cinema.exception.CustomException;
import com.cinema.controller.request.LoginRequestDTO;
import com.cinema.controller.request.LoginResponseDTO;
import com.cinema.controller.request.UserDTO;
import com.cinema.entities.User;
import com.cinema.model.ApiResponse;
import com.cinema.service.UserService;
import com.cinema.util.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class UserController {
    private final AuthenticationManager authenticationManager;
    private final TokenUtils tokenUtils;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @PostMapping(value = "/login", produces = "application/json")
    public ApiResponse<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequestDTO.getUsername(),
                loginRequestDTO.getPassword()
        ));
        String token = tokenUtils.generateToken(authentication);
        LoginResponseDTO userToken = new LoginResponseDTO(token);
        return ApiResponse.successWithResult(userToken);
    }

    @GetMapping(value = "/user", produces = "application/json")
    public ApiResponse<UserDTO> getCurrentUser() throws CustomException {
        User authentication = (User) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        User user = userService.findByUsername(authentication.getUsername());
        return ApiResponse.successWithResult(modelMapper.map(user, UserDTO.class));
    }

    @PostMapping(value = "/register", produces = "application/json")
    public ApiResponse<UserDTO> register(@Valid @RequestBody UserDTO userDTO) throws CustomException {
        var user = userService.createUser(userDTO);
        return ApiResponse.successWithResult(modelMapper.map(user, UserDTO.class));
    }

    @PutMapping(value = "{userId}", produces = "application/json")
    public ApiResponse<UserDTO> updateUser(@PathVariable Long userId, @Valid @RequestBody UpdateUserDTO userDTO) throws CustomException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = userService.preCheckUpdateUserInfo(userDTO, Long.valueOf(authentication.getPrincipal().toString()), userId);
        return ApiResponse.successWithResult(modelMapper.map(user, UserDTO.class));
    }
}
