package com.accounts.converter;

import com.accounts.domain.model.BankAccount;
import com.accounts.response.dto.AccountResponse;
import com.accounts.response.dto.CustomerResponse;

public class BankAccountToCustomerResponseDtoConverter {

	public static CustomerResponse convert(BankAccount account) {

     
        AccountResponse accountResponse = AccountResponse.builder()
    			.balance(account.getCurrentBalance())
    			.iban(account.getIban())
    			.createdAt(account.getCreatedAt())
    			.upldatedAt(account.getUpdatedAt())
    			.build();
        
        return CustomerResponse.builder()
    	.cif(account.getCustomer().getId().toString())
    	.firstName(account.getCustomer().getFirstName())
    	.lastName(account.getCustomer().getLastName())
    	.email(account.getCustomer().getEmail())
    	.createdAt(account.getCustomer().getCreatedAt())
    	.upldatedAt(account.getCustomer().getUpdatedAt())
    	.accountResponse(accountResponse)
    		.build();
    }
}
