package com.fourthhomework.n11bootcamp.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void createUser(User user){
        userRepository.save(user);
    }

    public void removeUser(UUID id) {
        userRepository.deleteById(id);
    }

    public User retrieveUser(UUID id) {
        return userRepository.getById(id);
    }

    public List<User> retrieveAllUser() {
        return userRepository.findAll();
    }
}
