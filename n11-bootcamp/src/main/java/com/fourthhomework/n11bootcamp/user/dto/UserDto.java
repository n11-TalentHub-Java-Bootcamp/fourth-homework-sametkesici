package com.fourthhomework.n11bootcamp.user.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserDto {

    private UUID id;

    private String name;

    private String lastname;

}
