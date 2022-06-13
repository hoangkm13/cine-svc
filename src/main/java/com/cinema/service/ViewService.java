package com.cinema.service;

import com.cinema.controller.request.ViewCountDTO;
import com.cinema.controller.request.ViewDTO;

public interface ViewService {
    long countByFilmId(Long userId);
    void createView(ViewDTO viewDTO);
}
