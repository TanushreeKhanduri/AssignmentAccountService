package com.accounts.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.accounts.converter.AccountDtoToCustomerConverter;
import com.accounts.domain.model.BankAccount;
import com.accounts.domain.model.Customer;
import com.accounts.exception.BankAccountManagerException;
import com.accounts.exception.InsufficientBalanceManagerException;
import com.accounts.repository.BankAccountRepository;
import com.accounts.repository.CustomerRepository;
import com.accounts.request.dto.AccoutDto;
import com.accounts.util.IBANGenerator;
import com.google.common.base.Preconditions;

/**
 * Customer management service
 */
@Service
public class AccountService {

    private static final String MESSAGE_FORMAT_NO_CUSTOMER = "No customer by customerId: %d";

    private BankAccountRepository accountsRepository;
    private CustomerRepository customerRepository;
    
    public AccountService(BankAccountRepository accountsRepository, CustomerRepository customerRepository) {
        this.accountsRepository = accountsRepository;
        this.customerRepository = customerRepository;
    }

    public BankAccount getAccounts(Long customerId) {
    	BankAccount ba = accountsRepository.findAllByCustomerId(customerId);
    	if (null == ba) {
    		throw new BankAccountManagerException("No customer found by given cif");
    	}
        return ba;
    }
    
    public Customer getCustomerInfo(Long customerId) {
    	Preconditions.checkNotNull(customerId, MESSAGE_FORMAT_NO_CUSTOMER, customerId);
        return customerRepository.findById(customerId)
                .orElseThrow(() -> BankAccountManagerException.to(MESSAGE_FORMAT_NO_CUSTOMER, customerId));
    }

    public Customer createAccount(AccoutDto account) {
    	
        Preconditions.checkNotNull(account, "account information cannot be null");
        Preconditions.checkNotNull(account.getFirstName(), "customer first name can not be null");
     
        //Conditions depends on requirement on what is mandatory and unique . 
        //For assignment purpose it is assumed email id is mandatory and unique
        if (customerRepository.findAllByEmail(account.getEmail()).isEmpty()){
        	  
        	Customer customer = AccountDtoToCustomerConverter.convert(account);
        	Customer savedCustomer = customerRepository.save(customer);
        	
        	BankAccount bankAccount = new BankAccount();
        	bankAccount.setCustomer(savedCustomer);
        	bankAccount.setIban(IBANGenerator.generateIBAN());
        	bankAccount.setCurrentBalance(account.getOpeningBalance());
            BankAccount createdAccount = accountsRepository.save(bankAccount);
            
            savedCustomer.setBankAccount(createdAccount);
            return savedCustomer;
            
        } else {
        	throw new BankAccountManagerException("Customer already Exists");
        }  
    }
    
    public BankAccount updateAccount(Long customerId, BigDecimal amount) {
    	BankAccount savedAccount = accountsRepository.findAllByCustomerId(customerId);
    	if (null == savedAccount) {
    		throw new BankAccountManagerException("Customer does not exist");
    	}
    	BigDecimal newBalance = savedAccount.getCurrentBalance().add(amount);
    	if (newBalance.compareTo(new BigDecimal(0))<0) {
    		throw new InsufficientBalanceManagerException("Insufficient Balance");
    	}
    	savedAccount.setCurrentBalance(newBalance);
       return accountsRepository.saveAndFlush(savedAccount);
    }
}

