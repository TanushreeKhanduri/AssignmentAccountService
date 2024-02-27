package com.accounts.converter;

import org.springframework.stereotype.Component;

import com.accounts.domain.model.BankAccount;
import com.accounts.response.dto.AccountResponse;

/**
 * Customer to CustomerDto converter
 */
@Component
public class BankAccountToAccountResponseDtoConverter {

    
    public static AccountResponse convert(BankAccount account) {

        return AccountResponse.builder()
            	.iban(account.getIban())
            	.balance(account.getCurrentBalance())
            	.createdAt(account.getCreatedAt())
            	.upldatedAt(account.getUpdatedAt())
            		.build();
    }

}
