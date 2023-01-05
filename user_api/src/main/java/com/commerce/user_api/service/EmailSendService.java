//package com.commerce.user_api.service;
//
//import com.commerce.user_api.client.MailgunClient;
//import com.commerce.user_api.client.mailgun.SendMailForm;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class EmailSendService {
//    private final MailgunClient mailgunClient;
//
//    public String sendEmail() {
//
//        SendMailForm form = SendMailForm.builder()
//                .from("Test@Email.com")
//                .to("dukeindream@gmail.com")
//                .subject("this is a test")
//                .text("this is text for the test")
//                .build();
//
//        return mailgunClient.sendEmail(form).getBody();
//    }
//
//
//}
