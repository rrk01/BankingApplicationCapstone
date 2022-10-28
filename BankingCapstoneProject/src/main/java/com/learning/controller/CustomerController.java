package com.learning.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.entity.Account;
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
	public Account createCustomerAccount(@PathVariable long id,@RequestBody Account account) {
		return customerService.createCustomerAccount(id, account);
	}
	@PutMapping("/{id}")
	public Customer updateCustomer(@RequestBody Customer customer, @PathVariable("id") long id) {
		return customerService.updateCustomer(customer,id);
	}

}
