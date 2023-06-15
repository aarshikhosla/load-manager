package com.example.loanmanagerapplication.service;

import com.example.loanmanagerapplication.exception.InvalidLoanRequestException;
import com.example.loanmanagerapplication.model.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class LoanService {
    @Autowired
    @Qualifier("validationService")
    private IValidator validator;

    @Autowired
    @Qualifier("loanStore")
    private LoanStore loanStore;

    public void storeLoan(Loan loan) throws Exception{
        if(validator.isValidLoanRequest(loan)){
            loanStore.save(loan);
        }
        else throw new InvalidLoanRequestException("Loan details are invalid, payment date can't exceed due date");
    }
}
