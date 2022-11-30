package com.daphne.visiblethread.documenttracking.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserInfo {

    private String firstName;
    private String lastName;
    private String email;
    private String team;

}
