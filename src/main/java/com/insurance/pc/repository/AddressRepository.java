package com.insurance.pc.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.insurance.pc.model.Address;

@Repository
public interface AddressRepository  extends JpaRepository<Address, String>{

	@Query(value = "SELECT * FROM address WHERE client_id = :clientId", nativeQuery = true)
    List<Address> findByClient(@Param("clientId") String clientId);	

}
