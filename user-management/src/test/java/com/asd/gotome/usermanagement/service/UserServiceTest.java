
package com.asd.gotome.usermanagement.service;


import com.asd.gotome.usermanagement.service.UserService;
import com.asd.gotome.usermanagement.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;



//@SpringBootTest
@DataJpaTest
class UserServiceTest {
    @Autowired
    private UserService userService;



    @Test
    void findAllUsersTest() {
        // given

        // when

        // then
    }

    @Test
    void saveUser() {
        User user = new User();
        user.setFirstname("Tobi");
        user.setLastname("Maier");
        user.setUsername("Legend");
        user.setPassword("Maschine");
        User savedUser = userService.saveUser(user);
        assertEquals(user.getFirstname(), savedUser.getFirstname());
        Long id = savedUser.getId();
        assertNotNull(id);
    }



    @Test
    void updateUserTest() {
        // given
        User userOld = new User();
        userOld.setFirstname("firstname1");
        userOld.setLastname("lastname1");
        userOld.setUsername("username1");
        userOld.setPassword("password1");

        User userNew = new User();
        userNew.setFirstname("firstname2");
        userNew.setLastname("lastname2");
        userNew.setUsername("username2");
        userNew.setPassword("password2");

        //User savedUser = userRestController.saveUser(user);
        //assertEquals(user.getFirstname(), savedUser.getFirstname());
        //Long id = savedUser.getId();
        //assertNotNull(id);
        //userService.updateUser(user);

        // when
        //userService.
        // expectet = userService.

        // then
        //assertTrue(expectet);
    }

    @Test
    void findUserByIdTest() {
    }

    @Test
    void deleteUserByIdTest() {
    }

    @Test
    void findByUsernameAndPasswordTest() {
    }

    @Test
    void findByUsernameTest() {
    }

    @Test
    void unlockUserTest() {
    }
}