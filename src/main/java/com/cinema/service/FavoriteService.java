package com.cinema.service;

import com.cinema.exception.CustomException;
import com.cinema.controller.request.FavoriteDTO;
import com.cinema.entities.Favorite;

public interface FavoriteService {
    Favorite findById(Long id) throws CustomException;
    Favorite createFavorite(FavoriteDTO favoriteDTO) throws CustomException;
    void deleteFavorite(Long id) throws CustomException;
}
