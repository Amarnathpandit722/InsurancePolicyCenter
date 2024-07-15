package com.insurance.pc.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurance.pc.model.Client;

@Repository
public interface ClientRepository  extends JpaRepository<Client, String>{

}
