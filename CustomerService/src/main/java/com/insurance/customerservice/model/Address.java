package com.insurance.customerservice.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Embeddable
public class Address {
	@NotBlank(message = "Street Name is required")
	private String street;
	@NotBlank(message = "State Name is required")
	private String state;
	@NotBlank(message = "City Name is required")
	private String city;
	@NotBlank(message = "Postal Code is required")
	private String postalcode;

	
}
