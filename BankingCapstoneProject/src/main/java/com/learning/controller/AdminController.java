package com.learning.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.learning.entity.Staff;
import com.learning.service.StaffService;

@RestController
@CrossOrigin
@RequestMapping("/api/admin")
public class AdminController {
	@Autowired
	StaffService staffService;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@PostMapping("/staff")
	public Staff createStaff(@RequestBody Staff staff) {
		staffService.createStaffMember(staff);
		return new Staff(0, staff.getName(), staff.getUserName(), staff.getPassword(), null);
		
	}
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@GetMapping("/staff/listAllStaff")
	public List<Staff> listAllStaff() {
		List<Staff> staffMembers=staffService.listAllStaffMembers();
		List<Staff> returnStaffMembers=new ArrayList<>();
		for(int i=0;i<staffMembers.size();i++) {
			returnStaffMembers.add(new Staff(staffMembers.get(i).getId(), staffMembers.get(i).getName(), null, null, staffMembers.get(i).getStatus()));
		}
		return staffService.listAllStaffMembers();
	}
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@PutMapping("/staff/{id}")
	public Staff disableOrEnableStaff(@PathVariable String username) {
		return staffService.enableOrDisableStaffMember(username);
	}

}
