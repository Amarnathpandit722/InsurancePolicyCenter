package com.insurance.pc.dto.request.client;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class CreateClientRequest extends BaseClientRequest {

	@NotEmpty(message = "${ValidationMessages.FirstName.NOT_EMPTY}")
	private String firstName;

	@NotEmpty(message = "${ValidationMessages.LastName.NOT_EMPTY}")
	private String lastName;

	@NotEmpty(message = "${ValidationMessages.Email.NOT_EMPTY}")
	private String email;

	@NotEmpty(message = "${ValidationMessages.PhoneNumber.NOT_EMPTY}")
	private String phoneNumber;

	@NotEmpty(message = "${ValidationMessages.DateOfBirth.NOT_EMPTY}")
	private String dateOfBirth;

}
