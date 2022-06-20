package com.cinema.service.impl;

import com.cinema.constants.ErrorCode;
import com.cinema.controller.request.CommentDTO;
import com.cinema.entities.*;
import com.cinema.exception.CustomException;
import com.cinema.repository.CommentRepository;
import com.cinema.repository.DislikeRepository;
import com.cinema.repository.FilmRepository;
import com.cinema.repository.LikeRepository;
import com.cinema.service.FilmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Log4j2
public class FilmServiceImpl implements FilmService {
    private final FilmRepository filmRepository;
    private final LikeRepository likeRepository;
    private final DislikeRepository dislikeRepository;
    private final CommentRepository commentRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<Film> findAllByGenres(Long genreId, int page, int size, String sortBy, List<Long> exludedFilmIds) {
        int offset = page * size;
        return filmRepository.findAllByGenres(genreId, offset, size, sortBy, exludedFilmIds);
    }

    @Override
    public Film findById(Long filmId) throws CustomException {
        Optional<Film> optionalFilm = filmRepository.findById(filmId);
        if (optionalFilm.isEmpty()) {
            throw new CustomException(ErrorCode.FILM_NOT_EXIST);
        }
        return optionalFilm.get();
    }

    @Override
    public Page<Film> findAllByGenres(Genre genre, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return filmRepository.findAllByGenres(genre, pageable);
    }

    @Override
    public Page<Film> findFavoriteFilmsByUserId(Long userId, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size); // order will not work in repo
        return filmRepository.findFavoriteFilmsByUserId(userId, pageable);
    }

    @Override
    public Page<Film> search(String searchText, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size); // order will not work in repo
        return this.filmRepository.findAllBySearchText(searchText, pageable);
    }

    @Override
    public Like createLike(Like like) {
        return this.likeRepository.save(like);
    }

    @Override
    public Like deleteLike(Long id) throws CustomException {
        var entity = this.findByLikeId(id);
        this.likeRepository.deleteById(entity.getId());

        return entity;
    }

    @Override
    public Like findByLikeId(Long id) throws CustomException {
        var entity = this.likeRepository.findById(id);
        if (entity.isEmpty()) {
            throw new CustomException(ErrorCode.LIKE_NOT_EXIST);
        }
        return entity.get();
    }

    @Override
    public Dislike createDislike(Dislike dislike) {
        return this.dislikeRepository.save(dislike);

    }

    @Override
    public Dislike deleteDislike(Long id) throws CustomException {
        var entity = this.findByDislikeId(id);
        this.dislikeRepository.deleteById(entity.getId());

        return entity;
    }

    @Override
    public Dislike findByDislikeId(Long id) throws CustomException {
        var entity = this.dislikeRepository.findById(id);
        if (entity.isEmpty()) {
            throw new CustomException(ErrorCode.DISLIKE_NOT_EXIST);
        }
        return entity.get();
    }

    @Override
    public Comment createComment(Comment comment) {
        comment.setCreatedAt(LocalDateTime.now().toString());
        return this.commentRepository.save(comment);
    }

    @Override
    public Comment deleteComment(Long id) throws CustomException {
        var entity = this.findByCommentId(id);
        this.commentRepository.deleteById(entity.getId());

        return entity;
    }

    @Override
    public Comment findByCommentId(Long id) throws CustomException {
        var entity = this.commentRepository.findById(id);
        if (entity.isEmpty()) {
            throw new CustomException(ErrorCode.COMMENT_NOT_EXIST);
        }
        return entity.get();
    }

    @Override
    public Page<CommentDTO> getCommentPagination(Long filmId, int page, int size, String sortBy, String orderBy) throws CustomException {
        if (!Objects.equals(orderBy, "ASC") && !Objects.equals(orderBy, "DSC")) {
            throw new CustomException(ErrorCode.INVALID_ORDER_BY_METHOD);
        }
        var film = this.findById(filmId);

        switch (orderBy) {
            case "ASC":
                film.getComments().sort(Comparator.comparing(Comment::getCreatedAt));
                break;
            case "DSC":
                film.getComments().sort(Comparator.comparing(Comment::getCreatedAt).reversed());
                break;
            default:
                break;
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        List<CommentDTO> newCommentFormat = new ArrayList<>();
        for (Comment comment: film.getComments()) {
            CommentDTO commentDTO = modelMapper.map(comment, CommentDTO.class);
            commentDTO.setUsername(comment.getUser().getUsername());
            newCommentFormat.add(commentDTO);
        }

        return new PageImpl<>(newCommentFormat, pageable, film.getComments().size());
    }

}
