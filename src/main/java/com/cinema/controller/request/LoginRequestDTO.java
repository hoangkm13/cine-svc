package com.cinema.controller.request;


import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {
    @NotNull
    private String username;

    @NotNull
    private String password;
}
