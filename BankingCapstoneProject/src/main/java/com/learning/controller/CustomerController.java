package com.learning.controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import com.fasterxml.jackson.databind.ObjectMapper;
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
@RequestMapping("/api/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	@Autowired
	AccountRepo accountRepo;
	
	//New for login
	@GetMapping("/getcustomer")
	public List<Customer> getCustomers() {
		return customerService.getCustomers();
	}
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@PostMapping(value="/register", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object>  registerCustomer(@RequestBody Customer customer) {
		LinkedHashMap obj = new LinkedHashMap();
		Customer newcustomer=customerService.registerCustomer(customer);
		String jsonString="";
		 try {
			obj.put("id", newcustomer.getId());
			obj.put("username", newcustomer.getUserName());
			obj.put("fullname", newcustomer.getFullName());
			obj.put("password", newcustomer.getPassword());
			jsonString=new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return ResponseEntity.status(HttpStatus.OK).body(jsonString.toString());
	}
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@PostMapping(value="/{id}/account",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createCustomerAccount(@Valid @PathVariable long id,@RequestBody Account account) {
		/*accountType:Enum(SB/CA), accountBalance:Number, approved: String -no (default)*/
		
		LinkedHashMap obj = new LinkedHashMap();
		account.setDateOfCreation(new Date(LocalDate.now().getYear(),LocalDate.now().getDayOfMonth(),LocalDate.now().getDayOfMonth()));
		Account newaccount=customerService.createCustomerAccount(id, account);
		String jsonString="";
		 try {
			obj.put("accountType", newaccount.getAccountType());
			obj.put("accountBalance", newaccount.getAccountBalance());
			obj.put("approved", newaccount.isApproved());
			obj.put("dateOfCreation", newaccount.getDateOfCreation());
			jsonString=new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.OK).body(jsonString.toString());
	
	}

	
	@GetMapping(value="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getCustomer(@Valid @PathVariable("id") long id) {
		Customer customer=customerService.findCustomerById(id);
		LinkedHashMap obj = new LinkedHashMap();
		String jsonString="";
		 try {
			obj.put("username", customer.getUserName());
			obj.put("fullName", customer.getFullName());
			obj.put("phone", customer.getPhone());
			obj.put("ssn", customer.getSSN());
			jsonString=new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return ResponseEntity.status(HttpStatus.OK).body(jsonString.toString());
	}
	
	/*@GetMapping("/getcustomer")
	public List<Customer> getCustomers() {
		return customerService.getCustomers();

	}*/
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@PutMapping(value="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateCustomer(@Valid @RequestBody Customer customer, @PathVariable("id") long id) {
		customerService.updateCustomer(customer,id);
		Customer updatedcustomer=customerService.findCustomerById(id);
		LinkedHashMap obj = new LinkedHashMap();
		String jsonString="";
		 try {
			obj.put("id", updatedcustomer.getId());
			obj.put("ssn", updatedcustomer.getSSN());
			obj.put("fullName", updatedcustomer.getFullName());
			obj.put("phone", updatedcustomer.getPhone());
			obj.put("secretQuestion", updatedcustomer.getSecretQuestion());
			obj.put("secretAnswer", updatedcustomer.getSecretAnswer());
			jsonString=new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.OK).body(jsonString.toString());
	}

	@GetMapping("/{custID}/account/{acctID}")
	public Account getCustomerAccount(@Valid @PathVariable("custID") long custID, @PathVariable("acctID") long acctID) {

		return customerService.findCustomerAccount(custID,acctID);
	}

	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@PostMapping(value="/{custID}/beneficiary",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object>  addBeneficiary(@Valid @RequestBody Beneficiary beneficiary, @PathVariable("custID") long custID) {
		Beneficiary newbeneficiary=customerService.addBeneficiary(beneficiary,custID);
		LinkedHashMap obj = new LinkedHashMap();
		String jsonString="";
		 try {
			obj.put("accountNumber", newbeneficiary.getAccountNumber());
			obj.put("accountType", newbeneficiary.getAccountType());
			obj.put("approved", newbeneficiary.getApproved());
			jsonString=new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return ResponseEntity.status(HttpStatus.OK).body(jsonString.toString());
	}
	
	@GetMapping(value="/{custID}/beneficiary",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getBeneficiary(@Valid @PathVariable("custID") long custID) {
		List<Beneficiary> beneficiaries=customerService.getBeneficiary(custID);
		List<String> newlist=new ArrayList<>();
		String jsonString="";
		LinkedHashMap obj = new LinkedHashMap();
		for(int i=0;i<beneficiaries.size();i++) {
			 try {
				obj.put("beneficiaryAccountNo", beneficiaries.get(i).getBeneficiaryAcNo());
				obj.put("beneficiaryName", beneficiaries.get(i).getBeneficiaryName());
				obj.put("approved", beneficiaries.get(i).getApproved());
				jsonString=new ObjectMapper().writeValueAsString(obj);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 newlist.add(jsonString);
		}
		return ResponseEntity.status(HttpStatus.OK).body(newlist.toString()); 

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
	@GetMapping("/{custID}/account")
	public List<Account> getAllCustomerAccount(@Valid @PathVariable("custID") long custID) {
		return customerService.findAllCustomerAccount(custID);
	}
}