package com.cinema.controller;

import com.cinema.controller.request.ViewCountDTO;
import com.cinema.controller.request.ViewDTO;
import com.cinema.model.ApiResponse;
import com.cinema.service.ViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api")
public class ViewController {
    private final ViewService viewService;

    @GetMapping("films/{id}/views/count")
    public ApiResponse<ViewCountDTO> getViewCountByFilmId(@PathVariable("id") Long filmId) {
        return ApiResponse.successWithResult(new ViewCountDTO(viewService.countByFilmId(filmId)));
    }

    @PostMapping("/views")
    public ApiResponse<String> createView(@Valid @RequestBody ViewDTO viewDTO) {
        viewService.createView(viewDTO);
        return ApiResponse.successWithResult("Create view successfully");

    }

}
