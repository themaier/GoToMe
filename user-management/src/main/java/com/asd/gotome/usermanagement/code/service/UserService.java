package com.asd.gotome.usermanagement.code.service;

import com.asd.gotome.usermanagement.code.entity.User;
import com.asd.gotome.usermanagement.code.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User findUserById(long id) {
        return userRepository.findById(id).isPresent() ? userRepository.findById(id).get() : null;
    }

    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }
}
