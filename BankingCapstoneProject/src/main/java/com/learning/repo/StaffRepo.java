package com.learning.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.learning.entity.Staff;

@Repository
@Transactional
public interface StaffRepo extends JpaRepository<Staff,Long> {
	
//	#9 
//	@Modifying
//	@Query(value="")
//	public void updateAccountBalance(Account updateAccountBalance);
	
//	@Modifying
//	@Query(value="")
//	public void updateAccountBalance_2(Account updateAccountBalance);

}
