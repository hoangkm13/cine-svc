package com.cinema.controller;

import com.cinema.entities.Genre;
import com.cinema.model.ApiResponse;
import com.cinema.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/genres")
public class GenreController {
    private final GenreService genreService;

    @GetMapping(value = "", produces = "application/json")
    public ApiResponse<List<Genre>> getAllGenres() {
        return ApiResponse.successWithResult(genreService.findAll());
    }
}
