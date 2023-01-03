package com.commerce.user_api.client.config;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Qualifier(value = "mailgun")
    @Bean
    public BasicAuthRequestInterceptor basicAuthenticationInterceptor() {
        return new BasicAuthRequestInterceptor("api", "0236c1d51e3e45dae203c1578b1dfa06-c2efc90c-c88cb68b");
    }

}
