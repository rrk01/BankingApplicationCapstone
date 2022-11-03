package com.learning.controller;

import java.util.List;
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
import com.learning.entity.CustomerStatus;
import com.learning.repo.AccountRepo;
import com.learning.repo.BeneficiaryRepo;
import com.learning.repo.CustomerRepo;
import com.learning.repo.StaffRepo;
import com.learning.service.CustomerService;
import com.learning.service.StaffService;

enum status {
	ENABLE, DISABLE
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
		Account account = customerService.findCustomerAccount(id, accountNumber);
		if (id == account.getCustomerId()) {
			account.setApproved(true);
		}
		customerService.saveApproval(account);
		return account;
	}

	@GetMapping("/{customerId}/account")
	public List<Account> getAllAccounts(@PathVariable long customerId) {
		return customerService.findAllCustomerAccount(customerId);
	}

	@GetMapping("/account/{accountNumber}") // GET the statement of particular (transactions) account
	public Account getParticularAccount(@PathVariable("accountNumber") long accountNumber) {
		return staffService.getParticularAccount(accountNumber);
	}

//	@GetMapping("/beneficiary") // GETS beneficiary that need to be approved
//	public List<Beneficiary> getAllBeneficiary() {
//		return staffService.getAllBeneficiary();
//	}

//	------------------------------------------------------------------------------------------
	// TEST CALL
//	@GetMapping("/beneficiaryApproved") // GETS beneficiary that need to be approved (TESTING PURPOSE TO SEE IF they got
										// approved)
//	public List<Beneficiary> getAllBeneficiaryApproved() {
//		return staffService.getAllBeneficiaryApproved();
//	}
//
//	@PutMapping("/beneficiary/{beneficiaryAcNo}/{customerId}") // APPROVES THE BENE Which were added by Customer
//	public ResponseEntity<Beneficiary> updateBeneficiary(@PathVariable("beneficiaryAcNo") long beneficiaryAcNo,
//			@PathVariable("customerId") long customerId) {
//		Beneficiary updateBeneficiary = beneficiaryRepo.findBeneficiary(beneficiaryAcNo, customerId)
//				.orElseThrow(() -> new RuntimeException("Beneficiary Not exisit with id: " + beneficiaryAcNo));
//		updateBeneficiary.setApproved(true);
//		beneficiaryRepo.save(updateBeneficiary);
//		return ResponseEntity.ok(updateBeneficiary);
//	}

//	------------------------------------------------------------------------------------------
//	@GetMapping("/accounts/approved")
//	public List<Account> getAccountsNotApproved() {
//		return staffService.getAccountsNotApproved();
//	}

//	@PutMapping("/accounts/approved/{accountNumber}/{customerId}")
//	public ResponseEntity<Account> updateAccountType(@PathVariable("accountNumber") long accountNumber,
//			@PathVariable("customerId") long customerId) {
//		Account updateAccountType = staffRepo.getParticularAccountType(accountNumber, customerId)
//				.orElseThrow(() -> new RuntimeException("Account Not exisit with id: " + accountNumber));
//
//		updateAccountType.setApproved(true);
//		staffRepo.saveAccount(updateAccountType.isApproved());
//		return ResponseEntity.ok(updateAccountType);
//	}

//	@GetMapping("/customer")
//	public List<Customer> getCustomer() {
//		return staffService.getCustomer();
//	}

//	@PutMapping("/customer/enable/{customerId}")
//	public ResponseEntity<Customer> updateCustomerEnable(@PathVariable("customerId") long customerId) {
//		Customer updateCustomer = customerRepo.findById(customerId)
//				.orElseThrow(() -> new RuntimeException("Customer Not exisit with id: " + customerId));
//
//		updateCustomer.setStatus(CustomerStatus.ENABLE);
//		staffRepo.saveCustomerEnable(customerId, updateCustomer.getStatus());
//		return ResponseEntity.ok(updateCustomer);
//	}

//	@PutMapping("/customer/disable/{customerId}")
//	public ResponseEntity<Customer> updateCustomerDisable(@PathVariable("customerId") long customerId) {
//		Customer updateCustomer = customerRepo.findById(customerId)
//				.orElseThrow(() -> new RuntimeException("Customer Not exisit with id: " + customerId));
//
//		updateCustomer.setStatus(CustomerStatus.DISABLE);
//		staffRepo.saveCustomerDisable(customerId, updateCustomer.getStatus());
//		return ResponseEntity.ok(updateCustomer);
//	}
//
//	@GetMapping("/customer/{customerId}")
//	public Customer getCustomerById(@PathVariable("customerId") long customerId) {
//		return staffService.getCustomerById(customerId);
//	}

	@Autowired
	AccountRepo accountRepo;

//	@PutMapping("/transfer/{fromAccNumber}")
//	public ResponseEntity<Account> updateAccountBalance(@PathVariable("fromAccNumber") long fromAccNumber,
//			@RequestBody Account accountDetails) {
//		Account updateAccountBalance = accountRepo.findById(fromAccNumber)
//				.orElseThrow(() -> new RuntimeException("Account Not exisit with id: " + fromAccNumber));
//		
//		Account updateAccountBalance_2 = accountRepo.findById(accountDetails.getAccountNumber())
//				.orElseThrow(() -> new RuntimeException("Account Not exisit with id: " + fromAccNumber));
//
//		updateAccountBalance.setAccountBalance(updateAccountBalance.getAccountBalance().subtract(accountDetails.getAccountBalance()));
//		updateAccountBalance_2.setAccountBalance(accountDetails.getAccountBalance().add(updateAccountBalance.getAccountBalance()));
//		
//		staffRepo.updateAccountBalance(updateAccountBalance);
//		staffRepo.updateAccountBalance_2(updateAccountBalance_2);
//		return ResponseEntity.ok(updateAccountBalance);
//	}
}
