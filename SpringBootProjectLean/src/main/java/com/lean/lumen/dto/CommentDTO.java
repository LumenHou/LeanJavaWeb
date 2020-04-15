package com.lean.lumen.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private Long parentID;
    private String content;
    private Long type;


}
