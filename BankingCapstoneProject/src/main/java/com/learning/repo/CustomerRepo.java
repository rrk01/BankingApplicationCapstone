package com.learning.repo;

import java.util.List;

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

	@Query(value="SELECT user_name,full_name,phone FROM customers WHERE id=:id", nativeQuery = true)
	public List<Object> getCustomer(@Param("id") long id);
	
//	#6
	@Query(value="Select * from customers c", nativeQuery=true)
	public List<Customer> getCustomer();
	
//	#7
	@Query(value="Select * from customers c where c.id =:customerId", nativeQuery=true)
	public Customer getCustomerById(@Param("customerId") long customerId);

}
