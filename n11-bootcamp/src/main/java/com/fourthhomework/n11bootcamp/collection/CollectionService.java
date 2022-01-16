package com.fourthhomework.n11bootcamp.collection;


import com.fourthhomework.n11bootcamp.debt.Debt;
import com.fourthhomework.n11bootcamp.debt.DebtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.fourthhomework.n11bootcamp.constant.DebtTypeConstants.LATE_FEE;
import static com.fourthhomework.n11bootcamp.constant.LateFeeConstants.LATE_FEE_AFTER;
import static com.fourthhomework.n11bootcamp.constant.LateFeeConstants.LATE_FEE_BEFORE;


@Service
@RequiredArgsConstructor
public class CollectionService {

    private final CollectionRepository collectionRepository;

    private final DebtService debtService;
    
    @Transactional
    public Collection makeCollection(Collection collection){

        Date createdDate = new Date();

        Debt mainDebt = debtService.retrieveDebtsById(collection.getDebt().getId());
        mainDebt.setRemainingDebt(0.0);
        debtService.updateDebt(mainDebt);

        collection.setCreatedAt(createdDate);
        collection.setUser(mainDebt.getUser());
        collection.setCreatedAt(createdDate);

        if(mainDebt.getDueDate().before(createdDate)){
            createDebtWithLateFee(mainDebt , createdDate);

            Double collectionAmount = mainDebt.getMainDebt() + calculateLateFee(mainDebt.getDueDate(),createdDate,mainDebt.getMainDebt());
            collection.setCollectionAmount(collectionAmount);
        }else{
            collection.setCollectionAmount(mainDebt.getMainDebt());
        }
        return collectionRepository.save(collection);
    }

    public List<Collection> retrieveCollectionsByCreatedAt(Date startedDate, Date endDate) {
        return collectionRepository.findByCreatedAtBetween(startedDate,endDate);
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


    public void createDebtWithLateFee (Debt mainDebt , Date createdDate){
        Debt debtWithLateFee = Debt.builder()
                .mainDebt(calculateLateFee(mainDebt.getDueDate(),createdDate,mainDebt.getMainDebt()))
                .remainingDebt(0.0)
                .debt(mainDebt)
                .user(mainDebt.getUser())
                .createdAt(createdDate)
                .build();
        debtService.createDebtWithLateFee(debtWithLateFee);
    }

    public List<Collection> retrieveCollectionsByUserId(Long userId) {
        return collectionRepository.findByUserId(userId);
    }

    public Double getLateFeeAmountByUser(Long userId) {
        return debtService.getAmountLateFee(userId,LATE_FEE);
    }


    public static Calendar toCalendar(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
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
}
