package com.fourthhomework.n11bootcamp.mapper;

import com.fourthhomework.n11bootcamp.user.User;
import com.fourthhomework.n11bootcamp.user.dto.SaveUserDto;
import com.fourthhomework.n11bootcamp.user.dto.UserDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserDto> {

    UserDto toDto(User user);

    User toEntity(UserDto userDto);

    List<User> toEntity(List<UserDto> dtoList);

    List<UserDto> toDto(List<User> entityList);

}
