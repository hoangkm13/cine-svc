package com.cinema.controller;

import com.cinema.entity.FavoriteDTO;
import com.cinema.exception.BadRequestException;
import com.cinema.exception.HttpException;
import com.cinema.exception.InternalServerException;
import com.cinema.model.Favorite;
import com.cinema.service.FavoriteService;
import com.cinema.util.GeneralUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FavoriteController {
    private final FavoriteService favoriteService;
    private final ModelMapper modelMapper;
    
    @PostMapping("/favorites")
    public ResponseEntity<FavoriteDTO> createFavorite(@Valid @RequestBody FavoriteDTO favoriteDTO, Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = GeneralUtils.processValidationError(errors);
            throw new BadRequestException(errorMessage);
        }
        
        try {
            Favorite favorite = favoriteService.createFavorite(favoriteDTO);
            return ResponseEntity.ok(modelMapper.map(favorite, FavoriteDTO.class));
        } catch (HttpException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalServerException(ex.getMessage());
        }
    }

    @DeleteMapping("/favorites/{id}")
    public ResponseEntity<Favorite> deleteFavorite(@PathVariable Long id) {
        try {
            favoriteService.deleteFavorite(id);
            return ResponseEntity.noContent().build();
        } catch (HttpException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalServerException(ex.getMessage());
        }
    }
}
