package com.lean.lumen.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomizeException extends RuntimeException {

    private Integer code;
    private String message;

}
