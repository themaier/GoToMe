package com.asd.gotome.usermanagement.controller;

import com.asd.gotome.usermanagement.entity.User;
import com.asd.gotome.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserRestController {

    @Autowired
    UserService userService;

    @GetMapping("/user")
    public String test(Model model) {
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "user";
    }
}
