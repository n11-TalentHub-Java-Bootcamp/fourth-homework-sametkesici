package com.fourthhomework.n11bootcamp.debt.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CreateDebtDto {

    private Double mainDebt;

    private Double remainingDebt;

    private Date dueDate;

    private Long userId;

    private Date createdAt;

}
