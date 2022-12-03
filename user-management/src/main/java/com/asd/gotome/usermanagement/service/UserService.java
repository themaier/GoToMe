package com.asd.gotome.usermanagement.service;

import com.asd.gotome.usermanagement.entity.User;
import com.asd.gotome.usermanagement.repo.UserRepository;
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

    public User saveUser(User user) {
        User savedUser = null;
        if (userRepository.findByUsername(user.getUsername()) == null) {
            savedUser = userRepository.save(user);
            System.out.println("save successful for " + savedUser.getFirstname() + " " + savedUser.getLastname() + " with Id " + savedUser.getId());
        } else {
            System.out.println("Der Benutzername ist bereits vergeben“");
        }
        return savedUser;
    }

    public void updateUser(User user) {
        User savedUser = userRepository.save(user);
        System.out.println("update successful for " + savedUser.getFirstname() + " " + savedUser.getLastname() + " with Id " + savedUser.getId());
    }

    public User findUserById(long id) {
        return userRepository.findById(id).isPresent() ? userRepository.findById(id).get() : null;
    }

    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }

    public User findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean unlockUser(User user) {
        long userLockTimeMillis = user.getLockDateAndTime().getTime();
        long systemCurrentTimeMillis = System.currentTimeMillis();
        boolean unlocked = false;
        if (userLockTimeMillis < systemCurrentTimeMillis) {
            user.setLockDateAndTime(null);
            user.setFailedLogin(0);
            userRepository.save(user);
            unlocked = true;
        }
        return unlocked;
    }
}
