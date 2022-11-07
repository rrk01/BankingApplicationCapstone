package com.learning.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.learning.entity.Account;
import com.learning.entity.Beneficiary;
import com.learning.entity.Customer;
import com.learning.repo.AccountRepo;
import com.learning.repo.BeneficiaryRepo;
import com.learning.repo.CustomerRepo;
import com.learning.entity.Beneficiary;
@Service
public class CustomerService {
	@Autowired
	private CustomerRepo customerRepo;
	@Autowired
	private BeneficiaryRepo beneficiaryRepo;
	private List<Beneficiary> beneficiaryList = new ArrayList<Beneficiary>();
  @Autowired
	private AccountRepo accountRepo;
  
  	
	
	List<Beneficiary> beneficiariesList=new ArrayList<>();
	
	
	public List<Customer> getCustomers() {
  		return customerRepo.findAll();
  	}
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
	public Account findCustomerAccount(long custId, long accountNumber) {
		Account account = null;
		for(Account acct : findAllCustomerAccount(custId)) { // makes sure that the customer is the owner of the searched account
			if(acct.getAccountNumber()==accountNumber) {
				account=acct;
			}
		}
		return account;

	}
	public List<Account> findAllCustomerAccount(long custID) {
		return accountRepo.findAll().stream().filter(a -> a.getCustomerId() == custID).collect(Collectors.toList());
	}

	public Customer findCustomerById(long id) {
		Optional<Customer> CustomerObject=customerRepo.findById(id);
		return CustomerObject.get();
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
		customer.setStatus(cust.getStatus());
		return customerRepo.save(customer);
	}
  
	public Beneficiary addBeneficiary(Beneficiary beneficiary, long custID) {
		beneficiary.setApproved(false);
		return beneficiaryRepo.save(beneficiary);
	}

	public List<Beneficiary> getBeneficiary(long custID) {
		beneficiaryList.clear();
		List<Account> validAccounts=accountRepo.getValidAccounts(custID); // CHECK IF ITS ACTIVE (approved)
		for(Account acct: validAccounts) { // Iterated accounts
			beneficiaryList.addAll(beneficiaryRepo.getBeneficiaryForAccount(acct.getAccountNumber())); // GET ALL beneficiary based off accNum
		}
		return beneficiaryList;
	}

	public String deleteBeneficiary(@Valid @PathVariable("beneficiaryID") long beneficiaryID, @PathVariable("custID") long custID) {
		//return beneficiaryRepo.deleteCustomersBeneficiary(beneficiaryID,custID);//////// OVERKILL
		try {

			if(beneficiaryRepo.getById(beneficiaryID).getCustomerId()==custID) {
				beneficiaryRepo.deleteById(beneficiaryID);
				return "BENEFICIARY DELETED";
			} else {
				return "BENEFICIARY NOT DELETED";
			}
		}catch(Exception e) {
			return "BENEFICIARY NOT DELETED";
		}
		
	}
}

