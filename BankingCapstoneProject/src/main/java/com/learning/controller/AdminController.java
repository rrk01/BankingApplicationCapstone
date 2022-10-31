package com.learning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.entity.Staff;
import com.learning.service.StaffService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
//	@Autowired
//	StaffService staffService;
//	@PostMapping("/staff")
//	public Staff createStaff(@RequestBody Staff staff) {
//		return staffService.createStaffMember(staff);
//	}
}
