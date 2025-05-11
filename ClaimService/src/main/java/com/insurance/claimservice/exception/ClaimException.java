package com.insurance.claimservice.exception;

public class ClaimException extends RuntimeException {
    public ClaimException(String message) {
        super(message);
    }
}