package com.cinema.controller;

import com.cinema.config.AppConfig;
import com.cinema.constants.ErrorCode;
import com.cinema.controller.request.*;
import com.cinema.entities.User;
import com.cinema.exception.CustomException;
import com.cinema.model.ApiResponse;
import com.cinema.service.UserService;
import com.cinema.util.TokenUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
@Log4j2
@RequestMapping("/api/auth")
public class UserController {
    private final AuthenticationManager authenticationManager;
    private final TokenUtils tokenUtils;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final AppConfig appConfig;

    @PostMapping(value = "/login", produces = "application/json")
    public ApiResponse<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        try {
            int tps = appConfig.checkTps(appConfig.userTpsName);

            if (appConfig.maxTpsUser >= 0 && tps >= appConfig.maxTpsUser) {
                log.warn("Over tps reach ---> reject request: tps = " + tps);
                return ApiResponse.failureWithCode(ErrorCode.OVER_REACH_TPS.getCode(), ErrorCode.OVER_REACH_TPS.getMessage());
            }

            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword()));
            String token = tokenUtils.generateToken(authentication);
            LoginResponseDTO userToken = new LoginResponseDTO(token);
            return ApiResponse.successWithResult(userToken);
        } finally {
            appConfig.decreaseTPS(appConfig.userTpsName);
        }
    }

    @GetMapping(value = "/user", produces = "application/json")
    public ApiResponse<UserDTO> getCurrentUser() throws CustomException {
        try {
            int tps = appConfig.checkTps(appConfig.userTpsName);

            if (appConfig.maxTpsUser >= 0 && tps >= appConfig.maxTpsUser) {
                log.warn("Over tps reach ---> reject request: tps = " + tps);
                return ApiResponse.failureWithCode(ErrorCode.OVER_REACH_TPS.getCode(), ErrorCode.OVER_REACH_TPS.getMessage());
            }

            User authentication = (User) SecurityContextHolder.getContext().getAuthentication().getCredentials();
            User user = userService.findByUsername(authentication.getUsername());

            return ApiResponse.successWithResult(modelMapper.map(user, UserDTO.class));
        } finally {
            appConfig.decreaseTPS(appConfig.userTpsName);
        }
    }

    @PostMapping(value = "/register", produces = "application/json")
    public ApiResponse<UserDTO> register(@Valid @RequestBody UserDTO userDTO) throws CustomException {
        try {
            int tps = appConfig.checkTps(appConfig.userTpsName);

            if (appConfig.maxTpsUser >= 0 && tps >= appConfig.maxTpsUser) {
                log.warn("Over tps reach ---> reject request: tps = " + tps);
                return ApiResponse.failureWithCode(ErrorCode.OVER_REACH_TPS.getCode(), ErrorCode.OVER_REACH_TPS.getMessage());
            }

            var user = userService.createUser(userDTO);
            return ApiResponse.successWithResult(modelMapper.map(user, UserDTO.class));
        } finally {
            appConfig.decreaseTPS(appConfig.userTpsName);
        }
    }

    @PutMapping(value = "{userId}", produces = "application/json")
    public ApiResponse<UserDTO> updateUser(@PathVariable Long userId, @Valid @RequestBody UpdateUserDTO userDTO) throws CustomException {
        try {
            int tps = appConfig.checkTps(appConfig.userTpsName);

            if (appConfig.maxTpsUser >= 0 && tps >= appConfig.maxTpsUser) {
                log.warn("Over tps reach ---> reject request: tps = " + tps);
                return ApiResponse.failureWithCode(ErrorCode.OVER_REACH_TPS.getCode(), ErrorCode.OVER_REACH_TPS.getMessage());
            }

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            var user = userService.preCheckUpdateUserInfo(userDTO, Long.valueOf(authentication.getPrincipal().toString()), userId);
            return ApiResponse.successWithResult(modelMapper.map(user, UserDTO.class));
        } finally {
            appConfig.decreaseTPS(appConfig.userTpsName);
        }
    }

    @PostMapping(value = "changePassword/{userId}", produces = "application/json")
    public ApiResponse<UserDTO> resetPassword(@PathVariable Long userId, @Valid @RequestBody ResetPasswordDTO resetPasswordDTO) throws CustomException {
        try {
            int tps = appConfig.checkTps(appConfig.userTpsName);

            if (appConfig.maxTpsUser >= 0 && tps >= appConfig.maxTpsUser) {
                log.warn("Over tps reach ---> reject request: tps = " + tps);
                return ApiResponse.failureWithCode(ErrorCode.OVER_REACH_TPS.getCode(), ErrorCode.OVER_REACH_TPS.getMessage());
            }

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            var user = userService.resetPassword(resetPasswordDTO, Long.valueOf(authentication.getPrincipal().toString()), userId);
            return ApiResponse.successWithResult(modelMapper.map(user, UserDTO.class));
        } finally {
            appConfig.decreaseTPS(appConfig.userTpsName);
        }
    }
}
