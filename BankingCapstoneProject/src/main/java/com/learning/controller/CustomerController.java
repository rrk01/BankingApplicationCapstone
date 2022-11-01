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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.learning.entity.Account;
import com.learning.entity.AccountType;
import com.learning.entity.Beneficiary;
import com.learning.entity.Customer;
import com.learning.entity.Beneficiary.accounttype;
import com.learning.repo.CustomerRepo;
import com.learning.service.CustomerService;

@RestController
@RequestMapping("api/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@PostMapping("/register")
	public Customer registerCustomer(@RequestBody Customer customer) {
		 customerService.registerCustomer(customer);
		 return new Customer(customer.getId(), 0, customer.getUserName(), customer.getFullName(), customer.getPassword(), null,null, null);
	}
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@PostMapping("/{id}/account")
	public Account createCustomerAccount(@Valid @PathVariable long id,@RequestBody Account account) {
		/*accountType:Enum(SB/CA), accountBalance:Number, approved: String -no (default)*/
		customerService.createCustomerAccount(id, account);
		return new Account(account.getAccountType(), null ,account.getAccountBalance(), account.isApproved(), 0,
				null, 0);
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@GetMapping("/{id}")
	public Customer getCustomer(@Valid @PathVariable("id") long id) {
		Customer customer=customerService.findCustomerById(id);
		return new Customer(0, customer.getSsn(), customer.getUserName(), customer.getFullName(), null, customer.getPhone(),null, null);
	}
	
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@PutMapping("/{id}")
	public Customer updateCustomer(@Valid @RequestBody Customer customer, @PathVariable("id") long id) {
		customerService.updateCustomer(customer,id);
		Customer updatedcustomer=customerService.findCustomerById(id);
		return new Customer(updatedcustomer.getId(), customer.getSsn(), null, customer.getFullName(), null, customer.getPhone()
				,updatedcustomer.getSecretQuestion(), updatedcustomer.getSecretAnswer());
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@GetMapping("/{custID}/account/{acctID}")
	public Account getCustomerAccount(@Valid @PathVariable("custID") long custID, @PathVariable("acctID") long acctID) {
		/*accountNumber: Integer, accountType:Enum(SB/CA), accountBalance:Number, accountStatus:Enum(Enable/Disable)*/
		Account account=customerService.findCustomerAccount(acctID);
		return new Account(account.getAccountType(), account.getAccountStatus() ,account.getAccountBalance(), account.isApproved(), account.getAccountNumber(),
				null, 0);
	}
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@PostMapping("/{custID}/beneficiary")
	public Beneficiary addBeneficiary(@Valid @RequestBody Beneficiary beneficiary, @PathVariable("custID") long custID) {
		customerService.addBeneficiary(beneficiary,custID);
		return new Beneficiary(0, beneficiary.getAccountNumber(), beneficiary.getAccountType(), null,
				beneficiary.getApproved(), null,null);
	}
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@GetMapping("/{custID}/beneficiary")
	public List<Beneficiary> getBeneficiary(@Valid @RequestBody Beneficiary beneficiary, @PathVariable("custID") long custID) {
		List<Beneficiary> beneficiarieslist=customerService.getBeneficiary(beneficiary, custID);
		List<Beneficiary> beneficiariesreturnlist=new ArrayList<>();
		for(int i=0;i<beneficiarieslist.size();i++) {
			beneficiariesreturnlist.add(new Beneficiary(0, beneficiarieslist.get(i).getAccountNumber(), null,
					beneficiarieslist.get(i).getBeneficiaryName(),
					null, null,beneficiarieslist.get(i).getStatus()));
		}
		return beneficiariesreturnlist;

	}
	@DeleteMapping("/{custID}/beneficiary/{beneficiaryID}")
	public int deleteBeneficiary(@Valid @PathVariable("beneficiaryID") long beneficiaryID, @PathVariable("custID") long custID) {
		return customerService.deleteBeneficiary(beneficiaryID,custID);
	}

}
