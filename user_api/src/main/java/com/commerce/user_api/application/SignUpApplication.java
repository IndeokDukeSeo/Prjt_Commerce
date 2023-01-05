package com.commerce.user_api.application;

import com.commerce.user_api.client.MailgunClient;
import com.commerce.user_api.client.mailgun.SendMailForm;
import com.commerce.user_api.domain.SignUpForm;
import com.commerce.user_api.domain.model.Customer;
import com.commerce.user_api.exception.CustomException;
import com.commerce.user_api.exception.ErrorCode;
import com.commerce.user_api.service.SignUpCustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
@Service
@Slf4j
@RequiredArgsConstructor
public class SignUpApplication {
    private final MailgunClient mailgunClient;
    private final SignUpCustomerService signUpCustomerService;

    public void customerVerify(String email, String code){
        signUpCustomerService.verifyEmail(email,code);
    }
    public String customerSignUp(SignUpForm form) {
        if (signUpCustomerService.isEmailExist(form.getEmail())) {
            //exception
            throw new CustomException(ErrorCode.ALREADY_REGISTERED_USER);
        } else {
            Customer c = signUpCustomerService.signUp(form);
            String code = getRandomCode();
            SendMailForm sendMailForm = SendMailForm.builder()
                    .from("tester@testserver.com")
                    .to("dukeindream@gmail.com")
                    .subject("Verification Email")
                    .text(getVerificationEmailBody(form.getEmail(), form.getName(), code))
                    .build();

            log.info("Send email result : " + mailgunClient.sendEmail(sendMailForm).getBody());
            signUpCustomerService.changeCustomerValidateEmail(c.getId(),code);
            return "회원 가입에 성공하였습니다.";
        }

    }
    private String getRandomCode() {
        return RandomStringUtils.random(10, true, true);
    }
    private String getVerificationEmailBody(String email, String name, String code) {
        StringBuilder builder = new StringBuilder();
        return builder.append("Hello ").append(name).append("! Plese Click Link for verification.\n\n")
                .append("http://localhost:8081/customer/signup/verify?email=")
                .append(email)
                .append("&code=")
                .append(code)
                .toString();
    }


}
