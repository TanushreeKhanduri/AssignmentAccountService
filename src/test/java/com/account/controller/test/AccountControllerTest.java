package com.account.controller.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;

import com.account.util.test.TestDataUtils;
import com.accounts.controller.AccountsController;
import com.accounts.domain.model.Customer;
import com.accounts.request.dto.AccoutDto;
import com.accounts.response.dto.CustomerResponse;
import com.accounts.service.AccountService;

@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {

    @Mock
    private AccountService bankAccountService;
   
    @Mock
    private ConversionService conversionService;

    @InjectMocks
    private AccountsController bankAccountController;



    @Test
    public void testSaveAccount() throws Exception {
    	assertNotNull(bankAccountService);

    	Customer cust = TestDataUtils.getCustomer1();
    	AccoutDto bankAccountDto = TestDataUtils.getBankAccountDto1();

        Mockito.when(
        		bankAccountService
                .createAccount(bankAccountDto))
                .thenReturn(cust);
        
        CustomerResponse responseEntity = bankAccountController.createAccount(bankAccountDto);
        assertEquals("1", responseEntity.getCif());
        
    }

}
