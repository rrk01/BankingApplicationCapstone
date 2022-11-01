package com.learning.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.learning.entity.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account,Long> {
	@Modifying
	@Query(value="Select * from Account where id=:id", nativeQuery=true)
	public List<Account> getAllCustomerAccounts(@Param(value="id") long id);

	@Query(value="Select * FROM accounts where customer_id = :custID and approved=true", nativeQuery= true)
	public List<Account> getValidAccounts(@Param(value="custID") long id);

}
