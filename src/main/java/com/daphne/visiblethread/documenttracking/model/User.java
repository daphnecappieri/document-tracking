package com.daphne.visiblethread.documenttracking.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    //    @Id
//    @GeneratedValue
//    @Column(name = "id")
//    private long id;
    @Id
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_added", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate firstAdded;


    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinTable(name = "team_user", joinColumns = @JoinColumn(name = "user_email"), inverseJoinColumns = @JoinColumn(name = "team_name", nullable = false))
    private Set<Team> teams = new HashSet<>();

    public void addTeam(Team team) {
        teams.add(team);
        team.getUsers().add(this);
    }


    public void removeTeam(Team team) {
        teams.remove(team);
        team.getUsers().remove(this);

    }

    @Override
    public String toString() {
        return "User [first_name=" + firstName + ", " +
                "last_name= " + lastName + ", " +
                "email= " + email + ", " +
                "firstAdded=  " + firstAdded + "]";
    }
}
