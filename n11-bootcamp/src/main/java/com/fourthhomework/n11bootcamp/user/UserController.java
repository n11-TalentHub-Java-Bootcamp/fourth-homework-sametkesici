package com.fourthhomework.n11bootcamp.user;

import com.fourthhomework.n11bootcamp.mapper.CreateUserMapper;
import com.fourthhomework.n11bootcamp.mapper.UserMapper;
import com.fourthhomework.n11bootcamp.user.dto.SaveUserDto;
import com.fourthhomework.n11bootcamp.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    private final CreateUserMapper createUserMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createUser(@RequestBody SaveUserDto saveUserDto){
        User user = createUserMapper.toEntity(saveUserDto);
        userService.createUser(user);
        return ResponseEntity.ok("User successfully saved");
    };

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeUser(@PathVariable Long id){
        userService.removeUser(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retrieveUser(@PathVariable Long id){
        UserDto userDto = userMapper.toDto(userService.retrieveUser(id));
        return ResponseEntity.ok(userDto);
    }

    @GetMapping
    public ResponseEntity<?> retrieveAllUser(){
        List<UserDto> userDto = userMapper.toDto(userService.retrieveAllUser());
        return ResponseEntity.ok(userDto);
    }
}
