package com.learning.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.entity.Account;
import com.learning.repo.AccountRepo;
import com.learning.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class StaffController {
	@Autowired
	CustomerService customerService;
	@Autowired
	AccountRepo accountRepo;
	@PutMapping("/{id}/account/{accountNumber}")
	public Account approveAccount(@PathVariable long id, @PathVariable long accountNumber) {
		Account account=customerService.findCustomerAccount(accountNumber);
		account.setApproved("Yes");
		customerService.saveApproval(account);
		return account;
	}

}
