package com.fourthhomework.n11bootcamp.debt;

import com.fourthhomework.n11bootcamp.collection.CollectionService;
import com.fourthhomework.n11bootcamp.constant.DebtTypeConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.fourthhomework.n11bootcamp.constant.DebtTypeConstants.LATE_FEE;
import static com.fourthhomework.n11bootcamp.constant.DebtTypeConstants.NORMAL;
import static com.fourthhomework.n11bootcamp.constant.LateFeeConstants.LATE_FEE_AFTER;
import static com.fourthhomework.n11bootcamp.constant.LateFeeConstants.LATE_FEE_BEFORE;

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
        return debtRepository.findById(id).get();
    }

    public Long getAllDebtAmountByUserId(Long userId){
        return debtRepository.sumDebtsFromUserId(userId);
    }

    public Double getTotalLateFeeAmountByUserId(Long userId , String debtType){

        Date createdDate = new Date();

        List<Debt> debts = debtRepository.findByDebtTypeEqualsAndUserId(debtType,userId);

        return debts.stream().mapToDouble(x -> calculateLateFee(x.getDueDate(),createdDate , x.getMainDebt())).sum();
    }

    public Double calculateLateFee(Date dueDate , Date createdDate , Double mainDebt){

        Calendar calendar = Calendar.getInstance();

        calendar.set(2018, Calendar.JANUARY,1);

        long diffInDaysDueDateAndNow = daysBetween(toCalendar(dueDate),toCalendar(createdDate));

        long diffInDaysDueDateAndCalendar = daysBetween(toCalendar(dueDate) , calendar);

        long diffInDaysCalendarAndCreatedDate = daysBetween(calendar, toCalendar(createdDate));

        if (toCalendar(dueDate).after(calendar))
        {
            return diffInDaysDueDateAndNow * mainDebt * LATE_FEE_AFTER / 100 ;
        }else {
            return diffInDaysDueDateAndCalendar * mainDebt * LATE_FEE_BEFORE / 100 + diffInDaysCalendarAndCreatedDate * mainDebt * LATE_FEE_AFTER / 100;
        }
    }

    public Double getAmountLateFee(Long userId, String debtType){
        return  debtRepository.findByUserIdAndDebtType(userId,debtType);
    }

    public static long daysBetween(Calendar startDate, Calendar endDate) {
        Calendar date = (Calendar) startDate.clone();
        long daysBetween = 0;
        while (date.before(endDate)) {
            date.add(Calendar.DAY_OF_MONTH, 1);
            daysBetween++;
        }
        return daysBetween-1;
    }

    public static Calendar toCalendar(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }


}
