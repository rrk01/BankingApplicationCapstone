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
import com.learning.repo.AccountRepo;
import com.learning.repo.BeneficiaryRepo;
import com.learning.service.CustomerService;
import com.learning.service.StaffService;

@RestController
@RequestMapping("/api/staff")
public class StaffController {
	
	@Autowired
	StaffService staffService;
	
	@Autowired
	BeneficiaryRepo beneficiaryRepo;
	
	@Autowired
	CustomerService customerService;
	@PutMapping("/{id}/account/{accountNumber}")
	public Account approveAccount(@PathVariable long id, @PathVariable long accountNumber) {
		Account account=customerService.findCustomerAccount(accountNumber);
		if(id==account.getCustomerId()) {
			account.setApproved(true);
		}
		customerService.saveApproval(account);
		return account;
	}
	@GetMapping("/{customerId}/acocunt")
	public List<Account> getAllAccounts(@PathVariable long customerId){
		return customerService.findAllCustomerAccount(customerId);
	}
	
	@GetMapping("/beneficiary")
	public List<Beneficiary> getAllBeneficiary() {
		return staffService.getAllBeneficiary(); 
	}
	
	@PutMapping("/beneficiary/{customerId}")
	public ResponseEntity<Beneficiary> updateBeneficiary(@PathVariable("customerId") long customerId, @RequestBody Beneficiary beneficiaryDetails) {
		Beneficiary updateBeneficiary = beneficiaryRepo.findById(customerId).orElseThrow(() -> 
		new RuntimeException("Beneficiary Not exisit with id: " + customerId)); 
		
		updateBeneficiary.setApproved(true);
		
		beneficiaryRepo.save(updateBeneficiary);
		
		return ResponseEntity.ok(updateBeneficiary); 
	}
	
}
