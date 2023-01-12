package com.zerobase.cms.order.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    ALREADY_REGISTERED_USER(HttpStatus.BAD_REQUEST,"이미 가입된 회원입니다."),
    NOT_ENOUGH_BALANCE(HttpStatus.BAD_REQUEST,"잔액이 부족합니다.")
    ;

    private final HttpStatus httpStatus;
    private final String detail;

}
