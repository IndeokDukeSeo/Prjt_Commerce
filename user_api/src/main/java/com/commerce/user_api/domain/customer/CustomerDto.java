package com.commerce.user_api.domain.customer;

import com.commerce.user_api.domain.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomerDto {
    private Long id;
    private String email;
    private Integer balance;


    public static CustomerDto from(Customer customer) {
        return new CustomerDto(customer.getId(), customer.getEmail(),customer.getBalance()==null?0:customer.getBalance());

    }
}
