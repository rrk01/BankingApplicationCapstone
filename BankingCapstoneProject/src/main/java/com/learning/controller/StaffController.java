package com.learning.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.entity.Account;
import com.learning.repo.AccountRepo;
import com.learning.service.CustomerService;

@CrossOrigin
@RestController
@RequestMapping("/api/customer")
public class StaffController {
	@Autowired
	CustomerService customerService;
	@PutMapping("/{custId}/account/{accountNumber}")
	public Account approveAccount(@PathVariable("custId") long id, @PathVariable("accountNumber") long accountNumber) {
		Account account=customerService.findCustomerAccount(id, accountNumber);
		if(id==account.getCustomerId()) {
			account.setApproved(true);
		}
		customerService.saveApproval(account);
		return account;
	}
	@GetMapping("/{customerId}/account")
	public List<Account> getAllAccounts(@PathVariable long customerId){
		return customerService.findAllCustomerAccount(customerId);
	}

}
