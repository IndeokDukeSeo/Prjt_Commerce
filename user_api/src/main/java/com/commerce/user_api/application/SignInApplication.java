package com.commerce.user_api.application;

import com.commerce.user_api.controller.SignInForm;
import com.commerce.user_api.domain.model.Customer;
import com.commerce.user_api.exception.CustomException;
import com.commerce.user_api.exception.ErrorCode;
import com.commerce.user_api.service.CustomerService;
import com.zerobase.domain.config.JwtAuthenticationProvider;
import com.zerobase.domain.domain.common.UserType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignInApplication {
    private final CustomerService customerService;
    private final JwtAuthenticationProvider provider;

    public String customerLogInToken(SignInForm form) {
        //1. 로그인 가능여부 확인
        Customer c = customerService.findValidCustomer(form.getEmail(),form.getPassword())
                .orElseThrow(()-> new CustomException(ErrorCode.LOGIN_CHECK_FAIL));
        //2. 토큰발행


        //3. 토큰 response.
        return provider.createToken(c.getEmail(),c.getId(), UserType.CUSTOMER);

    }
}
