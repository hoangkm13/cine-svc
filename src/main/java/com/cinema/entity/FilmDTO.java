package com.cinema.entity;

import com.cinema.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmDTO {
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private Integer maturity;

    @NotNull
    private String slug;

    private List<Actor> actors = new ArrayList<>();

    private List<LikeDTO> likes = new ArrayList<>();

    private List<DislikeDTO> dislikes = new ArrayList<>();

    private List<CommentDTO> comments = new ArrayList<>();

    private List<Genre> genres = new ArrayList<>();

    private List<View> views = new ArrayList<>();

    private Integer ratingStar;

    private Date createdAt;

    private Date updatedAt;



}
