package com.cinema.repository;

import com.cinema.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    boolean existsByFilmIdAndUserId(Long filmId, Long userId);
}
