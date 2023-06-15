package com.example.loanmanagerapplication.controller;

import com.example.loanmanagerapplication.enums.GroupType;
import com.example.loanmanagerapplication.exception.InvalidLoanRequestException;
import com.example.loanmanagerapplication.model.Loan;
import com.example.loanmanagerapplication.service.LoanService;
import com.example.loanmanagerapplication.service.LoanStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoanManagerController {
    @Autowired
    private LoanService loanService;

    @Autowired
    private LoanStore loanStore;

    @PostMapping("/save")
    public ResponseEntity<String> saveLoan(@RequestBody Loan loan){
        try {
            loanService.storeLoan(loan);
            return ResponseEntity.ok("Loan stored");
        }
        catch (InvalidLoanRequestException invalidLoanRequestException){
            return ResponseEntity.badRequest().body("invalid Request "+ invalidLoanRequestException.getMessage());

        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().body("something went wrong");
        }
    }
    @GetMapping("/loans")
    public ResponseEntity<Map<?,?>> getLoans(@RequestParam GroupType groupType){
        switch (groupType){
            case LENDER:
                return ResponseEntity.ok(loanStore.getLoansGroupedByLender());
            case PENALTY_GROUP:
                return ResponseEntity.ok(loanStore.getLoansGroupedByPenaltyGroup());
            case INTEREST:
                return ResponseEntity.ok(loanStore.getLoansGroupedByInterest());
            default:
                return ResponseEntity.internalServerError().body(null);

        }

    }
}
