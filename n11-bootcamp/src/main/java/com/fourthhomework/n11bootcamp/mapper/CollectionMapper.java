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
    @Mapping(source = "user.id" , target = "userId")
    CollectionDto toDto(Collection collection);

    @Mapping(source = "debtId" , target = "debt.id")
    @Mapping(source = "userId", target = "user.id")
    Collection toEntity(CollectionDto collection);

    @Mapping(source = "debtId" , target = "debt.id")
    @Mapping(source = "userId", target = "user.id")
    List<Collection> toEntity(List<CollectionDto> dtoList);

    @Mapping(source = "debt.id" , target = "debtId")
    @Mapping(source = "user.id" , target = "userId")
    List<CollectionDto> toDto(List<Collection> entityList);

}
