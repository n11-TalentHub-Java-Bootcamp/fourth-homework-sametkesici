package com.fourthhomework.n11bootcamp.mapper;

import com.fourthhomework.n11bootcamp.user.User;
import com.fourthhomework.n11bootcamp.user.dto.SaveUserDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CreateUserMapper extends BaseMapper<User, SaveUserDto> {

    SaveUserDto toDto(User user);

    User toEntity(SaveUserDto saveUserDto);

    List<User> toEntity(List<SaveUserDto> dtoList);

    List<SaveUserDto> toDto(List<User> entityList);


}
