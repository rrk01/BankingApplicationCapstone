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
	
//	Select a.account_number, c.full_name, a.account_balance, GROUP_CONCAT(t.t_date, t.t_reason, t.t_amount, t.credit_debit ORDER BY t.t_id) FROM accounts a, customers c JOIN transaction t ON t.account_number=123456 where a.account_number = 123456;
//	#1 
	@Query(value="Select a.account_number, c.full_name, a.account_balance, GROUP_CONCAT('date:', t.t_date, '\n', 'reason:', t.t_reason, '\n', 'amount:',t.t_amount, '\n', 'transactionType:',t.credit_debit, '\n' ORDER BY t.t_id) AS transaction FROM accounts a, customers c JOIN transaction t ON t.account_number=:accountNumber where a.account_number=:accountNumber", nativeQuery=true)
	public Account getParticularAccount(@Param("accountNumber") long accountNumber); 
//	**** Still Look into more about arrayWrap
//	Select a.account_number, c.full_name, a.account_balance, t.t_date, t.t_reason, t.account_number, t.credit_debit  FROM accounts a, customers c LEFT JOIN transaction t ON t.account_number =:accountNumber where c.id = a.customer_id

//	#2
	@Query(value="Select account_number AS 'fromCustomer', beneficiary_ac_no, beneficiary_added_date, approved from beneficiary where approved=0", nativeQuery=true)
	public List<Beneficiary> getAllBeneficiary();
	
//	#3
	@Query(value="Select account_number AS 'fromCustomer', beneficiary_ac_no, beneficiary_added_date, approved from beneficiary where approved=1", nativeQuery=true)
	public List<Beneficiary> getAllBeneficiaryApproved();
	
//	#4
	@Query(value="Select a.account_type, c.full_name, a.account_number, a.creation_date, a.approved from accounts a, customers c where c.id = a.customer_id AND a.approved = 0", nativeQuery=true)
	public List<Account> getAccountsNotApproved();

//	#5
	@Query(value="Select * from account a where a.account_number=:accountNumber AND a.customer_id=:customerId", nativeQuery=true)
	public Optional<Account> getParticularAccountType(@Param("accountNumber") long accountNumber, @Param("customerId") long customerId);
	
//	#6
	@Query(value="Select customer_id, customer_name, c.status from customers c", nativeQuery=true)
	public List<Customer> getCustomer();

//	#7
	@Query(value="Select c.customer_id, c.customer_name, c.status from customers c, staff s where c.customer_id =:customerId", nativeQuery=true)
	public Customer getCustomerById(@Param("customerId") long customerId);
		
//	#9 
//	@Modifying
//	@Query(value="")
//	public void updateAccountBalance(Account updateAccountBalance);
	
//	@Modifying
//	@Query(value="")
//	public void updateAccountBalance_2(Account updateAccountBalance);

}
