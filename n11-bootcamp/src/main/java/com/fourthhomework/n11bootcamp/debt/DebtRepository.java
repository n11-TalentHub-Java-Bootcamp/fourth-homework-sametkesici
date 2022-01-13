package com.fourthhomework.n11bootcamp.debt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface DebtRepository extends JpaRepository<Debt, UUID> {

    List<Debt> findByCreatedAtBetween(Date startDate , Date endDate);

}
