package com.insurance.customerservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * Customer for storing customer details.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private String name;

    private String email;

    private String phone; 
   
    @NotBlank(message = "Street Name is required")
	private String street;
	@NotBlank(message = "State Name is required")
	private String state;
	@NotBlank(message = "City Name is required")
	private String city;
	@NotBlank(message = "Postal Code is required")
	private String postalcode;
	
	private Long assignedPolicyId;
	
	
	}
