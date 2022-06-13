package com.cinema.service;

import com.cinema.entity.FavoriteDTO;
import com.cinema.model.Favorite;

public interface FavoriteService {
    Favorite findById(Long id);
    Favorite createFavorite(FavoriteDTO favoriteDTO);
    void deleteFavorite(Long id);
}
