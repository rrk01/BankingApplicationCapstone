package com.learning.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.entity.Account;
import com.learning.entity.Beneficiary;
import com.learning.entity.Customer;
import com.learning.repo.CustomerRepo;
import com.learning.service.CustomerService;

@RestController
@RequestMapping("api/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	@PostMapping("/register")
	public Customer registerCustomer(@RequestBody Customer customer) {
		return customerService.registerCustomer(customer);
	}
	@PostMapping("/{id}/account")
	public Account createCustomerAccount(@Valid @PathVariable long id,@RequestBody Account account) {
		return customerService.createCustomerAccount(id, account);
	}

	
	@GetMapping("/{id}")
	public List<Object> getCustomer(@Valid @PathVariable("id") long id) {
		return customerService.getCustomer(id);
	}
	
	@GetMapping("/getcustomer")
	public List<Customer> getCustomers() {
		return customerService.getCustomers();
	}

	@PutMapping("/{id}")
	public Customer updateCustomer(@Valid @RequestBody Customer customer, @PathVariable("id") long id) {
		return customerService.updateCustomer(customer,id);
	}

	@GetMapping("/{custID}/account/{acctID}")
	public Account getCustomerAccount(@Valid @PathVariable("custID") long custID, @PathVariable("acctID") long acctID) {
		return customerService.findCustomerAccount(acctID);
	}

	@PostMapping("/{custID}/beneficiary")
	public Beneficiary addBeneficiary(@Valid @RequestBody Beneficiary beneficiary, @PathVariable("custID") long custID) {
		return customerService.addBeneficiary(beneficiary,custID);
	}
	@GetMapping("/{custID}/beneficiary")

	public List<Beneficiary> getBeneficiary(@Valid @RequestBody Beneficiary beneficiary, @PathVariable("custID") long custID) {
		return customerService.getBeneficiary(beneficiary, custID);

	}
	@DeleteMapping("/{custID}/beneficiary/{beneficiaryID}")
	public int deleteBeneficiary(@Valid @PathVariable("beneficiaryID") long beneficiaryID, @PathVariable("custID") long custID) {
		return customerService.deleteBeneficiary(beneficiaryID,custID);
	}

}
