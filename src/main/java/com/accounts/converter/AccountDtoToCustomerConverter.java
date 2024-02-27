package com.accounts.converter;


import org.springframework.stereotype.Component;

import com.accounts.domain.model.Customer;
import com.accounts.request.dto.AccoutDto;

/**
 * CustomerDto to Customer converter
 */
@Component
public class AccountDtoToCustomerConverter{

    
    public static Customer convert(AccoutDto accoutDto) {

        return Customer.builder()
                .firstName(accoutDto.getFirstName())
                .lastName(accoutDto.getLastName())
                .email(accoutDto.getEmail())
                .build();
    }

}
