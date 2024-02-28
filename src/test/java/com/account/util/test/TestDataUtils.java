package com.account.util.test;


import com.accounts.domain.model.BankAccount;
import com.accounts.domain.model.Customer;
import com.accounts.request.dto.*;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.math.BigDecimal;

public class TestDataUtils {


    public static Customer getCustomer1() {
        return Customer.builder()
                .id(1L)
                .firstName("firstName")
                .lastName("lastName")
                .bankAccount(getBankAccount1())
                .build();
    }

   
    public static BankAccount getBankAccount1() {
    	BigDecimal b= new BigDecimal(10);
        return BankAccount.builder()
        		.id(1L)
                .iban("IBAN1")
                .currentBalance(b)
                .customer(new Customer())
                .build();
    }

  
    public static AccoutDto getBankAccountDto1() {
    	BigDecimal b= new BigDecimal(10);
        return AccoutDto.builder()
                .firstName("firstName")
                .lastName("lastName")
        		.email("t.k@gmail.com")
        		.openingBalance(b)
                .build();
    }


}
