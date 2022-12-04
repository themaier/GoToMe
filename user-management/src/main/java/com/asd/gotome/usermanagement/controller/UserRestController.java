package com.asd.gotome.usermanagement.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.asd.gotome.usermanagement.entity.User;
import com.asd.gotome.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class UserRestController {

    @Autowired
    UserService userService;

    private static User user;

    @GetMapping("/users")
    public List<User> findAllUsers() {
        List<User> users = userService.findAllUsers();
        System.out.println(users.toString());
        return users;
    }

    @PostMapping("/saveUser")
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        User userWithUsername = userService.findByUsername(username);
        if (userWithUsername.getLockDateAndTime() == null) {
            User user = userService.findByUsernameAndPassword(username, password);
            if (user == null) {
                if (userWithUsername.getFailedLogin() < 3) {
                    userWithUsername.setFailedLogin(userWithUsername.getFailedLogin() + 1);
                    userService.updateUser(userWithUsername);
                    return "Benutzername oder Passwort nicht korrekt";
                } else {
                    userWithUsername.setFailedLogin(userWithUsername.getFailedLogin() + 1);
                    Date date = new Date(System.currentTimeMillis() + 60000L);
                    userWithUsername.setLockDateAndTime(date);
                    userService.updateUser(userWithUsername);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.YYYY hh:mm");
                    return "Benutzer bis zum " + simpleDateFormat.format(userWithUsername.getLockDateAndTime()) + " Uhr gesperrt";
                }
            }
        } else {
            if (!userService.unlockUser(userWithUsername)) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.YYYY hh:mm");
                return "Benutzer bis zum " + simpleDateFormat.format(userWithUsername.getLockDateAndTime()) + " Uhr gesperrt";
            } else {
                return login(username, password);
            }
        }
        user = userWithUsername;
        return "Erfolgreich angemeldet als " + user.getUsername();
    }

    @GetMapping("/logout")
    public String logout() {
        if (user != null) {
            user = null;
            return "Abgemeldet";
        }
        return "Abmeldung ohne angemeldeten User nicht möglich";
    }

    @GetMapping("/changePassword")
    public String changePassword(@RequestParam String password, @RequestParam String repeatedPassword, @RequestParam String oldPassword) {
        if (user != null) {
            BCrypt.Result result = BCrypt.verifyer().verify(oldPassword.toCharArray(), user.getPassword());
            if (result.verified) {
                if (password.equals(repeatedPassword)) {
                    user.setPassword(BCrypt.withDefaults().hashToString(12, password.toCharArray()));
                    userService.updateUser(user);
                } else {
                    return "Passwörter stimmen nicht überein";
                }
            } else {
                return "Passwort ist nicht korrekt";
            }
            return "Passwort erfolgreich geändert";
        }
        return "Passwortänderung ohne angemeldeten User nicht möglich";
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestParam String password) {
        if (user != null) {
            if (user.getPassword().equals(password)) {
                userService.deleteUserById(user.getId());
                return "Account erfolgreich gelöscht";
            } else {
                return "Passwort ist nicht korrekt";
            }
        }
        return "Account löschen ohne angemeldeten User nicht möglich";
    }
}
