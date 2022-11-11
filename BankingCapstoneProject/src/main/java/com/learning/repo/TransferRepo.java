package com.learning.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.learning.entity.Transfer;

@Repository
@Transactional
public interface TransferRepo extends JpaRepository<Transfer,Long> {
	
	@Query(value="Select * from transfers t where t.from_account=:fromAccount and t.to_account=:toAccount", nativeQuery =true)
	Transfer getSpecificTransfer(long fromAccount, long toAccount);
	
}
