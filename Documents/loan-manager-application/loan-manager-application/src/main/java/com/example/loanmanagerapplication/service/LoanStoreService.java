package com.example.loanmanagerapplication.service;

import com.example.loanmanagerapplication.model.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Component("loanStore")
public class LoanStoreService implements LoanStore {
    private List<Loan> loans;

    @Autowired
    public LoanStoreService() {
        loans = new ArrayList<>();
    }

    @Override
    public void save(Loan loan) {
        loans.add(loan);
    }

    @Override
    public List<Loan> getLoansDue(Date date, int pageNo, int pageSize) {
        return loans.stream().filter(loan -> {
            try {
                return loan.getFormattedDueDate().equals(date);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }

    @Override
    public Map<String, List<Loan>> getLoansGroupedByLender() {
        return loans.stream().collect(Collectors.groupingBy(Loan::getLenderId));
    }

    @Override
    public Map<Double, List<Loan>> getLoansGroupedByInterest() {
        return loans.stream().collect(Collectors.groupingBy(Loan::getInterestPercentagePerDay));
    }

    @Override
    public Map<Double, List<Loan>> getLoansGroupedByPenaltyGroup() {
        return loans.stream().collect(Collectors.groupingBy(Loan::getPenaltyRatePerDay));
    }
}
