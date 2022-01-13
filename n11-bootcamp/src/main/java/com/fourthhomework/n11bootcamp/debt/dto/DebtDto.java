package com.fourthhomework.n11bootcamp.debt.dto;

import lombok.Data;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.UUID;


@Data
public class DebtDto {

    private UUID id;

    private Double mainDebt;

    private Double remainingDebt;

    @Temporal(TemporalType.DATE)
    private Date dueDate;

    private UUID userId;

    @Temporal(TemporalType.DATE)
    private Date createdAt;

}
