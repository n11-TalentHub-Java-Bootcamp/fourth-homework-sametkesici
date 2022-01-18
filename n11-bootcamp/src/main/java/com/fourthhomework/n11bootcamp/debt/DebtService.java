package com.fourthhomework.n11bootcamp.debt;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

import static com.fourthhomework.n11bootcamp.constant.DebtTypeConstants.LATE_FEE;
import static com.fourthhomework.n11bootcamp.constant.DebtTypeConstants.NORMAL;
import static com.fourthhomework.n11bootcamp.util.DateUtils.calculateLateFee;

@Service
@RequiredArgsConstructor
public class DebtService {

    private final DebtRepository debtRepository;

    @Transactional
    public void createDebt(Debt debt) {
        debt.setDebtType(NORMAL);
        debt.setRemainingDebt(debt.getMainDebt());
        debtRepository.save(debt);
    }

    public void updateDebt(Debt debt){
        debtRepository.save(debt);
    }

    @Transactional
    public void createDebtWithLateFee(Debt debt){
        debt.setDebtType(LATE_FEE);
        debtRepository.save(debt);
    }

    public List<Debt> retrieveDebtsByCreatedDate(Date startedDate, Date endDate) {
      return debtRepository.findByCreatedAtBetween(startedDate,endDate);
    }

    public List<Debt> retrieveDebtsByUser(Long id){
        return debtRepository.findDebtByUserIdAndRemainingDebtGreaterThan(id,0.0);
    }

    public List<Debt> findDebtsByOverDueAndUser(Long id){
        Date createdDate = new Date();

        return debtRepository.findByDueDateBeforeAndUserIdAndRemainingDebtGreaterThan(createdDate , id , 0.0);
    }

    public Debt retrieveDebtsById(Long id){
        return debtRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("kullanici bulunamadi"));
    }

    public Long getAllDebtAmountByUserId(Long userId){
        return debtRepository.sumDebtsFromUserId(userId);
    }

    public Double getTotalLateFeeAmountByUserId(Long userId , String debtType){

        Date createdDate = new Date();

        List<Debt> debts = debtRepository.findByDebtTypeEqualsAndUserId(debtType,userId);

        return debts.stream().mapToDouble(x -> calculateLateFee(x.getDueDate(),createdDate , x.getMainDebt())).sum();
    }


    public Double getAmountLateFee(Long userId, String debtType){
        return  debtRepository.findByUserIdAndDebtType(userId,debtType);
    }

    public Double getAmountLateFeeByOverDueAndUser(Long userId){
        Date createdDate = new Date();
        List<Debt> debtsList  = findDebtsByOverDueAndUser(userId);
        return debtsList.stream().mapToDouble(x -> calculateLateFee(x.getDueDate() , createdDate , x.getMainDebt())).sum();
    }
}
