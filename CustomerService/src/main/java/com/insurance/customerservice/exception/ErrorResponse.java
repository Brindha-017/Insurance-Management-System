package com.insurance.customerservice.exception;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ErrorResponse{
	private String error;
	private String message;
}
