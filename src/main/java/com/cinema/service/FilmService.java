package com.cinema.service;

import com.cinema.entity.FilmDTO;
import com.cinema.model.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FilmService {
    List<Film> findAllByGenres(Long genreId, int page, int size, String sortBy, List<Long> exludedFilmIds);

    Film findById(Long filmId);

    Page<Film> findAllByGenres(Genre genre, int page, int size, String sortBy);

    Page<Film> findFavoriteFilmsByUserId(Long userId, int page, int size, String sortBy);

    Page<Film> search(String searchText, int page, int size, String sortBy);

    Like createLike(Like like);

    void deleteLike(Long id);

    Like findByLikeId(Long id);

    Dislike createDislike(Dislike dislike);

    void deleteDislike(Long id);

    Dislike findByDislikeId(Long id);

    Comment createComment(Comment comment);

    void deleteComment(Long id);

    Comment findByCommentId(Long id);

}
