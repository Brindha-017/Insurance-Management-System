package com.insurance.policyservice.dto;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Embeddable
@RequiredArgsConstructor
public class AddressDTO {
	private String street;
	private String state;
	private String city;
	private String postalcode;
}
