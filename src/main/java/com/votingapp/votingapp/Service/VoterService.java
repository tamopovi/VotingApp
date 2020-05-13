package com.votingapp.votingapp.Service;

import com.votingapp.votingapp.Entity.GeographicRegions;
import com.votingapp.votingapp.Entity.Voter;
import com.votingapp.votingapp.Repository.VoterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@RequestMapping("/api")
public class VoterService {
    @Autowired
    private VoterRepository voterRepository;

    @Autowired
    CandidateService candidateService;


    public void vote(Voter voter) throws Exception {
        // check if candidate number exists, if not, do not let to vote
        boolean candidateExists = candidateService.getAllCandidates()
                .stream().anyMatch(candidate -> candidate.getNumberInList().equals(voter.getVotingFor()));
//        System.out.println("Candidate: " + voter.getVotingFor() +" exists: "+candidateExists);
        if (!candidateExists)
            throw new Exception(("The candidate you are voting for does not exist."));
        else {
            // check if voter isn't already in repo
            boolean voterExistsInRepo = voterRepository.getVoterByIdentification(voter.getIdentification()) != null;
            if (voterExistsInRepo)
                // if the same voter was found in the repository
                throw new Exception("Voter with the same identification has already voted.");
            else
                voterRepository.save(voter);
        }

    }

    public List<Voter> getVotersByRegion(GeographicRegions region) {
        return voterRepository.findAllVotersByRegion(region);
    }

    public List<Voter> getAllVoters() {
        return voterRepository.findAll();
    }

    public Long getTotalVoterCount() {
        return voterRepository.countVotersByIdentificationIsNotNull();
    }

    public Long getTotalVoterCountByRegion(GeographicRegions region) {
        return voterRepository.countVotersByRegion(region);
    }

    public Long getTotalVoterCountByVote(Long candidateNumberInList) {
        return voterRepository.countVotersByVotingForEquals(candidateNumberInList);
    }

    public Long getTotalCountOfVotersByRegionAndCandidate(GeographicRegions region, Long candidateNumberInList) {
        return voterRepository.countVotersByRegionAndVotingFor(region, candidateNumberInList);
    }
}
