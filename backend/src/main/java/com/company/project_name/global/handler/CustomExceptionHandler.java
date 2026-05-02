package com.company.project_name.global.handler;

import com.company.project_name.external.bser.exception.BserApiException;
import com.company.project_name.global.dto.CommonResponse;
import com.company.project_name.global.exception.CustomNotFoundException;
import com.company.project_name.global.exception.CustomValidateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomNotFoundException.class)
    public ResponseEntity<CommonResponse<?>> handleCustomNotFoundException(CustomNotFoundException e) {
        log.error("CustomNotFoundException: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(CommonResponse.error(404, e.getMessage()));
    }

    @ExceptionHandler(CustomValidateException.class)
    public ResponseEntity<CommonResponse<?>> handleCustomValidateException(CustomValidateException e) {
        log.error("CustomValidateException: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(CommonResponse.error(400, e.getMessage()));
    }

    @ExceptionHandler(BserApiException.class)
    public ResponseEntity<CommonResponse<?>> handleBserApiException(BserApiException e) {
        log.error("BserApiException [{}]: {}", e.getHttpStatus(), e.getMessage());
        int status = e.getHttpStatus() == 404 ? 404 : 502;
        return ResponseEntity.status(status)
                .body(CommonResponse.error(status, "외부 API 오류: " + e.getMessage()));
    }
}
