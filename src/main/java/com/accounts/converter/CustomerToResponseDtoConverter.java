package com.accounts.converter;

import com.accounts.domain.model.Customer;
import com.accounts.request.dto.AccoutDto;
import com.accounts.response.dto.AccountResponse;
import com.accounts.response.dto.CustomerResponse;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Customer to CustomerDto converter
 */
@Component
public class CustomerToResponseDtoConverter {

    
    public static CustomerResponse convert(Customer customer) {

    	AccountResponse accountResponse = AccountResponse.builder()
    			.balance(customer.getBankAccount().getCurrentBalance())
    			.iban(customer.getBankAccount().getIban())
    			.createdAt(customer.getBankAccount().getCreatedAt())
    			.upldatedAt(customer.getBankAccount().getUpdatedAt())
    			.build();
        return CustomerResponse.builder()
            	.cif(customer.getId().toString())
            	.firstName(customer.getFirstName())
            	.lastName(customer.getLastName())
            	.email(customer.getEmail())
            	.createdAt(customer.getCreatedAt())
            	.upldatedAt(customer.getUpdatedAt())
            	.accountResponse(accountResponse)
            		.build();
    }

}
