package com.example.loanmanagerapplication.service;

import com.example.loanmanagerapplication.model.Loan;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface LoanStore {
    void save(Loan loan);

    List<Loan> getLoansDue(Date date, int pageNo, int pageSize);

    Map<String, List<Loan>> getLoansGroupedByLender();

    Map<Double, List<Loan>> getLoansGroupedByInterest();

    Map<Double, List<Loan>> getLoansGroupedByPenaltyGroup();

}
