package com.learning.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.learning.entity.Account;
import com.learning.entity.Customer;
import com.learning.repo.AccountRepo;
import com.learning.repo.CustomerRepo;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepo customerRepo;
	@Autowired
	private AccountRepo accountRepo;
	
	public Customer registerCustomer(Customer customer) {
		return customerRepo.save(customer);
	}
	public Account createCustomerAccount(long id,Account account) {
		account.setCustomerId(id);
		return accountRepo.save(account);
	}
	public Account saveApproval(Account account) {
		return accountRepo.save(account);
	}
	public Account findCustomerAccount(long accountNumber) {
		Optional<Account> accountObject=accountRepo.findById(accountNumber);
		return accountObject.get();
	}
	public List<Account> findAllCustomerAccount(long accountNumber) {
		return accountRepo.findAll();
	}
}
