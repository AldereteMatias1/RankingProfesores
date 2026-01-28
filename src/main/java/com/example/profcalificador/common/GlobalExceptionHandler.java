package com.example.profcalificador.common;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ApiError> notFound(NotFoundException ex, HttpServletRequest req) {
    return build(HttpStatus.NOT_FOUND, ex.getMessage(), req.getRequestURI(), null);
  }

  @ExceptionHandler(BusinessRuleException.class)
  public ResponseEntity<ApiError> business(BusinessRuleException ex, HttpServletRequest req) {
    return build(HttpStatus.CONFLICT, ex.getMessage(), req.getRequestURI(), null);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiError> validation(MethodArgumentNotValidException ex, HttpServletRequest req) {
    List<ApiError.FieldError> fields = ex.getBindingResult().getFieldErrors().stream()
      .map(f -> new ApiError.FieldError(f.getField(), f.getDefaultMessage()))
      .toList();

    return build(HttpStatus.BAD_REQUEST, "Validación fallida", req.getRequestURI(), fields);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiError> generic(Exception ex, HttpServletRequest req) {
    return build(HttpStatus.INTERNAL_SERVER_ERROR, "Error inesperado", req.getRequestURI(), null);
  }

  private ResponseEntity<ApiError> build(HttpStatus status, String msg, String path, List<ApiError.FieldError> fields) {
    ApiError body = new ApiError(
      OffsetDateTime.now(),
      status.value(),
      status.getReasonPhrase(),
      msg,
      path,
      fields
    );
    return ResponseEntity.status(status).body(body);
  }
}
