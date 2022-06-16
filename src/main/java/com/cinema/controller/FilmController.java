package com.cinema.controller;

import com.cinema.exception.CustomException;
import com.cinema.controller.request.*;
import com.cinema.entities.*;
import com.cinema.model.ApiResponse;
import com.cinema.service.FilmService;
import com.cinema.service.GenreService;
import com.cinema.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/films")
public class FilmController {
    private final FilmService filmService;
    private final GenreService genreService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @GetMapping("/browse")
    public ApiResponse<List<CategorizedFilmsDTO>> getBrowseData(@RequestParam int size) {
        List<CategorizedFilmsDTO> categorizedFilmsDTOList = new ArrayList<>();
        List<Film> films = new ArrayList<>();
        List<Genre> genres = genreService.findAll();
        genres.forEach(genre -> {
            List<Long> excludedFilmsIds = new ArrayList<>();
            excludedFilmsIds.add(0L); // SQl operator IN will not work with empty array
            excludedFilmsIds.addAll(films.stream().map(Film::getId).toList());
            List<Film> list = filmService.findAllByGenres(genre.getId(), 0, size, "created_at", excludedFilmsIds);
            films.addAll(list);
            List<FilmDTO> filmDTOList = list.stream().map(film -> modelMapper.map(film, FilmDTO.class)).collect(Collectors.toList());

            categorizedFilmsDTOList.add(new CategorizedFilmsDTO(genre, filmDTOList));
        });
        return ApiResponse.successWithResult(categorizedFilmsDTOList);
    }

    @GetMapping("/search")
    public ApiResponse<Page<Film>> getSearchData(@RequestParam String searchText, @RequestParam int page, @RequestParam int size, @RequestParam String sortBy) {
        Page<Film> films = filmService.search(searchText, page - 1, size, sortBy);
        return ApiResponse.successWithResult(films);
    }

    @GetMapping("/{genre}")
    public ApiResponse<Page<Film>> getFilmsByGenre(@PathVariable("genre") String genreName, @RequestParam int page, @RequestParam int size, @RequestParam String sortBy) throws CustomException {
        Genre genre = genreService.findByName(genreName);
        return ApiResponse.successWithResult(filmService.findAllByGenres(genre, page - 1, size, sortBy));
    }

    @GetMapping("/favorites")
    public ApiResponse<Page<Film>> getFavoriteFilms(@RequestParam int page, @RequestParam int size, @RequestParam String sortBy) throws CustomException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username);
        return ApiResponse.successWithResult(filmService.findFavoriteFilmsByUserId(user.getId(), page - 1, size, sortBy));
    }

    @GetMapping("/singleFilm/{filmId}")
    public ApiResponse<FilmDTO> getFilmById(@PathVariable("filmId") Long filmId) throws CustomException {

        Film film = this.filmService.findById(filmId);
        FilmDTO filmDTO = modelMapper.map(film, FilmDTO.class);
        if (film.getComments().size() > 0) {
            for (CommentDTO comment : filmDTO.getComments()) {
                comment.setUsername(film.getComments().get(0).getUser().getUsername());
            }
        }

        return ApiResponse.successWithResult(filmDTO);

    }

    @PostMapping("/like")
    public ApiResponse<LikeDTO> createLike(@RequestBody LikeDTO likeDTO) {

        Like like1 = this.filmService.createLike(modelMapper.map(likeDTO, Like.class));

        return ApiResponse.successWithResult(modelMapper.map(like1, LikeDTO.class));

    }

    @PostMapping("/dislike")
    public ApiResponse<DislikeDTO> createDislike(@RequestBody DislikeDTO dislikeDTO) {

        Dislike dislike1 = this.filmService.createDislike(modelMapper.map(dislikeDTO, Dislike.class));
        return ApiResponse.successWithResult(modelMapper.map(dislike1, DislikeDTO.class));

    }

    @PostMapping("/comment")
    public ApiResponse<CommentDTO> createCommentDTO(@RequestBody CommentDTO commentDTO) throws CustomException {

        Comment comment1 = this.filmService.createComment(modelMapper.map(commentDTO, Comment.class));
        CommentDTO commentDTO1 = modelMapper.map(comment1, CommentDTO.class);
        var user = userService.findById(comment1.getUser().getId());
        commentDTO1.setUsername(user.getUsername());
        return ApiResponse.successWithResult(commentDTO1);

    }


    @DeleteMapping("/comment/{id}")
    public ApiResponse<Comment> deleteComment(@PathVariable Long id) throws CustomException {
        return ApiResponse.successWithResult(this.filmService.deleteComment(id), "Delete Comment Successfully");

    }

    @DeleteMapping("/like/{id}")
    public ApiResponse<Like> deleteLike(@PathVariable Long id) throws CustomException {

        return ApiResponse.successWithResult(this.filmService.deleteLike(id));

    }

    @DeleteMapping("/dislike/{id}")
    public ApiResponse<Dislike> deleteDislike(@PathVariable Long id) throws CustomException {
        return ApiResponse.successWithResult(this.filmService.deleteDislike(id));
    }

    @GetMapping("/comment/pagination/{filmId}")
    public ApiResponse<Page<Comment>> getCommentPagination(@PathVariable Long filmId, @RequestParam int page, @RequestParam int size, @RequestParam String sortBy, @RequestParam String orderBy) throws CustomException {
        var entity = this.filmService.getCommentPagination(filmId, page, size, sortBy, orderBy);
        return ApiResponse.successWithResult(entity);
    }
}
