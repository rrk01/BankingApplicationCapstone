package com.learning.repo;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.learning.entity.Account;
import com.learning.entity.Beneficiary;
import com.learning.entity.Customer;
import com.learning.entity.CustomerStatus;
import com.learning.entity.Staff;

@Repository
@Transactional
public interface StaffRepo extends JpaRepository<Staff,Long> {
	
//	#1 
	@Query(value="Select a.account_number, c.full_name, a.account_balance, t.t_date, t.t_reason, t.account_number, t.credit_debit  FROM accounts a, customers c LEFT JOIN transaction t ON t.account_number =:accountNumber where c.id = a.customer_id", nativeQuery=true)
	public Account getParticularAccount(@Param("accountNumber") long accountNumber); 
//	Select a.account_number, c.full_name, a.account_balance FROM accounts a, customers c where c.id = a.customer_id;

////	#2
//	@Query(value="Select account_number AS 'fromCustomer', beneficiary_ac_no, beneficiary_added_date, approved from beneficiary where approved=0", nativeQuery=true)
//	public List<Beneficiary> getAllBeneficiary();
//	
////	#3
//	@Query(value="Select account_number AS 'fromCustomer', beneficiary_ac_no, beneficiary_added_date, approved from beneficiary where approved=1", nativeQuery=true)
//	public List<Beneficiary> getAllBeneficiaryApproved();
//	
////	#4
//	@Query(value="Select a.account_number, c.full_name, a.account_number, a.creation_date, a.approved from accounts a, customers c where c.id = a.customer_id AND a.approved = 0")
//	public List<Account> getAccountsNotApproved();

//	#5
//	@Modifying
//	@Query(value="")
//	public Optional<Account> getParticularAccountType(@Param("accountNumber") long accountNumber, @Param("customerId") long customerId);
//	
//	@Modifying
//	@Query(value="")
//	public void saveAccount(boolean isApproved);

////	#6
//	@Query(value="Select customer_id, customer_name, s.status from customers c, staff s", nativeQuery=true)
//	public List<Customer> getCustomer();
//
////	#7
//	@Query(value="Select c.customer_id, c.customer_name, s.status from customers c, staff s where c.customer_id =:customerId", nativeQuery=true)
//	public Customer getCustomerById(@Param("customerId") long customerId);

//	#8
//	@Modifying
//	@Query(value="")
//	public void saveCustomerEnable(long customerId, CustomerStatus status);
//	
//	@Modifying
//	@Query(value="")
//	public void saveCustomerDisable(long customerId, CustomerStatus status);	
//	
////	#9 
//	@Modifying
//	@Query(value="")
//	public void updateAccountBalance(Account updateAccountBalance);
//	
//	@Modifying
//	@Query(value="")
//	public void updateAccountBalance_2(Account updateAccountBalance);

}
