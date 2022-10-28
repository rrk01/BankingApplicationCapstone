package com.learning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.entity.Staff;
import com.learning.repo.StaffRepo;

@Service
public class StaffService {
	@Autowired
	StaffRepo staffRepo;
	
	public Staff createStaffMember(Staff staff) {
		return staffRepo.save(staff);
	}
}
