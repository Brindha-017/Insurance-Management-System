package com.insurance.customerservice.dto;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.insurance.customerservice.model.Address;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for transferring customer data with validation.
 */

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CustomerDTO {

    private Long customerId;

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone number is required")
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

