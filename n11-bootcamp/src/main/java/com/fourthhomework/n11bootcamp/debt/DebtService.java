package com.fourthhomework.n11bootcamp.debt;

import com.fourthhomework.n11bootcamp.constant.DebtTypeConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

import static com.fourthhomework.n11bootcamp.constant.DebtTypeConstants.NORMAL;

@Service
@Transactional
@RequiredArgsConstructor
public class DebtService {


    private final DebtRepository debtRepository;

    public void createDebt(Debt debt) {
        debt.setDebtType(NORMAL);
        debtRepository.save(debt);
    }

    public List<Debt> retrieveDebtsByCreatedDate(Date startedDate, Date endDate) {
      return debtRepository.findByCreatedAtBetween(startedDate,endDate);
    }
}
