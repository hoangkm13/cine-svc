package com.cinema.repository;

import com.cinema.model.Film;
import com.cinema.model.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
    Page<Film> findAllByGenres(Genre genre, Pageable pageable);

    @Query(
            nativeQuery = true,
            value = "select * from film " +
                    "join actor_film on film.id = actor_film.film_id " +
                    "join actor on actor.id = actor_film.actor_id " +
                    "where film.title like concat('%', :searchText, '%') or actor.name like concat('%', :searchText, '%') group by film.id",
            countQuery = "select count(*) from film " +
                    "join actor_film on film.id = actor_film.film_id " +
                    "join actor on actor.id = actor_film.actor_id " +
                    "where film.title like concat('%', :searchText, '%') or actor.name like concat('%', :searchText, '%') group by film.id"
    )
    Page<Film> findAllBySearchText(String searchText, Pageable pageable);

    @Query(
            nativeQuery = true,
            value = "select * from film " +
                    "join favorite on film.id = favorite.film_id " +
                    "join user on favorite.user_id = user.id " +
                    "where user.id = :userId order by film.created_at",
            countQuery = "select count(*) from favorite where user_id = :userId"
    )
    Page<Film> findFavoriteFilmsByUserId(Long userId, Pageable pageable);

    @Query(
            nativeQuery = true,
            value = "select * from film f " +
                    "join film_genre fg on f.id = fg.film_id " +
                    "where fg.genre_id = :genre_id " +
                    "and f.id not in (:exluded_film_ids) " +
                    "order by :sort_by " +
                    "limit :size offset :offset"
    )
    List<Film> findAllByGenres(
            @Param("genre_id") Long genreId,
            @Param("offset") int offset,
            @Param("size") int size,
            @Param("sort_by") String sortBy,
            @Param("exluded_film_ids") List<Long> exludedFilmIds
    );

}
