package com.example.loanmanagerapplication.scheduler;

import com.example.loanmanagerapplication.model.Loan;
import com.example.loanmanagerapplication.service.LoanStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.swing.text.DateFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@EnableScheduling
public class LoanDueScheduler {
    @Autowired
    @Qualifier("loanStore")
    private LoanStore loanStore;

    @Scheduled(fixedDelay = 1 ,timeUnit = TimeUnit.DAYS)
    public void scheduleFixedDelayTask() {
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        calendar.add(Calendar.DATE, -1);
        int pageNo = 0;
        List<Loan> loans = new ArrayList<>();
        do {
            loans = loanStore.getLoansDue(calendar.getTime(), pageNo, 10);
            loans.forEach(loan ->
                    System.out.println("loan id {} still due" + loan.getId()));
        } while (!loans.isEmpty());
    }
}
