package com.daphne.visiblethread.documenttracking.service;

import com.daphne.visiblethread.documenttracking.model.User;
import com.daphne.visiblethread.documenttracking.model.UserInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface UserService {

    void saveUser(UserInfo user);

    List<User> getUsers();

    void updateUsersTeams(String user, String teamName);
}
