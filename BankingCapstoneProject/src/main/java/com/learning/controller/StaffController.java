package com.learning.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.entity.Account;
import com.learning.entity.Beneficiary;
import com.learning.entity.Customer;
import com.learning.entity.Staff;
import com.learning.repo.AccountRepo;
import com.learning.repo.BeneficiaryRepo;
import com.learning.repo.CustomerRepo;
import com.learning.repo.StaffRepo;
import com.learning.service.CustomerService;
import com.learning.service.StaffService;

enum status {
	ENABLE,DISABLE
}

@RestController
@RequestMapping("/api/staff")
public class StaffController {
	
	@Autowired
	StaffService staffService;
	
	@Autowired
	BeneficiaryRepo beneficiaryRepo;
	@Autowired
	StaffRepo staffRepo;
	@Autowired
	CustomerRepo customerRepo;
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
	
	@GetMapping("/account/:accountNumber") // GET the statement of particular account
	public Account getParticularAccount(@PathVariable("accountNumber") long acountNumber) {
		return staffService.getParticularAccount();
	}
	
	@GetMapping("/beneficiary") // GETS beneficiary that need to be approved
	public List<Beneficiary> getAllBeneficiary() {
		return staffService.getAllBeneficiary(); 
	}
	
//	------------------------------------------------------------------------------------------
	// TEST CALL
	@GetMapping("/beneficiaryApproved") // GETS beneficiary that need to be approved (TESTING PURPOSE TO SEE IF they got approved)
	public List<Beneficiary> getAllBeneficiaryApproved() {
		return staffService.getAllBeneficiaryApproved(); 
	}
	
	@PutMapping("/beneficiary/{customerId}") // APPROVES THE BENE Which were added by Customer
	public ResponseEntity<Beneficiary> updateBeneficiary(@PathVariable("customerId") long customerId, @RequestBody Beneficiary beneficiaryDetails) {
		Beneficiary updateBeneficiary = beneficiaryRepo.findById(customerId).orElseThrow(() -> 
		new RuntimeException("Beneficiary Not exisit with id: " + customerId)); 
		updateBeneficiary.setApproved(true);
		beneficiaryRepo.save(updateBeneficiary);
		return ResponseEntity.ok(updateBeneficiary); 
	}
//	------------------------------------------------------------------------------------------
	@GetMapping("/accounts/approved")
	public List<Account> getAccountsNotApproved() {
		return staffService.getAccountsNotApproved();
	}
	
	@PutMapping("/accounts/approved")
	Account updateAccount(@RequestBody Account accountDetails) {
		return staffRepo.save(accountDetails);
	}
	
	@GetMapping("/customer")
	public List<Customer> getCustomer(){
		return staffService.getCustomer();
	}
	
	@PutMapping("/customer/{customerId}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable("customerId") long customerId, @RequestBody Customer customerDetails) {
		Customer updateCustomer = customerRepo.findById(customerId).orElseThrow(() -> 
		new RuntimeException("Customer Not exisit with id: " + customerId)); 
		
//		Staff staff = new Staff();
//		updateCustomer
	
//		staffRepo.save(updateCustomer);
		return ResponseEntity.ok(updateCustomer); 
	}
	
	@GetMapping("/customer/{customerId}")
	public Customer getCustomerById(@PathVariable("customerId") long customerId) {
		return staffService.getCustomerById(customerId);
	}
	
//	@PutMapping("/transfer/{}")
//	public 
	
}
