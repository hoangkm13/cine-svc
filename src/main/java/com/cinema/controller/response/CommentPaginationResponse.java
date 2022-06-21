package com.cinema.controller.response;

import com.cinema.controller.request.CommentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentPaginationResponse {
    private List<CommentDTO> commentDTOList;
    private Integer totalElements;

}
