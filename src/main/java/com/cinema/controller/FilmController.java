package com.cinema.controller;

import com.cinema.config.AppConfig;
import com.cinema.constants.ErrorCode;
import com.cinema.controller.request.*;
import com.cinema.controller.response.CommentPaginationResponse;
import com.cinema.entities.*;
import com.cinema.exception.CustomException;
import com.cinema.model.ApiResponse;
import com.cinema.service.FilmService;
import com.cinema.service.GenreService;
import com.cinema.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@Log4j2
@RequestMapping("/api/films")
public class FilmController {
    private final FilmService filmService;
    private final GenreService genreService;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final AppConfig appConfig;

    @SecurityRequirements
    @GetMapping(value = "/browse", produces = "application/json")
    public ApiResponse<List<CategorizedFilmsDTO>> getBrowseData(@RequestParam int size) {
        try {
            int tps = appConfig.checkTps(appConfig.filmTpsName);

            if (appConfig.maxTpsFilm >= 0 && tps >= appConfig.maxTpsFilm) {
                log.warn("Over tps reach ---> reject request: tps = " + tps);
                return ApiResponse.failureWithCode(ErrorCode.OVER_REACH_TPS.getCode(), ErrorCode.OVER_REACH_TPS.getMessage());
            }

            List<CategorizedFilmsDTO> categorizedFilmsDTOList = new ArrayList<>();
            List<Film> films = new ArrayList<>();
            List<Genre> genres = genreService.findAll();
            genres.forEach(genre -> {
                List<Long> excludedFilmsIds = new ArrayList<>();
                excludedFilmsIds.add(0L); // SQl operator IN will not work with empty array
                excludedFilmsIds.addAll(films.stream().map(Film::getId).collect(Collectors.toList()));
                List<Film> list = filmService.findAllByGenres(genre.getId(), 0, size, "created_at", excludedFilmsIds);
                films.addAll(list);
                List<FilmDTO> filmDTOList = list.stream().map(film -> modelMapper.map(film, FilmDTO.class)).collect(Collectors.toList());

                categorizedFilmsDTOList.add(new CategorizedFilmsDTO(genre, filmDTOList));
            });
            return ApiResponse.successWithResult(categorizedFilmsDTOList);
        } finally {
            appConfig.decreaseTPS(appConfig.filmTpsName);
        }
    }

    @GetMapping(value = "/search", produces = "application/json")
    public ApiResponse<Page<Film>> getSearchData(@RequestParam String searchText, @RequestParam int page, @RequestParam int size, @RequestParam String sortBy) {
        try {
            int tps = appConfig.checkTps(appConfig.filmTpsName);

            if (appConfig.maxTpsFilm >= 0 && tps >= appConfig.maxTpsFilm) {
                log.warn("Over tps reach ---> reject request: tps = " + tps);
                return ApiResponse.failureWithCode(ErrorCode.OVER_REACH_TPS.getCode(), ErrorCode.OVER_REACH_TPS.getMessage());
            }

            Page<Film> films = filmService.search(searchText, page - 1, size, sortBy);
            return ApiResponse.successWithResult(films);
        } finally {
            appConfig.decreaseTPS(appConfig.filmTpsName);
        }
    }

    @GetMapping(value = "/{genre}", produces = "application/json")
    public ApiResponse<Page<Film>> getFilmsByGenre(@PathVariable("genre") String genreName, @RequestParam int page, @RequestParam int size, @RequestParam String sortBy) throws CustomException {
        try {
            int tps = appConfig.checkTps(appConfig.filmTpsName);

            if (appConfig.maxTpsFilm >= 0 && tps >= appConfig.maxTpsFilm) {
                log.warn("Over tps reach ---> reject request: tps = " + tps);
                return ApiResponse.failureWithCode(ErrorCode.OVER_REACH_TPS.getCode(), ErrorCode.OVER_REACH_TPS.getMessage());
            }

            Genre genre = genreService.findByName(genreName);
            return ApiResponse.successWithResult(filmService.findAllByGenres(genre, page - 1, size, sortBy));
        } finally {
            appConfig.decreaseTPS(appConfig.filmTpsName);
        }
    }

    @GetMapping(value = "/favorites", produces = "application/json")
    public ApiResponse<Page<Film>> getFavoriteFilms(@RequestParam int page, @RequestParam int size, @RequestParam String sortBy) throws CustomException {
        try {
            int tps = appConfig.checkTps(appConfig.filmTpsName);

            if (appConfig.maxTpsFilm >= 0 && tps >= appConfig.maxTpsFilm) {
                log.warn("Over tps reach ---> reject request: tps = " + tps);
                return ApiResponse.failureWithCode(ErrorCode.OVER_REACH_TPS.getCode(), ErrorCode.OVER_REACH_TPS.getMessage());
            }

            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userService.findByUsername(username);
            return ApiResponse.successWithResult(filmService.findFavoriteFilmsByUserId(user.getId(), page - 1, size, sortBy));

        } finally {
            appConfig.decreaseTPS(appConfig.filmTpsName);
        }
    }

    @GetMapping(value = "/singleFilm/{filmId}", produces = "application/json")
    public ApiResponse<FilmDTO> getFilmById(@PathVariable("filmId") Long filmId) throws CustomException {
        try {
            int tps = appConfig.checkTps(appConfig.filmTpsName);

            if (appConfig.maxTpsFilm >= 0 && tps >= appConfig.maxTpsFilm) {
                log.warn("Over tps reach ---> reject request: tps = " + tps);
                return ApiResponse.failureWithCode(ErrorCode.OVER_REACH_TPS.getCode(), ErrorCode.OVER_REACH_TPS.getMessage());
            }


            Film film = this.filmService.findById(filmId);
            FilmDTO filmDTO = modelMapper.map(film, FilmDTO.class);
            if (!film.getComments().isEmpty()) {
                for (CommentDTO comment : filmDTO.getComments()) {
                    comment.setUsername(film.getComments().get(0).getUser().getUsername());
                }
            }

            return ApiResponse.successWithResult(filmDTO);
        } finally {
            appConfig.decreaseTPS(appConfig.filmTpsName);
        }
    }

    @PostMapping(value = "/like", produces = "application/json")
    public ApiResponse<LikeDTO> createLike(@RequestBody LikeDTO likeDTO) {

        Like like1 = this.filmService.createLike(modelMapper.map(likeDTO, Like.class));

        return ApiResponse.successWithResult(modelMapper.map(like1, LikeDTO.class));

    }

    @PostMapping(value = "/dislike", produces = "application/json")
    public ApiResponse<DislikeDTO> createDislike(@RequestBody DislikeDTO dislikeDTO) {

        Dislike dislike1 = this.filmService.createDislike(modelMapper.map(dislikeDTO, Dislike.class));
        return ApiResponse.successWithResult(modelMapper.map(dislike1, DislikeDTO.class));

    }

    @PostMapping(value = "/comment", produces = "application/json")
    public ApiResponse<CommentDTO> createCommentDTO(@RequestBody CommentDTO commentDTO) throws CustomException {

        Comment comment1 = this.filmService.createComment(modelMapper.map(commentDTO, Comment.class));
        CommentDTO commentDTO1 = modelMapper.map(comment1, CommentDTO.class);
        var user = userService.findById(comment1.getUser().getId());
        commentDTO1.setUsername(user.getUsername());
        return ApiResponse.successWithResult(commentDTO1);

    }


    @DeleteMapping(value = "/comment/{id}", produces = "application/json")
    public ApiResponse<Comment> deleteComment(@PathVariable Long id) throws CustomException {
        return ApiResponse.successWithResult(this.filmService.deleteComment(id), "Delete Comment Successfully");

    }

    @DeleteMapping(value = "/like/{id}", produces = "application/json")
    public ApiResponse<Like> deleteLike(@PathVariable Long id) throws CustomException {

        return ApiResponse.successWithResult(this.filmService.deleteLike(id));

    }

    @DeleteMapping(value = "/dislike/{id}", produces = "application/json")
    public ApiResponse<Dislike> deleteDislike(@PathVariable Long id) throws CustomException {
        return ApiResponse.successWithResult(this.filmService.deleteDislike(id));
    }

    @GetMapping(value = "/comment/pagination/{filmId}", produces = "application/json")
    public ApiResponse<CommentPaginationResponse> getCommentPagination(@PathVariable Long filmId, @RequestParam int page, @RequestParam int size, @RequestParam String sortBy, @RequestParam String orderBy) throws CustomException {
        var entity = this.filmService.getCommentPagination(filmId, page, size, sortBy, orderBy);
        return ApiResponse.successWithResult(entity);
    }
}
