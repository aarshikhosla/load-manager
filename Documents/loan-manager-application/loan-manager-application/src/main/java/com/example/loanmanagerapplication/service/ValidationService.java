package com.example.loanmanagerapplication.service;

import com.example.loanmanagerapplication.model.Loan;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component("validationService")
public class ValidationService implements IValidator{
    @Override
    public boolean isValidLoanRequest(Loan loan) throws ParseException {
        return loan.getFormattedPaymentDate().after(loan.getFormattedDueDate());
    }
}
