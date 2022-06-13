package com.cinema.service.impl;

import com.cinema.entity.FilmDTO;
import com.cinema.exception.NotFoundException;
import com.cinema.model.*;
import com.cinema.repository.CommentRepository;
import com.cinema.repository.DislikeRepository;
import com.cinema.repository.FilmRepository;
import com.cinema.repository.LikeRepository;
import com.cinema.service.FilmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class FilmServiceImpl implements FilmService {
    private final FilmRepository filmRepository;
    private final ModelMapper modelMapper;
    private final LikeRepository likeRepository;
    private final DislikeRepository dislikeRepository;
    private final CommentRepository commentRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Film> findAllByGenres(Long genreId, int page, int size, String sortBy, List<Long> exludedFilmIds) {
        int offset = page * size;
        return filmRepository.findAllByGenres(genreId, offset, size, sortBy, exludedFilmIds);
    }

    @Override
    public Film findById(Long filmId) {
        Optional<Film> optionalFilm = filmRepository.findById(filmId);
        if (optionalFilm.isEmpty()) {
            throw new NotFoundException("Could not find film with id: " + filmId);
        }
        return optionalFilm.get();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Page<Film> findAllByGenres(Genre genre, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return filmRepository.findAllByGenres(genre, pageable);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
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
    public void deleteLike(Long id) {
        var entity = this.findByLikeId(id);
        this.likeRepository.deleteById(entity.getId());
    }

    @Override
    public Like findByLikeId(Long id) {
        var entity = this.likeRepository.findById(id);
        if (entity.isEmpty()){
            throw new NotFoundException("Khong tim thay Like");
        }
        return entity.get();
    }

    @Override
    public Dislike createDislike(Dislike dislike) {
        return this.dislikeRepository.save(dislike);

    }

    @Override
    public void deleteDislike(Long id) {
        var entity = this.findByDislikeId(id);
        this.dislikeRepository.deleteById(entity.getId());
    }

    @Override
    public Dislike findByDislikeId(Long id) {
        var entity = this.dislikeRepository.findById(id);
        if (entity.isEmpty()){
            throw new NotFoundException("Khong tim thay Dislike");
        }
        return entity.get();
    }

    @Override
    public Comment createComment(Comment comment) {
        return this.commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long id) {
        var entity = this.findByCommentId(id);
        this.commentRepository.deleteById(entity.getId());
    }

    @Override
    public Comment findByCommentId(Long id) {
        var entity = this.commentRepository.findById(id);
        if (entity.isEmpty()){
            throw new NotFoundException("Khong tim thay Comment");
        }
        return entity.get();
    }

}
