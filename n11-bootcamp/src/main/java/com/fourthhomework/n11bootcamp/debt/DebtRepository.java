package com.fourthhomework.n11bootcamp.debt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface DebtRepository extends JpaRepository<Debt, Long> {

    List<Debt> findByCreatedAtBetween(Date startDate , Date endDate);

    List<Debt> findByDueDateBeforeAndUserIdAndRemainingDebtGreaterThan(Date dueDate , Long UserId , Double debt);

    List<Debt> findDebtByUserIdAndRemainingDebtGreaterThan(Long id , Double debt);

    List<Debt> findByDebtTypeEqualsAndUserIdAndRemainingDebtGreaterThan(String DebtType , Long Id , Double remaningDebt);

    @Query("select sum (mainDebt) from Debt where user.id = :userId and debtType = :debtId")
    Double findByUserIdAndDebtType(Long userId , String debtId);

    @Query("select sum(remainingDebt) from Debt where user.id = :userId")
    Long sumDebtsFromUserId(Long userId);

}
