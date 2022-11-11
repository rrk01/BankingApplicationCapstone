/**
 * 
 */
package com.learning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.entity.Approver;
import com.learning.repo.AdminRepo;

/**
 * @author Greg N.
 * @date :
 * 
 */
@Service
public class AdminService {
	
	@Autowired
	AdminRepo adminRepo;
	
	public List<Approver> getAllApprovers(){
		return adminRepo.findAll();
	}

}

