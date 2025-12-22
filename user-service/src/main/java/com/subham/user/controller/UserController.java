package com.subham.user.controller;

import com.subham.user.VO.ResponseTemplateVO;
import com.subham.user.entity.User;
import com.subham.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Slf4j
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/")
    public User saveUser(@RequestBody User user) {
        log.info("Inside saveUser of UserController");
        return userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public ResponseTemplateVO getUserWithDepartment(@PathVariable("id") Long userId)  {
        //Thread.sleep(5000); // for testing circuit breaker open, close and half open
        //Thread.sleep(2000);
        log.info("Inside getUserWithDepartment of UserController");
        return userService.getUserWithDepartment(userId);
    }
}
