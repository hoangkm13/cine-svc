package com.cinema.controller;

import com.cinema.entity.LoginRequestDTO;
import com.cinema.entity.LoginResponseDTO;
import com.cinema.entity.UserDTO;
import com.cinema.exception.BadRequestException;
import com.cinema.exception.HttpException;
import com.cinema.exception.InternalServerException;
import com.cinema.model.User;
import com.cinema.service.UserService;
import com.cinema.util.GeneralUtils;
import com.cinema.util.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {
    private final AuthenticationManager authenticationManager;
    private final TokenUtils tokenUtils;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @PostMapping({"/login"})
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequestDTO.getUsername(),
                    loginRequestDTO.getPassword()
            ));
            String token = tokenUtils.generateToken(authentication);
            LoginResponseDTO userToken = new LoginResponseDTO(token);
            return ResponseEntity.ok(userToken);
        } catch (BadCredentialsException ex) {
            throw new BadRequestException("Incorrect username or password");
        } catch (AuthenticationException ex) {
            throw new BadRequestException(ex.getMessage());
        } catch (HttpException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalServerException(ex.getMessage());
        }
    }

    @GetMapping("/user")
    public ResponseEntity<UserDTO> getCurrentUser(){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.findByUsername(authentication.getName());
            return ResponseEntity.ok(modelMapper.map(user, UserDTO.class));
        } catch (HttpException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalServerException(ex.getMessage());
        }
    }
    
    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody UserDTO userDTO, Errors errors){
        if (errors.hasErrors()) {
            String errorMessage = GeneralUtils.processValidationError(errors);
            throw new BadRequestException(errorMessage);
        }
        try {
            var user = userService.createUser(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(user, UserDTO.class));
        } catch (HttpException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalServerException(ex.getMessage());
        }
    }
}
