package com.cinema.service.impl;

import com.cinema.CustomException;
import com.cinema.constants.ErrorCode;
import com.cinema.controller.request.FavoriteDTO;
import com.cinema.entities.Favorite;
import com.cinema.repository.FavoriteRepository;
import com.cinema.service.FavoriteService;
import com.cinema.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Override
    public Favorite findById(Long id) throws CustomException {
        Optional<Favorite> optionalFavorite = favoriteRepository.findById(id);
        if (optionalFavorite.isEmpty()) {
            throw new CustomException(ErrorCode.FAVOURITE_EXIST);
        }
        return optionalFavorite.get();
    }

    @Override
    public Favorite createFavorite(FavoriteDTO favoriteDTO) throws CustomException {
        userService.checkPermission(favoriteDTO.getUserId());
        if (favoriteRepository.existsByFilmIdAndUserId(favoriteDTO.getFilmId(), favoriteDTO.getUserId())) {
            throw new CustomException(ErrorCode.FAVOURITE_EXIST);
        }
        return favoriteRepository.save(modelMapper.map(favoriteDTO, Favorite.class));
    }

    @Override
    public void deleteFavorite(Long id) throws CustomException {
        Favorite favorite = findById(id);
        userService.checkPermission(favorite.getUser().getId());
        favoriteRepository.deleteById(id);
    }
}
