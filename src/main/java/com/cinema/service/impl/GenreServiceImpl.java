package com.cinema.service.impl;

import com.cinema.CustomException;
import com.cinema.constants.ErrorCode;
import com.cinema.entities.Genre;
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
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public Genre findById(Long id) throws CustomException {
        Optional<Genre> optionalGenre = genreRepository.findById(id);
        if (optionalGenre.isEmpty()) {
            throw new CustomException(ErrorCode.GENRE_NOT_EXIST);
        }
        return optionalGenre.get();
    }

    @Override
    public Genre findByName(String name) throws CustomException {
        Optional<Genre> optionalGenre = genreRepository.findByName(name);
        if (optionalGenre.isEmpty()) {
            throw new CustomException(ErrorCode.GENRE_NOT_EXIST);
        }
        return optionalGenre.get();
    }
}
