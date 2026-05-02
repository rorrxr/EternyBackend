package com.company.project_name.external.bser.exception;

public class BserApiException extends RuntimeException {

    private final int httpStatus;

    public BserApiException(int httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public int getHttpStatus() {
        return httpStatus;
    }
}
