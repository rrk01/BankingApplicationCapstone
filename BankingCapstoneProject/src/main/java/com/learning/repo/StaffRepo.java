package com.learning.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.learning.entity.Beneficiary;
import com.learning.entity.Staff;

@Repository
public interface StaffRepo extends JpaRepository<Staff,String> {
	@Modifying
	@Query(value="Select accountNumber AS 'fromCustomer', beneficiaryAcNo, beneficiaryAddedDate, approved from Beneficiary where approved='No'", nativeQuery=true)
	public List<Beneficiary> getAllBeneficiary(); //
}
