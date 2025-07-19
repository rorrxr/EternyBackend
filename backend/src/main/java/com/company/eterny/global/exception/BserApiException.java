package com.company.eterny.global.exception;

/**
 * BSER API 관련 예외
 */
public class BserApiException extends RuntimeException {
    
    private final int statusCode;
    private final String apiEndpoint;
    
    public BserApiException(String message) {
        super(message);
        this.statusCode = 500;
        this.apiEndpoint = "unknown";
    }
    
    public BserApiException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
        this.apiEndpoint = "unknown";
    }
    
    public BserApiException(String message, int statusCode, String apiEndpoint) {
        super(message);
        this.statusCode = statusCode;
        this.apiEndpoint = apiEndpoint;
    }
    
    public BserApiException(String message, Throwable cause) {
        super(message, cause);
        this.statusCode = 500;
        this.apiEndpoint = "unknown";
    }
    
    public BserApiException(String message, Throwable cause, int statusCode, String apiEndpoint) {
        super(message, cause);
        this.statusCode = statusCode;
        this.apiEndpoint = apiEndpoint;
    }
    
    public int getStatusCode() {
        return statusCode;
    }
    
    public String getApiEndpoint() {
        return apiEndpoint;
    }
}
