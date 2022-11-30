package com.daphne.visiblethread.documenttracking.service;

import com.daphne.visiblethread.documenttracking.model.Team;
import com.daphne.visiblethread.documenttracking.model.User;
import com.daphne.visiblethread.documenttracking.model.UserInfo;

import java.util.List;


public interface TeamService {

    void saveTeam(Team team);

    public List<Team> getTeam();
}
