package ru.marina.shop.service;

import org.springframework.stereotype.Service;
import ru.marina.shop.entity.User;
import ru.marina.shop.repository.UserRepository;

@Service
public class UserService {
private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void addUser(User user){
        userRepository.save(user);
    }
}
