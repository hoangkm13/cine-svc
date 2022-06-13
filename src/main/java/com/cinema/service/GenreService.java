package com.cinema.service;

import com.cinema.model.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> findAll();
    Genre findById(Long id);
    Genre findByName(String name);
}
