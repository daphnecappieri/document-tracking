package com.daphne.visiblethread.documenttracking.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "teams")
@Getter
@Setter
public class Team {
    //    @Id
//    @GeneratedValue
//    private long id;
    @Id
    @Column(name = "team_name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "teams")
    private Set<User> users = new HashSet<>();

    @Override
    public String toString() {
        return "Team [name=" + name + "]";
    }


//    public void addUser(User user) {
//        this.users.add(user);
//        user.getTeams().add(this);
//    }
//
//    public void removeUser(long teamId) {
//        User user = this.users.stream().filter(t -> t.getId() == teamId).findFirst().orElse(null);
//        if (user != null) {
//            this.users.remove(user);
//            user.getTeams().remove(this);
//        }
//    }

}
