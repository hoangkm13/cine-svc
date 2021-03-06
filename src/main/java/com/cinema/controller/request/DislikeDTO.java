package com.cinema.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DislikeDTO {
    private Long id;

    @NotNull
    private Long userId;

    @NotNull
    private Long filmId;


}
