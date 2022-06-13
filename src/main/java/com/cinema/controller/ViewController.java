package com.cinema.controller;

import com.cinema.controller.request.ViewCountDTO;
import com.cinema.controller.request.ViewDTO;
import com.cinema.exception.BadRequestException;
import com.cinema.exception.HttpException;
import com.cinema.exception.InternalServerException;
import com.cinema.service.ViewService;
import com.cinema.util.GeneralUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ViewController {
    private final ViewService viewService;
    
    @GetMapping("films/{id}/views/count")
    public ResponseEntity<ViewCountDTO> getViewCountByFilmId(@PathVariable("id") Long filmId) {
        try {
            return ResponseEntity.ok(new ViewCountDTO(viewService.countByFilmId(filmId)));
        } catch (HttpException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalServerException(ex.getMessage());
        }
    }
    
    @PostMapping("/views")
    public ResponseEntity<ViewCountDTO> createView(@Valid @RequestBody ViewDTO viewDTO, Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = GeneralUtils.processValidationError(errors);
            throw new BadRequestException(errorMessage);
        }
        try {
            viewService.createView(viewDTO);
            return ResponseEntity.noContent().build();
        } catch (HttpException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalServerException(ex.getMessage());
        }
    }
    
}
