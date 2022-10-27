package com.learning.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.learning.entity.Account;

public interface AccountRepo extends JpaRepository<Account,Long> {
	@Modifying
	@Query(value="Select * from Account where id=:id", nativeQuery=true)
	public Account getAllCustomerAccounts(@Param(value="id") long id);

}
