/**
 * 
 */
package com.learning.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Greg N.
 * @date :
 * 
 */
@Repository
public interface BeneficiaryRepo extends JpaRepository<Beneficiary, Long>{

}
