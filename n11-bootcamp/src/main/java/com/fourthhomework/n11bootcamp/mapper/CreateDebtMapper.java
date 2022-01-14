package com.fourthhomework.n11bootcamp.mapper;

import com.fourthhomework.n11bootcamp.debt.Debt;
import com.fourthhomework.n11bootcamp.debt.dto.CreateDebtDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CreateDebtMapper extends BaseMapper<Debt, CreateDebtDto> {

    @Mapping(source = "user.id" , target = "userId")
    CreateDebtDto toDto(Debt debt);

    @Mapping(source = "userId" , target = "user.id")
    Debt toEntity(CreateDebtDto createDebtDto);

    @Mapping(source = "userId" , target = "user.id")
    List<Debt> toEntity(List<CreateDebtDto> dtoList);

    @Mapping(source = "user.id" , target = "userId")
    List<CreateDebtDto> toDto(List<Debt> entityList);

}
