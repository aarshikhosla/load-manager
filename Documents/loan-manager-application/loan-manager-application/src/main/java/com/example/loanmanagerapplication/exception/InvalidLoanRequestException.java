package com.example.loanmanagerapplication.exception;

public class InvalidLoanRequestException extends RuntimeException {
    public InvalidLoanRequestException(String message){
        super(message);
    }
}
