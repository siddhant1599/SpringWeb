package com.web.springweb.springweb.advices;

import com.web.springweb.springweb.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleResourceNotFound(ResourceNotFoundException exception){
        ApiError apiError = ApiError.builder().status(HttpStatus.NOT_FOUND).errorMessage(exception.getMessage()).build();
        return buildErrorResponseEntity(apiError);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleInternalServerError(Exception e){
        ApiError apiError = ApiError.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).errorMessage(e.getMessage()).build();
        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleInputValidationError(MethodArgumentNotValidException exception){
        List<String> errors =  exception.getBindingResult()
                .getAllErrors().stream().
                map((objectError -> objectError.getDefaultMessage())).
                collect(Collectors.toList());

        ApiError apiError = ApiError.builder().
                status(HttpStatus.BAD_REQUEST).
                errorMessage("Input Validation Failed").subErrorMessages(errors ).build();
        return buildErrorResponseEntity(apiError);
    }

    private ResponseEntity<ApiResponse<?>> buildErrorResponseEntity(ApiError error){

        ApiResponse<ApiError> response = new ApiResponse<>(error);
        return new ResponseEntity<>(response, error.getStatus());
    }
}
