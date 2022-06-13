package com.cinema.entity;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteDTO {
    private Long id;

    @NotNull
    private Long filmId;

    @NotNull
    private Long userId;
}
