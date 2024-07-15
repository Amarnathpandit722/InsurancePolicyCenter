package com.insurance.pc.dto.request.coverables;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateCoverablesRequest {

	@NotNull
	private String coverablesType;
	@NotNull
	private String description;
	
	
	
}
