package com.fourthhomework.n11bootcamp.mapper;

import com.fourthhomework.n11bootcamp.collection.Collection;
import com.fourthhomework.n11bootcamp.collection.dto.CollectionDto;
import com.fourthhomework.n11bootcamp.collection.dto.CreateCollectionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CollectionMapper extends BaseMapper <Collection, CollectionDto> {

    @Mapping(source = "debt.id" , target = "debtId")
    CollectionDto toDto(Collection collection);

    @Mapping(source = "debt" , target = "debt.id")
    Collection toEntity(CollectionDto collection);

    @Mapping(source = "debt" , target = "debt.id")
    List<Collection> toEntity(List<CollectionDto> dtoList);

    @Mapping(source = "debt.id" , target = "debtId")
    List<CollectionDto> toDto(List<Collection> entityList);

}
