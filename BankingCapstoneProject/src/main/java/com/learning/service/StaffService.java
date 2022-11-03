package com.learning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.learning.entity.Account;
import com.learning.entity.Beneficiary;
import com.learning.entity.Customer;
import com.learning.entity.Staff;
import com.learning.entity.StaffStatus;
import com.learning.repo.StaffRepo;

@Service
public class StaffService {
	@Autowired
	StaffRepo staffRepo;
	
	public Staff createStaffMember(Staff staff) {
		return staffRepo.save(staff);
	}
	
	public Account getParticularAccount(long accountNumber) {
		return staffRepo.getParticularAccount(accountNumber);
	}
	
	public List<Beneficiary> getAllBeneficiary() {
		return staffRepo.getAllBeneficiary(); 
	}
	
	public List<Beneficiary> getAllBeneficiaryApproved() {
		return staffRepo.getAllBeneficiary(); 
	}
	
	public List<Account> getAccountsNotApproved(){
		return staffRepo.getAccountsNotApproved();
	}
	
	public List<Customer> getCustomer() {
		return staffRepo.getCustomer();
	}

	public Customer getCustomerById(long customerId) {
		return staffRepo.getCustomerById(customerId);
	}
	
// - Move to Approver entity (?)
	public List<Staff> listAllStaffMembers() {
		return staffRepo.findAll();
	}
	public Staff enableOrDisableStaffMember(long id) {
		Staff staffObject=staffRepo.findById(id).get();
		if(staffObject.getStatus()==StaffStatus.ENABLED) {
			staffObject.setStatus(StaffStatus.DISABLED);
		}else if(staffObject.getStatus()==StaffStatus.DISABLED) {
			staffObject.setStatus(StaffStatus.ENABLED);
		}
		staffRepo.save(staffObject);
		return new Staff(staffObject.getId(), null, null, null, staffObject.getStatus()) ;
	}

}
