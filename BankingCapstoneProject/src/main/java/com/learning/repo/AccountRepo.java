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

@Repository // might not be required since it extends to JPARepo
@Transactional 
public interface AccountRepo extends JpaRepository<Account,Long> {
	
	@Query(value="Select * from Account where customer_id=:custId", nativeQuery=true)
	public Account getAllCustomerAccounts(@Param(value="custId") long id);

	@Query(value="Select * FROM accounts where customer_id =:custID and approved=true", nativeQuery= true)
	public List<Account> getValidAccounts(@Param(value="custID") long id);
	
//	#1 
	@Query(value="Select * FROM accounts a where a.account_number=:accountNumber", nativeQuery=true)
	public Account getParticularAccount(@Param("accountNumber") long accountNumber); 

//	#4
	@Query(value="Select * from accounts a where a.approved = 0", nativeQuery=true)
	public List<Account> getAccountsNotApproved();

//	#5
	@Query(value="Select * from accounts a where a.account_number=:accountNumber AND a.customer_id=:customerId", nativeQuery=true)
	public Optional<Account> getParticularAccountType(@Param("accountNumber") long accountNumber, @Param("customerId") long customerId);
	

}
