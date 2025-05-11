package com.insurance.customerservice.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Customer entity for storing customer details.
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
   
    private Address address;
}
