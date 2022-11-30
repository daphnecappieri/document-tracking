package com.daphne.visiblethread.documenttracking.controller;


import com.daphne.visiblethread.documenttracking.exception.BadRequestException;
import com.daphne.visiblethread.documenttracking.model.User;
import com.daphne.visiblethread.documenttracking.model.UserInfo;
import com.daphne.visiblethread.documenttracking.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@Slf4j
public class UserController {


    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }



    @GetMapping("/users")
    ResponseEntity<List<User>> getUsers() {
        List<User> list = userService.getUsers();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * Create User and add them to a team
     *
     * @param user Users details
     * @return ResponseEntity
     */
    @PostMapping("/users")
    public ResponseEntity<String> saveUser(@RequestBody UserInfo user) {
        try {
            userService.saveUser(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (BadRequestException exc) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Cannot create user. " + exc.getMessage());
        }
    }


    /**
     * Add user to new team
     *
     * @param email Email address of user
     * @param teamName Name of team
     * @return ResponseEntity
     */
    @PutMapping("/changeTeam")
    public ResponseEntity<String>  updateUserTeam(@RequestParam("email") String email, @RequestParam("teamName") String teamName) {
        try {
            userService.updateUsersTeams(email, teamName);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (BadRequestException exc) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Cannot update users team. " + exc.getMessage());
        }
    }
}
