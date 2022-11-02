package com.learning.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.learning.entity.Account;
import com.learning.entity.Beneficiary;
import com.learning.entity.Customer;
import com.learning.entity.Staff;

@Repository
public interface StaffRepo extends JpaRepository<Staff,Long> {
	
//	#1 
	@Query(value="Select a.accountNumber, c.fullName, a.accountBalance from account a, customers c JOIN transaction t ON t.account_number = a.accountNumber where c.id = a.customer_id;", nativeQuery=true)
	public Account getParticularAccount(); 
	
//	#2
	@Query(value="Select accountNumber AS 'fromCustomer', beneficiaryAcNo, beneficiaryAddedDate, approved from beneficiary where approved=false", nativeQuery=true)
	public List<Beneficiary> getAllBeneficiary();
	
//	#3
	@Query(value="Select accountNumber AS 'fromCustomer', beneficiaryAcNo, beneficiaryAddedDate, approved from beneficiary where approved=true", nativeQuery=true)
	public List<Beneficiary> getAllBeneficiaryApproved();
	
//	#4
	@Query(value="Select a.accountType, c.fullName, a.accountNumber, a.dateOfCreation, a.approved from Accounts a, Customers c where c.id = a.customerId AND a.approved = false")
	public List<Account> getAccountsNotApproved();

//	#5
	@Modifying
	@Query(value="")
	public Account save(Account accountDetails);

//	#6
	@Query(value="Select customerId, customerName, s.status from customers c, staff s", nativeQuery=true)
	public List<Customer> getCustomer();

//	#7
	@Query(value="Select c.customerId, c.customerName, s.status from customers c, staff s where c.customerId =:customerId", nativeQuery=true)
	public Customer getCustomerById(@Param("customerId") long customerId);

//	#8
//	@Query(value="")
//	public 

}
