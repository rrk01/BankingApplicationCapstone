/**
 * 
 */
package com.learning.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.entity.Approver;

/**
 * @author Greg N.
 * @date :
 * 
 */
@Repository
public interface AdminRepo extends JpaRepository<Approver, Long> {

}

