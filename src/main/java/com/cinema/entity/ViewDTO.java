package com.cinema.entity;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewDTO {
    private Long id;

    @NotNull
    private Long filmId;

    @NotNull
    private Long userId;
    
    private Date createdAt;
}
