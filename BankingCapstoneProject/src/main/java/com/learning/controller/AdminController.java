package com.learning.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.learning.entity.Approver;
import com.learning.entity.Staff;
import com.learning.service.AdminService;
import com.learning.service.StaffService;

@CrossOrigin
@RestController
@RequestMapping("/api/admin")
public class AdminController {
	@Autowired
	StaffService staffService;
	@Autowired
	AdminService adminService;
	
	@GetMapping("/getadmin")
	public List<Approver> getAllApprovers(){
		return adminService.getAllApprovers();
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@PostMapping(value="/staff",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createStaff(@RequestBody Staff staff) {
		Staff newstaff=staffService.createStaffMember(staff);
		LinkedHashMap obj = new LinkedHashMap();
		String jsonString="";
		 try {
			obj.put("name", newstaff.getName());
			obj.put("username", newstaff.getUserName());
			obj.put("password", newstaff.getPassword());
			jsonString=new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.OK).body(jsonString.toString());

	}
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@GetMapping(value="/staff/listAllStaff",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> listAllStaff() {
		List<Staff> staffMembers=staffService.listAllStaffMembers();
		List<String> returnStaffMembers=new ArrayList<>();
		LinkedHashMap obj = new LinkedHashMap();
		for(int i=0;i<staffMembers.size();i++) {
			String jsonString="";
			 try {
				obj.put("id", staffMembers.get(i).getId());
				obj.put("name", staffMembers.get(i).getName());
				obj.put("userName", staffMembers.get(i).getUserName());
				obj.put("status", staffMembers.get(i).getStatus());
				jsonString=new ObjectMapper().writeValueAsString(obj);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			returnStaffMembers.add(jsonString);
		}
		return ResponseEntity.status(HttpStatus.OK).body(returnStaffMembers.toString());
	}
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@PutMapping(value="/staff/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> disableOrEnableStaff(@PathVariable  long id) {
		Staff staff=staffService.enableOrDisableStaffMember(id);
		LinkedHashMap obj = new LinkedHashMap();
		String jsonString="";
		 try {
			obj.put("staffId", staff.getId());
			obj.put("status", staff.getStatus());
			jsonString=new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return ResponseEntity.status(HttpStatus.OK).body(jsonString.toString());
	}

} 