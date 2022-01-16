package com.fourthhomework.n11bootcamp.mapper;

import com.fourthhomework.n11bootcamp.collection.Collection;
import com.fourthhomework.n11bootcamp.collection.dto.CreateCollectionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CreateCollectionMapper extends BaseMapper<Collection, CreateCollectionDto> {

    @Mapping(source = "debt.id" , target = "debtId")
    CreateCollectionDto toDto(Collection collection);

    @Mapping(source = "debtId" , target = "debt.id")
    Collection toEntity(CreateCollectionDto createCollection);

    @Mapping(source = "debtId" , target = "debt.id")
    List<Collection> toEntity(List<CreateCollectionDto> dtoList);

    @Mapping(source = "debt.id" , target = "debtId")
    List<CreateCollectionDto> toDto(List<Collection> entityList);

}
