package com.fourthhomework.n11bootcamp.util;

import lombok.AllArgsConstructor;
import lombok.experimental.UtilityClass;

import java.util.Calendar;
import java.util.Date;

import static com.fourthhomework.n11bootcamp.constant.LateFeeConstants.LATE_FEE_AFTER;
import static com.fourthhomework.n11bootcamp.constant.LateFeeConstants.LATE_FEE_BEFORE;

@AllArgsConstructor
public final class DateUtils {

    public static Double calculateLateFee(Date dueDate , Date createdDate , Double mainDebt){

        if(dueDate.after(createdDate)){
            return 0.0;
        }

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

    public static Long daysBetween(Calendar startDate, Calendar endDate) {
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
