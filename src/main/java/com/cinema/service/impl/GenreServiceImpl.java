package com.cinema.service.impl;

import com.cinema.exception.NotFoundException;
import com.cinema.model.Genre;
import com.cinema.repository.GenreRepository;
import com.cinema.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Genre findById(Long id) {
        Optional<Genre> optionalGenre = genreRepository.findById(id);
        if (optionalGenre.isEmpty()) {
            throw new NotFoundException("Could not find genre with id: " + id);
        }
        return optionalGenre.get();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Genre findByName(String name) {
        Optional<Genre> optionalGenre = genreRepository.findByName(name);
        if (optionalGenre.isEmpty()) {
            throw new NotFoundException("Could not find genre with name: " + name);
        }
        return optionalGenre.get();
    }
}
