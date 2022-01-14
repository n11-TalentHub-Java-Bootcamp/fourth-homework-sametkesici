package com.fourthhomework.n11bootcamp.debt;

import com.fourthhomework.n11bootcamp.constant.DebtTypeConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

import static com.fourthhomework.n11bootcamp.constant.DebtTypeConstants.NORMAL;

@Service
@RequiredArgsConstructor
public class DebtService {


    private final DebtRepository debtRepository;

    @Transactional
    public void createDebt(Debt debt) {
        debt.setDebtType(NORMAL);
        debtRepository.save(debt);
    }

    public List<Debt> retrieveDebtsByCreatedDate(Date startedDate, Date endDate) {
      return debtRepository.findByCreatedAtBetween(startedDate,endDate);
    }

    public List<Debt> retrieveDebtsByUser(Long id){
        return debtRepository.findDebtByUserId(id);
    }

    public List<Debt> findDebtsByOverDueAndUser(Date dueDate , Long id){
        return debtRepository.findByDueDateBeforeAndUserId(dueDate , id);
    }

    public Debt retrieveDebtsById(Long id){
        return debtRepository.findById(id).get();
    }



}
