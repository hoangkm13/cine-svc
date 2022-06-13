package com.cinema.controller.request;


import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO {
    @NotNull
    private String token;
}
