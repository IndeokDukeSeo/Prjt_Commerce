package com.zerobase.cms.order.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    NOT_FOUND_PRODUCT(HttpStatus.BAD_REQUEST,"상품을 찾을 수 없습니다."),
    EXISTING_ITEM(HttpStatus.BAD_REQUEST,"중복된 상품명 입니다.")
    ;

    private final HttpStatus httpStatus;
    private final String detail;

}
