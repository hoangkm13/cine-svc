package com.example.customersvc.constants;

import lombok.Getter;

@Getter
public enum ErrorCode {

    UNKNOWN_ERROR("UNKNOWN_ERROR", "Lỗi không xác định"),
    CUSTOMER_NOT_EXIST("CUSTOMER_NOT_EXIST", "Khách hàng không tồn tại"),
    CUSTOMER_EXIST("CUSTOMER_EXIST", "Khách hàng tồn tại");
    private final String message;
    private final String code;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
