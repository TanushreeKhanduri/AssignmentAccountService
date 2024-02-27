package com.accounts.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.accounts.converter.BankAccountToAccountResponseDtoConverter;
import com.accounts.converter.BankAccountToCustomerResponseDtoConverter;
import com.accounts.converter.CustomerToResponseDtoConverter;
import com.accounts.domain.model.BankAccount;
import com.accounts.domain.model.Customer;
import com.accounts.request.dto.AccoutDto;
import com.accounts.request.dto.AmountDto;
import com.accounts.response.dto.AccountResponse;
import com.accounts.response.dto.CustomerResponse;
import com.accounts.service.AccountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * This class is created to manage account process
 */
@Api("Account management services")
@RestController
@RequestMapping(AccountsController.SERVICE_PATH)
public class AccountsController {

    public static final String SERVICE_PATH = "api/account";

    @Autowired
    private AccountService accountService;

    @ApiOperation(value = "Create a new account")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request."),
            @ApiResponse(code = 500, message = "Internal Error.")
    })
    @PostMapping(value = "/createAccount")
    @ResponseStatus(value = HttpStatus.OK)
    public CustomerResponse createAccount(@ApiParam(value = "Details of customer") @RequestBody @Valid AccoutDto accoutDto) {
    	Customer customer = accountService.createAccount(accoutDto);
        return CustomerToResponseDtoConverter.convert(customer);
    }


    @ApiOperation(value = "Retrieve account and customer information by given customerId", response = CustomerResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK."),
            @ApiResponse(code = 400, message = "Bad Request."),
            @ApiResponse(code = 500, message = "Internal Error.")
    })
    @GetMapping(value = "/{customerId}")
    public CustomerResponse getAccount(@ApiParam(value = "The ID of the customer") @PathVariable(name = "customerId") Long customerId) {
        
    	BankAccount account = accountService.getAccounts(customerId);
    	
        return BankAccountToCustomerResponseDtoConverter.convert(account);
    }
    
    @ApiOperation(value = "Update Account Balance for a given customer")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request."),
            @ApiResponse(code = 500, message = "Internal Error.")
    })
    @PutMapping(value = "update/{customerId}")
    @ResponseStatus(value = HttpStatus.OK)
    public AccountResponse updateAccount(@ApiParam(value = "The ID of the customer") @PathVariable(name = "customerId") Long customerId, @ApiParam(value = "Details of updated balance") @RequestBody @Valid AmountDto amount) {
        return BankAccountToAccountResponseDtoConverter.convert(accountService.updateAccount(customerId, amount.getAmount()));
    }

}
