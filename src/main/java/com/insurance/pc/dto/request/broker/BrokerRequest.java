package com.insurance.pc.dto.request.broker;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BrokerRequest {

	@NotNull
	private String name;
	@NotNull
	private String email;
	@NotNull
	private String phoneNumber;

}
