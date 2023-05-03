package com.sstep.demo.controller;

import com.sstep.demo.domain.User;
import com.sstep.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public User Register(@RequestBody User user){
        return userService.save(user);
    }

}
