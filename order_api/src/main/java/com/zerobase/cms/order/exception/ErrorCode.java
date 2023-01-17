package com.zerobase.cms.order.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    NOT_FOUND_PRODUCT(HttpStatus.BAD_REQUEST,"상품을 찾을 수 없습니다."),
    NOT_FOUND_ITEM(HttpStatus.BAD_REQUEST,"아이템을 찾을 수 없습니다."),
    EXISTING_ITEM(HttpStatus.BAD_REQUEST,"중복된 상품명 입니다."),
    CART_ADDING_FAIL(HttpStatus.BAD_REQUEST,"장바구니에 추가할 수 없습니다."),
    ITEM_COUNT_NOT_ENOUGH(HttpStatus.BAD_REQUEST,"상품의 수량이 부족합니다."),
    ORDER_FAILED(HttpStatus.BAD_REQUEST,"주문 실패. 장바구니를 확인해주세요."),
    ORDER_FAILED_NO_MONEY(HttpStatus.BAD_REQUEST,"주문 실패. 잔액이 부족합니다..")
    ;

    private final HttpStatus httpStatus;
    private final String detail;

}
