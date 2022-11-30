package com.daphne.visiblethread.documenttracking.service;

import com.daphne.visiblethread.documenttracking.model.Team;
import com.daphne.visiblethread.documenttracking.model.User;
import com.daphne.visiblethread.documenttracking.model.UserInfo;
import com.daphne.visiblethread.documenttracking.repo.TeamRepository;
import com.daphne.visiblethread.documenttracking.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TeamRepository teamRepository;


    UserServiceImpl(UserRepository userRepository, TeamRepository teamRepository) {
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;

    }

    @Override
    public void saveUser(UserInfo userInfo) {
        User user = userRepository.findByEmail(userInfo.getEmail());
        if (user != null) {
            //ToDo throw exception
            System.out.println("Error, no user fount");
            return;
        }
        user = new User();
        user.setFirstName(userInfo.getFirstName());
        user.setLastName(userInfo.getLastName());
        user.setEmail(userInfo.getEmail());
        user.setFirstAdded(LocalDate.now());

        Team team = teamRepository.findByName(userInfo.getTeam());
        if (team == null) {
            team = new Team();
            team.setName(userInfo.getTeam());
        }
        user.addTeam(team);
        userRepository.save(user);
    }

    @Override
    public void updateUsersTeams(String email, String teamName) {

        User user = userRepository.findByEmail(email);
        if (user == null) {
            //ToDo throw exception
            System.out.println("Error, no user fount");
            return;
        }
        Team team = teamRepository.findByName(teamName);
        if (team == null) {
            team = new Team();
            team.setName(teamName);
        }
        user.addTeam(team);
        userRepository.save(user);
    }


    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
