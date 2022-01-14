package com.fourthhomework.n11bootcamp.user.dto;

import com.fourthhomework.n11bootcamp.debt.Debt;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class UserDto {

    private Long id;

    private String name;

    private String lastName;

    private List<Debt> debts;

}
