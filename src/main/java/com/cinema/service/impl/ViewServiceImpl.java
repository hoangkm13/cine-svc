package com.cinema.service.impl;

import com.cinema.controller.request.ViewDTO;
import com.cinema.entities.View;
import com.cinema.repository.ViewRepository;
import com.cinema.service.ViewService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ViewServiceImpl implements ViewService {
    private final ViewRepository viewRepository;
    private final ModelMapper modelMapper;

    @Override
    public long countByFilmId(Long userId) {
        return viewRepository.countByFilmId(userId);
    }

    @Override
    public void createView(ViewDTO viewDTO) {
        viewRepository.save(modelMapper.map(viewDTO, View.class));
    }
}
