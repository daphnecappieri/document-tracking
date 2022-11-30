package com.daphne.visiblethread.documenttracking.controller;


import com.daphne.visiblethread.documenttracking.model.User;
import com.daphne.visiblethread.documenttracking.model.UserInfo;
import com.daphne.visiblethread.documenttracking.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class UserController {


    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users")
    List<User> getUsers() {

        List<User> list = userService.getUsers();
        return list;
    }


    @PostMapping("/users")
    public void saveUser(@RequestBody UserInfo user) {

        userService.saveUser(user);
    }


    @PutMapping("/changeTeam")
    public void updateUserTeam(@RequestParam("email") String email, @RequestParam("teamName") String teamName) {
        userService.updateUsersTeams(email, teamName);
    }
}
