package com.learning.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.learning.entity.Account;
import com.learning.entity.AccountStatus;
import com.learning.entity.Beneficiary;
import com.learning.entity.Staff;
import com.learning.repo.AccountRepo;
import com.learning.repo.BeneficiaryRepo;
import com.learning.entity.StaffStatus;
import com.learning.repo.StaffRepo;

@Service
public class StaffService {
	@Autowired
	StaffRepo staffRepo;
	@Autowired
	BeneficiaryRepo beneficiaryRepo;
	@Autowired
	AccountRepo accountRepo;
	
	public Staff createStaffMember(Staff staff) {
		return staffRepo.save(staff);
	}
	
	public List<Beneficiary> getAllBeneficiary() {
		return beneficiaryRepo.getAllBeneficiary(); 
	}
	
	public List<Beneficiary> getAllBeneficiaryApproved() {
		return beneficiaryRepo.getAllBeneficiaryApproved(); 
	}
	
	public List<Account> getAccountsNotApproved(){
		return accountRepo.getAccountsNotApproved();
	}

	// Enable / Disable 
	public List<Staff> listAllStaffMembers() {
		return staffRepo.findAll();
	}
	
	public Staff enableOrDisableStaffMember(long id) {
		Staff staffObject=staffRepo.findById(id).get();
		if(staffObject.getStatus()==StaffStatus.ENABLED) {
			staffObject.setStatus(StaffStatus.DISABLED);
		}else if(staffObject.getStatus()==StaffStatus.DISABLED) {
			staffObject.setStatus(StaffStatus.ENABLED);
      // Approve beneficiary
		}
		staffRepo.save(staffObject);
		return new Staff(staffObject.getId(), null, null, null, staffObject.getStatus()) ;
	}
	
}
