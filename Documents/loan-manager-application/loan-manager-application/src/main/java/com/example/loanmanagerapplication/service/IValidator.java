package com.example.loanmanagerapplication.service;

import com.example.loanmanagerapplication.model.Loan;

import java.text.ParseException;

public interface IValidator {

    public boolean isValidLoanRequest(Loan loan) throws ParseException;
}
