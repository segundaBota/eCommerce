package org.example.jle.ecommerce.application.exception.utils;

public enum ErrorCode {
    UNDEFINED_ERROR(0, "Undefined error."),
    PRICE_NOT_FOUND(1, "Price not found."),
    ERROR_DB_CALL(2, "Error in database call."),
    PROCESS_ERROR(3, "Error in processing.");

    private final int code;
    private final String description;

    ErrorCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public String toString() { return this.code + ": " + this.description; }

    public int getCode() { return this.code; }

    public String getDescription() { return this.description; }
}
