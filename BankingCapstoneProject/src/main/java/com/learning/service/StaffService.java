package com.learning.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.entity.Staff;
import com.learning.entity.Staff.StaffStatus;
import com.learning.repo.StaffRepo;

@Service
public class StaffService {
	@Autowired
	StaffRepo staffRepo;
	
	public Staff createStaffMember(Staff staff) {
		return staffRepo.save(staff);
	}
	public List<Staff> listAllStaffMembers() {
		return staffRepo.findAll();
	}
	public Staff disableStaffMember(String username) {
		Staff staffObject=staffRepo.findById(username).get();
		staffObject.setStatus(StaffStatus.DISABLED);
		staffRepo.save(staffObject);
		return staffObject;
	}
}
