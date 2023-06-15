package com.example.loanmanagerapplication;

import com.example.loanmanagerapplication.model.Loan;
import com.example.loanmanagerapplication.service.LoanService;
import com.example.loanmanagerapplication.service.LoanStore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.validation.Validator;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class LoanServiceTest {
    @Mock
    private LoanService loanService;

    @Mock
    private LoanStore loanStore;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void addLoanTest() throws Exception {

        Loan loan = Loan.builder().id("L1")
                .customerId("C1")
                .amount(10000.0)
                .remainingAmount(10000.0)
                .paymentDate("05/06/2023")
                .interestPercentagePerDay(1.0)
                .dueDate("05/07/2023")
                .penaltyRatePerDay(0.01)
                .build();
        Map<Double,List<Loan>> map=new HashMap<>();
        map.put(0.01, Collections.singletonList(loan));
        when(loanStore.getLoansGroupedByPenaltyGroup()).thenReturn(map);

        loanService.storeLoan(loan);
        List<Loan> loans = loanStore.getLoansGroupedByPenaltyGroup().get(0.01).stream().filter(loan1 -> loan1.getId().equals("L1")).collect(Collectors.toList());
        Assertions.assertEquals(1, loans.size());

    }
}
