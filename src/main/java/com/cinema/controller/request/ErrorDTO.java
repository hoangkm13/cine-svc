package com.cinema.entity;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDTO {
    @NotNull
    private String errorMessage;
}
