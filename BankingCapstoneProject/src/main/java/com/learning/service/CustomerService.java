package com.learning.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.learning.entity.Beneficiary;
import com.learning.entity.Account;
import com.learning.entity.Customer;
import com.learning.repo.AccountRepo;
import com.learning.repo.BeneficiaryRepo;
import com.learning.repo.CustomerRepo;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepo customerRepo;
	@Autowired
	private AccountRepo accountRepo;
	@Autowired
	private BeneficiaryRepo beneficiaryRepo;////////////////////////////////////////////////?????????????????????????????????????????????????????????
	private List<Beneficiary> beneficiariesList;
	
	public Customer registerCustomer(Customer customer) {
		return customerRepo.save(customer);
	}
	public Account createCustomerAccount(long id,Account account) {
		account.setCustomerId(id);
		return accountRepo.save(account);
	}
	public Account saveApproval(Account account) {
		return accountRepo.save(account);
	}
	public Account findCustomerAccount(long accountNumber) {
		Optional<Account> accountObject=accountRepo.findById(accountNumber);
		return accountObject.get();
	}
	public List<Account> findAllCustomerAccount(long accountNumber) {
		return accountRepo.findAll();
	}
	public List<Object> getCustomer(long id) {
		return customerRepo.getCustomer(id);
	}
	public List<Customer> getCustomers() {
		return customerRepo.findAll();
	}
	public Customer updateCustomer(Customer cust, long id) {
		Customer customer= customerRepo.getById(id);
		
		customer.setId(cust.getId());
		customer.setFullName(cust.getFullName());
		customer.setPassword(cust.getPassword());
		customer.setPhone(cust.getPhone());
		customer.setSecretQuestion(cust.getSecretQuestion());
		customer.setSecretAnswer(cust.getSecretAnswer());
		customer.setUserName(cust.getUserName());
		
		return customerRepo.save(customer);
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public Beneficiary addBeneficiary(Beneficiary beneficiary, long custID) {
		beneficiary.setApproved(false);
		return beneficiaryRepo.save(beneficiary);
	}
	public List<Beneficiary> getBeneficiary(Beneficiary beneficiary, long custID) {
		beneficiariesList.clear();
		List<Account> validAccounts=accountRepo.getValidAccounts(custID);
		for(Account acct: validAccounts) {
			beneficiariesList.addAll(beneficiaryRepo.getBeneficiaryForAccount(acct.getAccountNumber()));
		}
		return beneficiariesList;	
	}
	
	public String deleteBeneficiary(@Valid @PathVariable("beneficiaryID") long beneficiaryID, @PathVariable("custID") long custID) {
		return beneficiaryRepo.deleteCustomersBeneficiary(beneficiaryID,custID);
	}
} 
