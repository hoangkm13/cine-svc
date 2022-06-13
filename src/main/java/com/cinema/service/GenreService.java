package com.cinema.service;

import com.cinema.exception.CustomException;
import com.cinema.entities.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> findAll();
    Genre findById(Long id) throws CustomException;
    Genre findByName(String name) throws CustomException;
}
