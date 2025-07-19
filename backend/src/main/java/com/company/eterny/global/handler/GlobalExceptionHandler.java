package com.company.eterny.global.handler;

import com.company.eterny.global.dto.CommonResponse;
import com.company.eterny.global.exception.BserApiException;
import com.company.eterny.global.exception.CustomNotFoundException;
import com.company.eterny.global.exception.CustomValidateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.concurrent.TimeoutException;

/**
 * 전역 예외 처리 핸들러
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * BSER API 예외 처리
     */
    @ExceptionHandler(BserApiException.class)
    public ResponseEntity<CommonResponse<Object>> handleBserApiException(BserApiException e) {
        log.error("BSER API 예외 발생 - endpoint: {}, statusCode: {}, message: {}", 
                e.getApiEndpoint(), e.getStatusCode(), e.getMessage(), e);
        
        return ResponseEntity.ok(
            new CommonResponse<>(e.getStatusCode(), 
                String.format("BSER API 오류: %s", e.getMessage()), null)
        );
    }

    /**
     * WebClient 예외 처리
     */
    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<CommonResponse<Object>> handleWebClientResponseException(WebClientResponseException e) {
        log.error("WebClient 요청 실패 - status: {}, message: {}", e.getStatusCode(), e.getMessage(), e);
        
        String message = switch (e.getStatusCode().value()) {
            case 400 -> "잘못된 요청입니다.";
            case 401 -> "API 인증에 실패했습니다.";
            case 403 -> "API 접근이 거부되었습니다.";
            case 404 -> "요청한 데이터를 찾을 수 없습니다.";
            case 429 -> "API 요청 한도를 초과했습니다.";
            case 500 -> "외부 API 서버 오류입니다.";
            default -> "외부 API 통신 중 오류가 발생했습니다.";
        };
        
        return ResponseEntity.ok(
            new CommonResponse<>(e.getStatusCode().value(), message, null)
        );
    }

    /**
     * 타임아웃 예외 처리
     */
    @ExceptionHandler(TimeoutException.class)
    public ResponseEntity<CommonResponse<Object>> handleTimeoutException(TimeoutException e) {
        log.error("요청 타임아웃 발생: {}", e.getMessage(), e);
        
        return ResponseEntity.ok(
            new CommonResponse<>(408, "요청 시간이 초과되었습니다. 잠시 후 다시 시도해주세요.", null)
        );
    }

    /**
     * 데이터베이스 접근 예외 처리
     */
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<CommonResponse<Object>> handleDataAccessException(DataAccessException e) {
        log.error("데이터베이스 접근 오류: {}", e.getMessage(), e);
        
        return ResponseEntity.ok(
            new CommonResponse<>(500, "데이터베이스 처리 중 오류가 발생했습니다.", null)
        );
    }

    /**
     * 커스텀 NotFound 예외 처리
     */
    @ExceptionHandler(CustomNotFoundException.class)
    public ResponseEntity<CommonResponse<Object>> handleCustomNotFoundException(CustomNotFoundException e) {
        log.warn("리소스를 찾을 수 없음: {}", e.getMessage());
        
        return ResponseEntity.ok(
            new CommonResponse<>(404, e.getMessage(), null)
        );
    }

    /**
     * 커스텀 Validation 예외 처리
     */
    @ExceptionHandler(CustomValidateException.class)
    public ResponseEntity<CommonResponse<Object>> handleCustomValidateException(CustomValidateException e) {
        log.warn("유효성 검증 실패: {}", e.getMessage());
        
        return ResponseEntity.ok(
            new CommonResponse<>(400, e.getMessage(), null)
        );
    }

    /**
     * Bean Validation 예외 처리
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public ResponseEntity<CommonResponse<Object>> handleValidationException(Exception e) {
        String message = "입력값이 올바르지 않습니다.";
        
        if (e instanceof MethodArgumentNotValidException methodArgException) {
            if (methodArgException.getBindingResult().hasFieldErrors()) {
                message = methodArgException.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
            }
        } else if (e instanceof BindException bindException) {
            if (bindException.getBindingResult().hasFieldErrors()) {
                message = bindException.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
            }
        }
        
        log.warn("유효성 검증 실패: {}", message);
        
        return ResponseEntity.ok(
            new CommonResponse<>(400, message, null)
        );
    }

    /**
     * IllegalArgument 예외 처리
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<CommonResponse<Object>> handleIllegalArgumentException(IllegalArgumentException e) {
        log.warn("잘못된 인수: {}", e.getMessage());
        
        return ResponseEntity.ok(
            new CommonResponse<>(400, e.getMessage(), null)
        );
    }

    /**
     * NullPointer 예외 처리
     */
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<CommonResponse<Object>> handleNullPointerException(NullPointerException e) {
        log.error("NullPointer 예외 발생: {}", e.getMessage(), e);
        
        return ResponseEntity.ok(
            new CommonResponse<>(500, "처리 중 오류가 발생했습니다.", null)
        );
    }

    /**
     * 캐시 관련 예외 처리
     */
    @ExceptionHandler(org.springframework.cache.Cache.ValueRetrievalException.class)
    public ResponseEntity<CommonResponse<Object>> handleCacheException(Exception e) {
        log.error("캐시 처리 중 오류 발생: {}", e.getMessage(), e);
        
        return ResponseEntity.ok(
            new CommonResponse<>(500, "캐시 처리 중 오류가 발생했습니다.", null)
        );
    }

    /**
     * 일반적인 런타임 예외 처리
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<CommonResponse<Object>> handleRuntimeException(RuntimeException e) {
        log.error("런타임 예외 발생: {}", e.getMessage(), e);
        
        return ResponseEntity.ok(
            new CommonResponse<>(500, "서버 처리 중 오류가 발생했습니다.", null)
        );
    }

    /**
     * 모든 예외의 최종 처리
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponse<Object>> handleException(Exception e) {
        log.error("예상치 못한 예외 발생: {}", e.getMessage(), e);
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new CommonResponse<>(500, "서버에서 예상치 못한 오류가 발생했습니다.", null));
    }
}
