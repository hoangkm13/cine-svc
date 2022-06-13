package com.cinema.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeDTO {
    private Long id;

    @NotNull
    private Long userId;

    @NotNull
    private Long filmId;


}
