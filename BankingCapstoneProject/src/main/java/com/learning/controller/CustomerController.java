package com.learning.controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;
import com.learning.entity.Account;
import com.learning.entity.AccountStatus;
import com.learning.entity.AccountType;

import com.learning.entity.Beneficiary;
import com.learning.entity.Customer;
import com.learning.entity.Transfer;
import com.learning.repo.AccountRepo;
import com.learning.repo.CustomerRepo;
import com.learning.service.CustomerService;

@CrossOrigin
@RestController
@RequestMapping("api/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	@Autowired
	AccountRepo accountRepo;
	
	@GetMapping("/getcustomer")
	public List<Customer> getCustomers() {
		return customerService.getCustomers();
	}
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@PostMapping("/register")
	public Customer registerCustomer(@RequestBody Customer customer) {
		 customerService.registerCustomer(customer);
		 return new Customer(customer.getId(), 0, customer.getUserName(), customer.getFullName(), customer.getPassword(), null,null, null, null);
	}
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@PostMapping("/{id}/account")
	public Account createCustomerAccount(@Valid @PathVariable long id,@RequestBody Account account) {
		/*accountType:Enum(SB/CA), accountBalance:Number, approved: String -no (default)*/
		customerService.createCustomerAccount(id, account);
		return new Account(0, 0, account.getAccountType(), null, account.getAccountBalance(), account.isApproved(), null);
	
	}

	
	@GetMapping("/{id}")
	public Customer getCustomer(@Valid @PathVariable("id") long id) {
		return customerService.findCustomerById(id);
	}
	
	/*@GetMapping("/getcustomer")
	public List<Customer> getCustomers() {
		return customerService.getCustomers();

	}*/
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@PutMapping("/{id}")
	public Customer updateCustomer(@Valid @RequestBody Customer customer, @PathVariable("id") long id) {
		customerService.updateCustomer(customer,id);
		Customer updatedcustomer=customerService.findCustomerById(id);
		return new Customer(updatedcustomer.getId(), customer.getSSN(), null, customer.getFullName(), null, customer.getPhone()
				,updatedcustomer.getSecretQuestion(), updatedcustomer.getSecretAnswer(), null);
	}

	@GetMapping("/{custID}/account/{acctID}")
	public Account getCustomerAccount(@Valid @PathVariable("custID") long custID, @PathVariable("acctID") long acctID) {

		return customerService.findCustomerAccount(custID,acctID);
	}

	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@PostMapping("/{custID}/beneficiary")
	public Beneficiary addBeneficiary(@Valid @RequestBody Beneficiary beneficiary, @PathVariable("custID") long custID) {
		customerService.addBeneficiary(beneficiary,custID);
		return new Beneficiary(0, beneficiary.getAccountNumber(),0, beneficiary.getAccountType(), null,
				beneficiary.getApproved(), false,null);
	}
	
	@GetMapping("/{custID}/beneficiary")
	public List<Beneficiary> getBeneficiary(@Valid @PathVariable("custID") long custID) {
		return customerService.getBeneficiary(custID);

	}
  
	@DeleteMapping("/{custID}/beneficiary/{beneficiaryID}")
	public String deleteBeneficiary(@Valid @PathVariable("beneficiaryID") long beneficiaryID, @PathVariable("custID") long custID) {
		return customerService.deleteBeneficiary(beneficiaryID,custID);
	}
	
	// TODO - CUSTOMER TRANSFER (?)
	@PutMapping("/transfer")
	public ResponseEntity<Account> updateAccountBalance(@RequestBody Transfer transfer) {
		Account updateAccountBalance = accountRepo.findById(transfer.getFromAccount())
				.orElseThrow(() -> new RuntimeException("Account Not exisit with id: " + transfer.getFromAccount()));
		
		Account updateAccountBalance_2 = accountRepo.findById(transfer.getToAccount())
				.orElseThrow(() -> new RuntimeException("Account Not exisit with id: " + transfer.getToAccount()));

		updateAccountBalance.setAccountBalance(updateAccountBalance.getAccountBalance().subtract(transfer.getAmount()));
		updateAccountBalance_2.setAccountBalance(updateAccountBalance_2.getAccountBalance().add(transfer.getAmount()));
		
		accountRepo.save(updateAccountBalance);
		accountRepo.save(updateAccountBalance_2); 
		return ResponseEntity.ok(updateAccountBalance);
	}
}