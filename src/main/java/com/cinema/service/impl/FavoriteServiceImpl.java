package com.cinema.service.impl;

import com.cinema.entity.FavoriteDTO;
import com.cinema.exception.BadRequestException;
import com.cinema.exception.NotFoundException;
import com.cinema.model.Favorite;
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
    public Favorite findById(Long id) {
        Optional<Favorite> optionalFavorite = favoriteRepository.findById(id);
        if (optionalFavorite.isEmpty()) {
            throw new NotFoundException("Could not find favorite width id: " + id);
        }
        return optionalFavorite.get();
    }

    @Override
    public Favorite createFavorite(FavoriteDTO favoriteDTO) {
        userService.checkPermission(favoriteDTO.getUserId());
        if (favoriteRepository.existsByFilmIdAndUserId(favoriteDTO.getFilmId(), favoriteDTO.getUserId())) {
            throw new BadRequestException("This user already marked this film as favorite");
        } 
        return favoriteRepository.save(modelMapper.map(favoriteDTO, Favorite.class));
    }

    @Override
    public void deleteFavorite(Long id) {
        Favorite favorite = findById(id);
        userService.checkPermission(favorite.getUser().getId());
        favoriteRepository.deleteById(id);
    }
}
