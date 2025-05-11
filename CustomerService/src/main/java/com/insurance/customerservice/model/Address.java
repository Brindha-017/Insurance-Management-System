package com.insurance.customerservice.model;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Embeddable
public class Address {
	private String street;
	private String state;
	private String city;
	private String postalcode;

	
}
