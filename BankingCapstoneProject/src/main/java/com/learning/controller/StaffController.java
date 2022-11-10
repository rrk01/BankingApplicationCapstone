package com.learning.controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.entity.Account;
import com.learning.entity.Beneficiary;
import com.learning.entity.Customer;
import com.learning.entity.CustomerStatus;
import com.learning.entity.Staff;
import com.learning.entity.Transfer;

/*import com.fasterxml.jackson.annotation.JsonInclude;
import com.learning.entity.Account;
import com.learning.entity.AccountStatus;
import com.learning.entity.AccountType;
import com.learning.entity.Beneficiary;*/

import com.learning.repo.AccountRepo;
import com.learning.repo.BeneficiaryRepo;
import com.learning.repo.CustomerRepo;
import com.learning.repo.StaffRepo;
import com.learning.repo.TransferRepo;
import com.learning.service.CustomerService;
import com.learning.service.StaffService;

enum status {
	ENABLE, DISABLE;
}


@RestController
@CrossOrigin
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
	

	@PutMapping(value="/{custId}/account/{accountNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object>  approveAccount(@PathVariable("custId") long id, @PathVariable("accountNumber") long accountNumber) {
		Account account = customerService.findCustomerAccount(id, accountNumber);
		String jsonString="";
		if (id == account.getCustomerId()) {
			account.setApproved(true);
			LinkedHashMap obj = new LinkedHashMap();
			 try {
				obj.put("accountNumber", account.getAccountNumber());
				obj.put("approved", account.isApproved());
				jsonString=new ObjectMapper().writeValueAsString(obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		customerService.saveApproval(account);
		return ResponseEntity.status(HttpStatus.OK).body(jsonString.toString());
	}

	/*
	 * accountNumber: Integer, accountType:Enum(SB/CA), accountBalance:Number, accountStatus: Enum(Enable/Disable)
	 */
	@GetMapping(value="/{customerId}/account",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAllAccounts(@PathVariable long customerId) {
		customerService.findAllCustomerAccount(customerId);
		List<Account> allaccounts=customerService.findAllCustomerAccount(customerId);
		List<String> newlist=new ArrayList<>();
		String jsonString="";
		LinkedHashMap obj = new LinkedHashMap();
		for(int i=0;i<allaccounts.size();i++) {
			 try {
				obj.put("accountNumber", allaccounts.get(i).getAccountNumber());
				obj.put("accountType", allaccounts.get(i).getAccountType());
				obj.put("accountBalance", allaccounts.get(i).getAccountBalance());
				obj.put("accountStatus", allaccounts.get(i).getAccountStatus());
				jsonString=new ObjectMapper().writeValueAsString(obj);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 newlist.add(jsonString);
		}
		return ResponseEntity.status(HttpStatus.OK).body(newlist.toString()); 
	}

	@GetMapping("/account/{accountNumber}") // GET the statement of particular (transactions) account
	public Account getParticularAccount(@PathVariable("accountNumber") long accountNumber) {
		return accountRepo.getParticularAccount(accountNumber);
	}

	@GetMapping("/beneficiary") // GETS beneficiary that need to be approved
	public List<Beneficiary> getAllBeneficiary() {
		return staffService.getAllBeneficiary();
	}

/*//	------------------------------------------------------------------------------------------
	// TEST CALL
	@GetMapping("/beneficiaryApproved") // (TESTING PURPOSE TO SEE IF they got approved
	public List<Beneficiary> getAllBeneficiaryApproved() {
		return staffService.getAllBeneficiaryApproved();
	}
	
	@PutMapping("/beneficiary/{beneficiaryAcNo}") // APPROVES THE BENE Which were added by Customer
	public ResponseEntity<Beneficiary> updateBeneficiary(@PathVariable("beneficiaryAcNo") long beneficiaryAcNo) {
		Beneficiary updateBeneficiary = beneficiaryRepo.findById(beneficiaryAcNo)
				.orElseThrow(() -> new RuntimeException("Beneficiary Not exisit with id: " + beneficiaryAcNo));
		updateBeneficiary.setApproved(true);
		beneficiaryRepo.save(updateBeneficiary);
		return ResponseEntity.ok(updateBeneficiary);
	}


//	------------------------------------------------------------------------------------------
	// List all the accounts to be approved
//	------------------------------------------------------------------------------------------*/

	@GetMapping("/accounts/approved")
	public List<Account> getAccountsNotApproved() {
		return staffService.getAccountsNotApproved();
	}

	@PutMapping("/accounts/approved/{accountNumber}/{customerId}")
	public ResponseEntity<Account> updateAccountType(@PathVariable("accountNumber") long accountNumber,
			@PathVariable("customerId") long customerId) {
		Account updateAccountType = accountRepo.getParticularAccountType(accountNumber, customerId)
				.orElseThrow(() -> new RuntimeException("Account Not exisit with id: " + accountNumber));

		updateAccountType.setApproved(true);
		accountRepo.save(updateAccountType);
		return ResponseEntity.ok(updateAccountType);
	}

	@GetMapping("/customer")
	public List<Customer> getCustomer() {
		return customerRepo.getCustomer();
	}

	@PutMapping("/customer/enable/{customerId}")
	public ResponseEntity<Customer> updateCustomerActive(@PathVariable("customerId") long customerId) {
		Customer updateCustomer = customerRepo.findById(customerId)
				.orElseThrow(() -> new RuntimeException("Customer Not exisit with id: " + customerId));

		updateCustomer.setStatus(CustomerStatus.ENABLE);
		customerRepo.save(updateCustomer); 
		return ResponseEntity.ok(updateCustomer);
	}

	@PutMapping("/customer/disable/{customerId}")
	public ResponseEntity<Customer> updateCustomerInactive(@PathVariable("customerId") long customerId) {
		Customer updateCustomer = customerRepo.findById(customerId)
				.orElseThrow(() -> new RuntimeException("Customer Not exisit with id: " + customerId));

		updateCustomer.setStatus(CustomerStatus.DISABLE);
		customerRepo.save(updateCustomer); 
		return ResponseEntity.ok(updateCustomer);
	}

	@GetMapping("/customer/{customerId}")
	public Customer getCustomerById(@PathVariable("customerId") long customerId) {
		return customerRepo.getCustomerById(customerId);
	}
	
//	--------------------------------------------------------------------
	@Autowired
	TransferRepo transferRepo;
	@GetMapping("/transfer/{fromAccount}/{toAccount}")
	public Transfer getSpecificTransfer(@PathVariable("fromAccount") long fromAccount, @PathVariable("toAccount") long toAccount) {
		return transferRepo.getSpecificTransfer(fromAccount, toAccount);
	}
	
	@PostMapping("/transfer")
	Transfer newTrans(@Valid @RequestBody Transfer transfer) {
		return transferRepo.save(transfer);
	}
	
	@Autowired
	AccountRepo accountRepo;
	@PutMapping("/transfers")
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
	
	@GetMapping("/getstaff")
	public List<Staff> listAllStaffMembers() {
		return staffService.listAllStaffMembers();
	}
}
