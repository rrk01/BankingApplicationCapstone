package com.learning.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import com.learning.entity.Account;
import com.learning.entity.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Long> {
	
}
