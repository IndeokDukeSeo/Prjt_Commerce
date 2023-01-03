package com.commerce.user_api.client.service;

import com.commerce.user_api.client.domain.SignUpForm;
import com.commerce.user_api.client.domain.model.Customer;
import com.commerce.user_api.client.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpCustomerService {

    private final CustomerRepository customerRepository;

    public Customer signUp(SignUpForm form) {
        return customerRepository.save(Customer.from(form));
    }
}
