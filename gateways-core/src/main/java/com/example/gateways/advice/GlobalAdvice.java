package com.example.gateways.advice;

import com.example.gateways.dto.ErrorDto;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalAdvice {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    protected ResponseEntity<ErrorDto> handleConflict(
        MethodArgumentNotValidException ex, WebRequest request) {
        var errorMessage = ex.getBindingResult().getAllErrors().stream().map(i -> {
            // getCodes[1] = Pattern.<FIELD_NAME>
            return "Incorrect parameter: " + i.getCodes()[1].replace("Pattern.", "");
        }).collect(Collectors.joining("\n"));
        var errorDto = new ErrorDto();
        errorDto.setMessage(errorMessage);
        errorDto.setCode(400L);
        return new ResponseEntity<>(errorDto, HttpStatusCode.valueOf(400));
    }

    @ExceptionHandler(value = {Throwable.class})
    protected ResponseEntity<ErrorDto> generalHandle() {
        var errorDto = new ErrorDto();
        errorDto.setMessage("Unknown error");
        errorDto.setCode(500L);
        return new ResponseEntity<>(errorDto, HttpStatusCode.valueOf(500));
    }
}
