package com.fourthhomework.n11bootcamp.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void createUser(User user){
        userRepository.save(user);
    }

    @Transactional
    public void removeUser(Long id) {
        userRepository.deleteById(id);
    }

    public User retrieveUser(Long id) {
        return userRepository.getById(id);
    }

    public List<User> retrieveAllUser() {
        return userRepository.findAll();
    }
}
