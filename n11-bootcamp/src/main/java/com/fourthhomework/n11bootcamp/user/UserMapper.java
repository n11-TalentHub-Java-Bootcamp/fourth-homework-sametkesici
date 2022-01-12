package com.fourthhomework.n11bootcamp.user;

import com.fourthhomework.n11bootcamp.mapper.BaseMapper;
import com.fourthhomework.n11bootcamp.user.dto.UserDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserDto> {

    UserDto toDto(User customer);

    User toEntity(UserDto customerDto);

    List<User> toEntity(List<UserDto> dtoList);

    List<UserDto> toDto(List<User> entityList);

}
