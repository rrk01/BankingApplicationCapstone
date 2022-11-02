package com.learning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.learning.entity.Account;
import com.learning.entity.Beneficiary;
import com.learning.entity.Customer;
import com.learning.entity.Staff;
import com.learning.entity.status;
import com.learning.repo.StaffRepo;

@Service
public class StaffService {
	@Autowired
	StaffRepo staffRepo;
	
	public Staff createStaffMember(Staff staff) {
		return staffRepo.save(staff);
	}
	
	public Account getParticularAccount() {
		return staffRepo.getParticularAccount();
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

	public Account save(Account accountDetails) {
		return staffRepo.save(accountDetails);
	}

	public List<Customer> getCustomer() {
		return staffRepo.getCustomer();
	}

	public Customer getCustomerById(long customerId) {
		return staffRepo.getCustomerById(customerId);
	}
	
	public Staff enableOrDisableStaffMember(logn customerId) {
        Staff staffObject=staffRepo.findById(username).get();
        if(staffObject.getStatus()==status.ENABLE) {
            staffObject.setStatus(status.DISABLE);
        }else if(staffObject.getStatus()==status.DISABLE) {
            staffObject.setStatus(status.ENABLE);
        }
        staffRepo.save(staffObject);
        return new Staff(staffObject.getId(), null, null, null, staffObject.getStatus()) ;
    }
	
}
