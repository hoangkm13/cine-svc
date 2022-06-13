package com.cinema.controller.request;

import com.cinema.entities.Genre;
import lombok.*;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategorizedFilmsDTO {
    private Genre genre;
    List<FilmDTO> films;
}
