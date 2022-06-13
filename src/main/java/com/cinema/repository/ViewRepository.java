package com.cinema.repository;

import com.cinema.entities.View;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewRepository extends JpaRepository<View, Long> {
    long countByFilmId(Long userId);
}
