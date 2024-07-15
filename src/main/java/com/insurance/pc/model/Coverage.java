package com.insurance.pc.model;


import org.hibernate.annotations.GenericGenerator;



import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tbl_coverages")
public class Coverage {
	
		@Id
		@GeneratedValue(generator = "UUID")
		@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
		private String   id;
		
		private String coverageType;

	  
	    private String description;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "policy_id")
	    private InsurancePolicy policy;

}
