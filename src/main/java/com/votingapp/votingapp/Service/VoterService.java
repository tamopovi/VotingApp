package com.votingapp.votingapp.Service;

import com.votingapp.votingapp.Entity.Candidate;
import com.votingapp.votingapp.Entity.GeographicRegions;
import com.votingapp.votingapp.Entity.Voter;
import com.votingapp.votingapp.Repository.CandidateRepository;
import com.votingapp.votingapp.Repository.VoterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;

@Service
@RequestMapping("/api")
public class VoterService {
    @Autowired
    private VoterRepository voterRepository;

    // I have a feeling that including the candidate repository inside VoterService isn't a good idea
    @Autowired
    private CandidateRepository candidateRepository;

    public void vote(Voter voter) throws Exception {
        // check if candidate number exists, if not, do not let to vote
        ExampleMatcher candidateMatcher = ExampleMatcher.matching()
                .withIgnorePaths("id")
                .withMatcher("numberInList", exact());
        Candidate probeCandidate = new Candidate();
        probeCandidate.setNumberInList(voter.getVotingFor());
        Example<Candidate> candidateExample = Example.of(probeCandidate, candidateMatcher);
        if (!candidateRepository.exists(candidateExample))
            throw new Exception(("The candidate you are voting for does not exist."));
        else {
            // check if voter isn't already in repo
            ExampleMatcher voterMatcher = ExampleMatcher.matching().withMatcher("identification", ignoreCase());
            Voter probeVoter = new Voter();
            // create an example of the same identification as the new vote
            probeVoter.setIdentification(voter.getIdentification());
            Example<Voter> voterExample = Example.of(probeVoter, voterMatcher);
            if (voterRepository.exists(voterExample))
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
}
