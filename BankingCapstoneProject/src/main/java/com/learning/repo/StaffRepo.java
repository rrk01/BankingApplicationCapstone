package com.learning.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.entity.Staff;

public interface StaffRepo extends JpaRepository<Staff,String> {

}
