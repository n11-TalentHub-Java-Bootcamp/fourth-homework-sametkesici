package com.fourthhomework.n11bootcamp.mapper;

import com.fourthhomework.n11bootcamp.debt.Debt;
import com.fourthhomework.n11bootcamp.debt.dto.DebtDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DebtMapper extends BaseMapper<Debt, DebtDto>{

    DebtDto toDto(Debt debt);

    Debt toEntity(DebtDto debtDto);

    List<Debt> toEntity(List<DebtDto> dtoList);

    List<DebtDto> toDto(List<Debt> entityList);
}
