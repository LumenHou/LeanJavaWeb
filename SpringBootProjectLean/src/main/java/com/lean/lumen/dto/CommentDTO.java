package com.lean.lumen.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private Long parentId;
    private String content;
    private Long type;
    private Integer commentator;


}
