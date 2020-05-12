package com.votingapp.votingapp.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Setter
@Getter
public class Voter {
    public Voter() {

    }

    @Id
    private String identification;
    private GeographicRegions region;
    private Long votingFor;
}
