package com.example.loanmanagerapplication.model;

import lombok.Builder;
import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Builder
public class Loan {
    public final String id;
    public final String customerId;
    public final String lenderId;
    public final Double amount;
    public final Double remainingAmount;
    public final String paymentDate;
    public final Double interestPercentagePerDay;
    public final String dueDate;
    public final Double penaltyRatePerDay;

    public Date getFormattedPaymentDate() throws ParseException {
        return new SimpleDateFormat("dd/MM/yyyy").parse(this.paymentDate);
    }
    public Date getFormattedDueDate() throws ParseException {
        return new SimpleDateFormat("dd/MM/yyyy").parse(this.dueDate);
    }
}
