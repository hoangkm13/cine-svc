package com.cinema.controller;

import com.cinema.exception.CustomException;
import com.cinema.controller.request.FavoriteDTO;
import com.cinema.entities.Favorite;
import com.cinema.model.ApiResponse;
import com.cinema.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/favorites")
public class FavoriteController {
    private final FavoriteService favoriteService;
    private final ModelMapper modelMapper;

    @PostMapping(value = "", produces = "application/json")
    public ApiResponse<FavoriteDTO> createFavorite(@Valid @RequestBody FavoriteDTO favoriteDTO) throws CustomException {
        Favorite favorite = favoriteService.createFavorite(favoriteDTO);
        return ApiResponse.successWithResult(modelMapper.map(favorite, FavoriteDTO.class));
    }

    @GetMapping(value = "{id}", produces = "application/json")
    public ApiResponse<Favorite> getFavorite(@PathVariable Long id) throws CustomException {
        Favorite favorite = favoriteService.findById(id);
        return ApiResponse.successWithResult(favorite);
    }

    @DeleteMapping(value = "{id}", produces = "application/json")
    public ApiResponse<String> deleteFavorite(@PathVariable Long id) throws CustomException {
        favoriteService.deleteFavorite(id);
        return ApiResponse.successWithResult("Delete Favourite Successfully");
    }
}
