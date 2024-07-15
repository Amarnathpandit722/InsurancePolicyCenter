package com.insurance.pc.repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurance.pc.model.InsurancePolicy;


@Repository
public interface InsurancePolicyRepository  extends JpaRepository<InsurancePolicy,String>{

    Optional<InsurancePolicy> findByPolicyNumber(String policyNumber);

	List<InsurancePolicy> findByNationalId(String nationalId);

	List<InsurancePolicy> findByBrokerId(String brokerId);
	

}
