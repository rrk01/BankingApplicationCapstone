package com.learning.controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.learning.entity.Account;
import com.learning.entity.AccountStatus;
import com.learning.entity.AccountType;
import com.learning.entity.Beneficiary;
import com.learning.repo.AccountRepo;
import com.learning.service.CustomerService;

@CrossOrigin
@RestController
@RequestMapping("/api/customer")
public class StaffController {
	@Autowired
	CustomerService customerService;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@PutMapping("/{cid}/account/{accountNumber}")
	public Account approveAccount(@PathVariable long cid, @PathVariable long accountNumber) {
		Account account=customerService.findCustomerAccount(cid, accountNumber);

		if(cid==account.getCustomerId()) {
			account.setApproved(true);
		}
		customerService.saveApproval(account);
		return new Account(null, null,BigDecimal.ZERO, account.isApproved(), account.getAccountNumber(),
				null, 0);
	}
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@GetMapping("/{customerId}/account")

	public List<Account> getAllAccounts(@PathVariable long customerId){
		List<Account> accountsList=customerService.findAllCustomerAccount(customerId);
		List<Account> accountsreturnlist=new ArrayList<>();
		for(int i=0;i<accountsList.size();i++) {
			accountsreturnlist.add(new Account(accountsList.get(i).getAccountType(), accountsList.get(i).getAccountStatus(),
					accountsList.get(i).getAccountBalance(), false, accountsList.get(i).getAccountNumber(), null, 0));
		}
		return accountsreturnlist;
	}

}
