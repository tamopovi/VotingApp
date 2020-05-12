package com.votingapp.votingapp.Repository;

import com.votingapp.votingapp.Entity.GeographicRegions;
import com.votingapp.votingapp.Entity.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoterRepository extends JpaRepository<Voter, Long> {
    List<Voter> findAllVotersByRegion(GeographicRegions region);

}
