package com.cinema.controller.request;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private Long id;

    @NotNull
    private Long userId;

    private String username;

    @NotNull
    private Long filmId;

    @NotBlank
    private String commentText;

    private Date createdAt;


}
