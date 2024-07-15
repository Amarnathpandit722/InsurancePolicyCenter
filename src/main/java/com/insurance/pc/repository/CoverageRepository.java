package com.insurance.pc.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurance.pc.model.Coverage;


@Repository
public interface CoverageRepository  extends JpaRepository<Coverage, String>{

}