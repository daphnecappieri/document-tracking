package com.daphne.visiblethread.documenttracking.service;

import com.daphne.visiblethread.documenttracking.model.Team;
import com.daphne.visiblethread.documenttracking.model.TeamInfo;
import com.daphne.visiblethread.documenttracking.model.User;
import com.daphne.visiblethread.documenttracking.model.UserInfo;
import com.daphne.visiblethread.documenttracking.repo.TeamRepository;
import com.daphne.visiblethread.documenttracking.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Slf4j
@Service
public class TeamServiceImpl implements TeamService {

    private final UserRepository userRepository;
    private final TeamRepository teamRepository;

    TeamServiceImpl(UserRepository userRepository, TeamRepository teamRepository) {
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public void saveTeam(Team team) {
        teamRepository.save(team);
    }

    @Override
    public List<Team> getTeam() {
        List<Team> list = teamRepository.findAll();
        return list;
    }
}
