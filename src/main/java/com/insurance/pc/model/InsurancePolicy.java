package com.insurance.pc.model;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.insurance.pc.helper.PolicyPremium;
import com.insurance.pc.helper.PolicyType;
import com.insurance.pc.helper.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tbl_insurance_policy")
@Entity
public class InsurancePolicy {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String  id;

	private String policyNumber;

	private PolicyType insuracePolicyType;

	private double insurancePolicyCoverageAmount;

	private double insurancePolicyPremiumAmount;

	@Enumerated(EnumType.STRING)
	private PolicyPremium insurancePolicyPremium;

	private String insurancePolicyStartDate;

	private String insurancePolicyEndDate;

	@Enumerated(EnumType.STRING)
	private Status policyStatus;

	private double brokerCompensation;
	
	private String nationalId;
	
	private String premiumTenure;

    @JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id")
	private Client client;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "broker_id")
	private Broker broker;

	@OneToMany(mappedBy = "policy", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Coverage> coverages = new HashSet<>();

	@OneToMany(mappedBy = "policy", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Coverables> coverables = new HashSet<>();

//	@OneToMany(mappedBy = "policy", cascade = CascadeType.ALL, orphanRemoval = true)
//	private Set<CalculatedPremium> calculatedPremiums = new HashSet<>();

}
