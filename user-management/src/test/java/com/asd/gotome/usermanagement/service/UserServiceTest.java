package com.asd.gotome.usermanagement.service;

import com.asd.gotome.usermanagement.controller.UserRestController;
import com.asd.gotome.usermanagement.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserRestController userRestController;

    @Test
    public void saveUserTest() {
        User user = new User();
        user.setFirstname("Tobi");
        user.setLastname("Maier");
        user.setUsername("Legend");
        user.setPassword("Maschine");
        User savedUser = userRestController.saveUser(user);
        assertEquals(user.getFirstname(), savedUser.getFirstname());
        Long id = savedUser.getId();
        assertNotNull(id);
    }
}