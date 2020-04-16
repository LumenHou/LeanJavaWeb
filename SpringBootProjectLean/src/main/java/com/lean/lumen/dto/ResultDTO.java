package com.lean.lumen.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultDTO {

    private Integer code;
    private String message;

    public static ResultDTO errorOf(Integer Code, String message) {
        return new ResultDTO(Code, message);
    }

    public static ResultDTO okOf() {
        return new ResultDTO(200, "操作成功");
    }
}
