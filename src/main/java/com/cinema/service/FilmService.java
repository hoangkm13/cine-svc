package com.cinema.service;

import com.cinema.CustomException;
import com.cinema.entities.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FilmService {
    List<Film> findAllByGenres(Long genreId, int page, int size, String sortBy, List<Long> exludedFilmIds);

    Film findById(Long filmId) throws CustomException;

    Page<Film> findAllByGenres(Genre genre, int page, int size, String sortBy);

    Page<Film> findFavoriteFilmsByUserId(Long userId, int page, int size, String sortBy);

    Page<Film> search(String searchText, int page, int size, String sortBy);

    Like createLike(Like like);

    Like deleteLike(Long id) throws CustomException;

    Like findByLikeId(Long id) throws CustomException;

    Dislike createDislike(Dislike dislike);

    Dislike deleteDislike(Long id) throws CustomException;

    Dislike findByDislikeId(Long id) throws CustomException;

    Comment createComment(Comment comment);

    Comment deleteComment(Long id) throws CustomException;

    Comment findByCommentId(Long id) throws CustomException;

}
