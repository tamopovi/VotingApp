package com.votingapp.votingapp.Entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Candidate {
    public Candidate() {

    }

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Column(unique = true)
    private Long numberInList;
    private String agendaSummary;
}
