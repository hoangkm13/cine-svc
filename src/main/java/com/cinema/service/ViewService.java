package com.cinema.service;

import com.cinema.entity.ViewDTO;

public interface ViewService {
    long countByFilmId(Long userId);
    void createView(ViewDTO viewDTO);
}
