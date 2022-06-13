package com.cinema.controller;

import com.cinema.exception.HttpException;
import com.cinema.exception.InternalServerException;
import com.cinema.model.Genre;
import com.cinema.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/genres")
public class GenreController {
    private final GenreService genreService;

    @GetMapping
    public ResponseEntity<List<Genre>> getAllGenres() {
        try {
            return ResponseEntity.ok(genreService.findAll());
        } catch (HttpException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalServerException(ex.getMessage());
        }
    }
}
