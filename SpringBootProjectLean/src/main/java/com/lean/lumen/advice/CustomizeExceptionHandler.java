package com.lean.lumen.advice;

import com.lean.lumen.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    Object handleControllerException(HttpServletRequest request, Throwable e, Model model,
                                     HttpServletResponse response) {
        HttpStatus status = getStatus(request);

        String contentType = request.getContentType();
        if (contentType.contains("json")) {
            return null;
//            return ResultDTO.errorOf(2001,"报错了");
        } else {
            if (e instanceof CustomizeException) {
                model.addAttribute("errorMessage", e.getMessage());
            } else {
                model.addAttribute("errorMessage", "稍后再试...");
            }

            return new ModelAndView("error");
        }
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

}
