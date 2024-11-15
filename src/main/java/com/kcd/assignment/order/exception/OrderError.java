package com.kcd.assignment.order.exception;

public enum OrderError {
    ORDER_NOT_FOUND("ORDER-001", "주문을 찾을 수 없습니다."),

    ;
    private final String code;
    private final String message;

    OrderError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
