package com.cinema.controller;

import com.cinema.entity.*;
import com.cinema.exception.HttpException;
import com.cinema.exception.InternalServerException;
import com.cinema.model.*;
import com.cinema.repository.LikeRepository;
import com.cinema.service.FilmService;
import com.cinema.service.GenreService;
import com.cinema.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
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
    private final LikeRepository likeRepository;

    @GetMapping("/browse")
    public ResponseEntity<List<CategorizedFilmsDTO>> getBrowseData(@RequestParam int size) {
        try {
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
            return ResponseEntity.ok(categorizedFilmsDTOList);
        } catch (HttpException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalServerException(ex.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Film>> getSearchData(@RequestParam String searchText, @RequestParam int page, @RequestParam int size, @RequestParam String sortBy) {
        try {
            Page<Film> films = filmService.search(searchText, page - 1, size, sortBy);
            return ResponseEntity.ok(films);
        } catch (HttpException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalServerException(ex.getMessage());
        }
    }

//    @PutMapping()
//    public ResponseEntity<FilmDTO> updateFilm(@RequestBody FilmDTO filmDTO) {
//        try {
//            var entity = this.filmService.updateFilm(filmDTO);
//            return ResponseEntity.ok(modelMapper.map(entity, FilmDTO.class));
//        } catch (HttpException ex) {
//            throw ex;
//        } catch (Exception ex) {
//            throw new InternalServerException(ex.getMessage());
//        }
//    }


    @GetMapping("/{genre}")
    public ResponseEntity<Page<Film>> getFilmsByGenre(@PathVariable("genre") String genreName, @RequestParam int page, @RequestParam int size, @RequestParam String sortBy) {
        try {
            Genre genre = genreService.findByName(genreName);
            return ResponseEntity.ok(filmService.findAllByGenres(genre, page - 1, size, sortBy));
        } catch (HttpException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalServerException(ex.getMessage());
        }
    }

    @GetMapping("/favorites")
    public ResponseEntity<Page<Film>> getFavoriteFilms(@RequestParam int page, @RequestParam int size, @RequestParam String sortBy) {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userService.findByUsername(username);
            return ResponseEntity.ok(filmService.findFavoriteFilmsByUserId(user.getId(), page - 1, size, sortBy));
        } catch (HttpException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalServerException(ex.getMessage());
        }
    }

    @GetMapping("/singleFilm/{filmId}")
    public ResponseEntity<FilmDTO> getFilmById(@PathVariable("filmId") Long filmId) {
        try {
            Film film = this.filmService.findById(filmId);
            FilmDTO filmDTO = modelMapper.map(film, FilmDTO.class);
            if (film.getComments().size() > 0){
                for(CommentDTO comment: filmDTO.getComments()){
                    comment.setUsername(film.getComments().get(0).getUser().getUsername());
                }
            }

            return ResponseEntity.ok(filmDTO);
        } catch (HttpException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalServerException(ex.getMessage());
        }
    }

    @PostMapping("/like")
    public ResponseEntity<LikeDTO> createLike(@RequestBody LikeDTO likeDTO) {
        try {
            Like like1 = this.filmService.createLike(modelMapper.map(likeDTO, Like.class));

            return ResponseEntity.ok(modelMapper.map(like1, LikeDTO.class));
        } catch (HttpException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalServerException(ex.getMessage());
        }
    }

    @PostMapping("/dislike")
    public ResponseEntity<DislikeDTO> createDislike(@RequestBody DislikeDTO dislikeDTO) {
        try {
            Dislike dislike1 = this.filmService.createDislike(modelMapper.map(dislikeDTO, Dislike.class));
            return ResponseEntity.ok(modelMapper.map(dislike1, DislikeDTO.class));
        } catch (HttpException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalServerException(ex.getMessage());
        }
    }

    @PostMapping("/comment")
    public ResponseEntity<CommentDTO> createCommentDTO(@RequestBody CommentDTO commentDTO) {
        try {
            Comment comment1 = this.filmService.createComment(modelMapper.map(commentDTO, Comment.class));
            CommentDTO commentDTO1 = modelMapper.map(comment1, CommentDTO.class);
            var user = userService.findById(comment1.getUser().getId());
            commentDTO1.setUsername(user.getUsername());
            return ResponseEntity.ok(commentDTO1);
        } catch (HttpException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalServerException(ex.getMessage());
        }
    }


    @DeleteMapping("/comment/{id}")
    public ResponseEntity<Comment> deleteComment(@PathVariable Long id) {
        try {
            this.filmService.deleteComment(id);
            return ResponseEntity.noContent().build();
        } catch (HttpException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalServerException(ex.getMessage());
        }
    }

    @DeleteMapping("/like/{id}")
    public ResponseEntity<Like> deleteLike(@PathVariable Long id) {
        try {
            this.filmService.deleteLike(id);
            return ResponseEntity.noContent().build();
        } catch (HttpException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalServerException(ex.getMessage());
        }
    }

    @DeleteMapping("/dislike/{id}")
    public ResponseEntity<Dislike> deleteDislike(@PathVariable Long id) {
        try {
            this.filmService.deleteDislike(id);
            return ResponseEntity.noContent().build();
        } catch (HttpException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalServerException(ex.getMessage());
        }
    }
}
