package com.asd.gotome.usermanagement.controller;

import com.asd.gotome.usermanagement.entity.User;
import com.asd.gotome.usermanagement.repo.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRestControllerTest {
    @Autowired
    private UserRestController userRestController;

    @Autowired
    UserRepository userRepository;

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

    @Test
    public void loginUser() {
        User user = new User();
        user.setFirstname("Tobi");
        user.setLastname("Maier");
        user.setUsername("Legend");
        user.setPassword("Maschine");
        User savedUser = userRestController.saveUser(user);
        assertEquals(user.getFirstname(), savedUser.getFirstname());
        Long id = savedUser.getId();
        assertNotNull(id);

        assertEquals(userRestController.login("Legend", "Maschine"), "Erfolgreich angemeldet als Legend");
    }

    @Test
    public void unlockUser() {
        User user = new User();
        user.setFirstname("Tobi");
        user.setLastname("Maier");
        user.setUsername("Legend");
        user.setPassword("Maschine");
        User savedUser = userRestController.saveUser(user);
        assertEquals(user.getFirstname(), savedUser.getFirstname());
        Long id = savedUser.getId();
        assertNotNull(id);
        assertEquals(userRestController.login("Legend", "FalschesPasswort"), "Benutzername oder Passwort nicht korrekt");
        assertEquals(userRestController.login("Legend", "FalschesPasswort"), "Benutzername oder Passwort nicht korrekt");
        String res = userRestController.login("Legend", "FalschesPasswort");
        assertTrue(res.contains("gesperrt"));
        try {
            Thread.sleep(65000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertEquals(userRestController.login("Legend", "Maschine"), "Erfolgreich angemeldet als Legend");
    }

    @Test
    public void getUsers() {
        User user = new User();
        user.setFirstname("Tobi");
        user.setLastname("Maier");
        user.setUsername("Legend");
        user.setPassword("Maschine");
        User savedUser = userRestController.saveUser(user);
        assertEquals(user.getFirstname(), savedUser.getFirstname());
        Long id = savedUser.getId();
        assertNotNull(id);
        List<User> users = userRestController.findAllUsers();
        assertTrue(users.stream().anyMatch(o -> o.getUsername().equals("Legend")));
    }

    @Test
    public void logout() {
        assertEquals(userRestController.logout(), "Abmeldung ohne angemeldeten User nicht möglich");
        User user = new User();
        user.setFirstname("Tobi");
        user.setLastname("Maier");
        user.setUsername("Legend");
        user.setPassword("Maschine");
        User savedUser = userRestController.saveUser(user);
        assertEquals(user.getFirstname(), savedUser.getFirstname());
        Long id = savedUser.getId();
        assertNotNull(id);

        assertEquals(userRestController.login("Legend", "Maschine"), "Erfolgreich angemeldet als Legend");
        assertEquals(userRestController.logout(), "Abgemeldet");
    }

    @Test
    public void changePassword() {
        assertEquals(userRestController.changePassword("Test", "Test", "Maschine"), "Passwortänderung ohne angemeldeten User nicht möglich");
        User user = new User();
        user.setFirstname("Tobi");
        user.setLastname("Maier");
        user.setUsername("Legend");
        user.setPassword("Maschine");
        User savedUser = userRestController.saveUser(user);
        assertEquals(user.getFirstname(), savedUser.getFirstname());
        Long id = savedUser.getId();
        assertNotNull(id);
        assertEquals(userRestController.login("Legend", "Maschine"), "Erfolgreich angemeldet als Legend");
        assertEquals(userRestController.changePassword("Test", "Test", "Maschine"), "Passwort erfolgreich geändert");
    }

    @Test
    public void deleteUser() {
        assertEquals(userRestController.deleteUser("Maschine"), "Account löschen ohne angemeldeten User nicht möglich");
        User user = new User();
        user.setFirstname("Tobi");
        user.setLastname("Maier");
        user.setUsername("Legend");
        user.setPassword("Maschine");
        User savedUser = userRestController.saveUser(user);
        assertEquals(user.getFirstname(), savedUser.getFirstname());
        Long id = savedUser.getId();
        assertNotNull(id);
        assertEquals(userRestController.login("Legend", "Maschine"), "Erfolgreich angemeldet als Legend");
        assertEquals(userRestController.deleteUser("FalschesPasswort"), "Passwort ist nicht korrekt");
        assertEquals(userRestController.deleteUser("Maschine"), "Account erfolgreich gelöscht");
    }

    @AfterEach
    public void revert() {
        userRestController.logout();
        userRepository.deleteAll();
    }

}