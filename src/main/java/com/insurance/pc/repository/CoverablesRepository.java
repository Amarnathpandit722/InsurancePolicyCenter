package com.insurance.pc.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurance.pc.model.Coverables;


@Repository
public interface CoverablesRepository extends JpaRepository<Coverables, String> {

}
