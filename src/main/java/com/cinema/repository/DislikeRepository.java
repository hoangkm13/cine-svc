package com.cinema.repository;

import com.cinema.model.Dislike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DislikeRepository extends JpaRepository<Dislike, Long> {


}
