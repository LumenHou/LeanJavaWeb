package com.lean.lumen.exception;

import lombok.Data;

@Data
public class CustomizeException extends RuntimeException {

    private String message;

    public CustomizeException(String message) {
        this.message = message;
    }
}
