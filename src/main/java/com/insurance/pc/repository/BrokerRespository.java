package com.insurance.pc.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurance.pc.model.Broker;


@Repository
public interface BrokerRespository extends JpaRepository<Broker, String> {

}
