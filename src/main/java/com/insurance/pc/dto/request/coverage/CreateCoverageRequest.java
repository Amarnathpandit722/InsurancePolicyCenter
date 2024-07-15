package com.insurance.pc.dto.request.coverage;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateCoverageRequest {

	@NotNull
	private String coverageType;
	
	@NotNull
	private String description;
	
	
}
