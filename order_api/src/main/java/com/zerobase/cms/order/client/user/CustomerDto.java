package com.zerobase.cms.order.client.user;

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

}
