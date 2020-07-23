package com.ling.hr.api.controller;

import com.ling.hr.api.service.UserService;
import com.ling.hr.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "save")
    public void save(User user) {
        this.userService.save(user);
    }
}




