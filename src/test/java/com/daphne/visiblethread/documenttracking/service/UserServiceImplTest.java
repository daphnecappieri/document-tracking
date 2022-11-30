package com.daphne.visiblethread.documenttracking.service;

import com.daphne.visiblethread.documenttracking.model.Team;
import com.daphne.visiblethread.documenttracking.model.User;
import com.daphne.visiblethread.documenttracking.model.UserInfo;
import com.daphne.visiblethread.documenttracking.repo.TeamRepository;
import com.daphne.visiblethread.documenttracking.repo.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.io.IOException;
import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;

class UserServiceImplTest {

    private UserService userService;
    private TeamRepository mockTeamRepository;
    private UserRepository mockUserRepository;


    @BeforeEach
    void setup() {
        mockTeamRepository = Mockito.mock(TeamRepository.class);
        mockUserRepository = Mockito.mock(UserRepository.class);
        userService = new UserServiceImpl(mockUserRepository, mockTeamRepository);
    }

    @Test
    void verifySaveUser() throws IOException {

        Mockito.when(mockUserRepository.findByEmail(any())).thenReturn(null);
        Mockito.when(mockTeamRepository.findByName(any())).thenReturn(null);
        Mockito.when(mockUserRepository.save(any())).thenReturn(null);

        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);

        userService.saveUser(createUserInfo());
        Mockito.verify(mockUserRepository, Mockito.times(1))
                .save(argument.capture());

        User user = argument.getValue();
        Assertions.assertEquals("kate", user.getFirstName());
        Assertions.assertEquals("murphy", user.getLastName());

        Team teams = user.getTeams().stream().findFirst().get();
        Assertions.assertEquals("apples", teams.getName());
    }

    @Test
    void verifyUpdateUsersTeams() throws IOException {

        Mockito.when(mockUserRepository.findByEmail(any())).thenReturn(createUser());
        Mockito.when(mockTeamRepository.findByName(any())).thenReturn(null);
        Mockito.when(mockUserRepository.save(any())).thenReturn(null);

        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);

        userService.updateUsersTeams("kate@kate.com", "banana");
        Mockito.verify(mockUserRepository, Mockito.times(1))
                .save(argument.capture());

        User user = argument.getValue();
        Assertions.assertEquals("kate", user.getFirstName());
        Assertions.assertEquals("murphy", user.getLastName());
        Assertions.assertEquals("kate@kate.com", user.getEmail());

    }

    private User createUser() {
        User user = new User();
        user.setFirstName("kate");
        user.setLastName("murphy");
        user.setEmail("kate@kate.com");
        user.setFirstAdded(LocalDate.of(2022, 05, 05));
        return user;
    }

    private UserInfo createUserInfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.setFirstName("kate");
        userInfo.setLastName("murphy");
        userInfo.setEmail("murphy");
        userInfo.setTeam("apples");
        return userInfo;
    }
}