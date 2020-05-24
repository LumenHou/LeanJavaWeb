package com.lean.lumen.bean;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ResultDTO<T> {
    private T data;
    private Boolean state = true;
    private String msg;

    public static <T> ResultDTO<T> success(T t, String message) {
        ResultDTO<T> resultDTO = new ResultDTO();
        resultDTO.setData(t).setMsg(message);
        return resultDTO;
    }

    public static <T> ResultDTO<T> fail(T t, String message) {
        ResultDTO<T> resultDTO = new ResultDTO();
        resultDTO.setData(t).setMsg(message).setState(false);
        return resultDTO;
    }

}
