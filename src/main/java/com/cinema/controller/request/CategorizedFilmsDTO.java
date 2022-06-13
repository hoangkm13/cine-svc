package com.cinema.entity;

import com.cinema.model.Film;
import com.cinema.model.Genre;
import lombok.*;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategorizedFilmsDTO {
    private Genre genre;
    List<FilmDTO> films;
}
