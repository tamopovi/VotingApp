package com.votingapp.votingapp.Service;

import com.votingapp.votingapp.Model.CandidateResult;
import com.votingapp.votingapp.Entity.Candidate;
import com.votingapp.votingapp.Entity.GeographicRegions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequestMapping("/api")
public class ReportService {
    @Autowired
    private VoterService voterService;

    @Autowired
    private CandidateService candidateService;

    public HashMap<Long, Float> getOverallDistribution() {
        HashMap<Long, Float> candidateDistribution = new HashMap<>();
        Long totalVoterCount = voterService.getTotalVoterCount();
        List<Candidate> candidates = candidateService.getAllCandidates();
        candidates.forEach(candidate -> {
            candidateDistribution.put(candidate.getId(), (float) voterService.getTotalVoterCountByVote(candidate.getNumberInList()) / totalVoterCount);
        });
        return candidateDistribution;
    }

    public HashMap<GeographicRegions, List<CandidateResult>> getRegionDistribution() {
        HashMap<GeographicRegions, List<CandidateResult>> result = new HashMap<GeographicRegions, List<CandidateResult>>();
        Stream.of(GeographicRegions.values()).forEach(region -> {
            List<Candidate> candidates = candidateService.getAllCandidates();
            Long votersInRegion = voterService.getTotalVoterCountByRegion(region);

            Stream<CandidateResult> candidateResults = candidates.stream().map(candidate -> {
                Long votersInRegionVotedForCandidate = voterService.getTotalCountOfVotersByRegionAndCandidate(region, candidate.getNumberInList());
                // if no one voted for the candidate, voter result percentage is 0
                if (votersInRegionVotedForCandidate > 0)
                    return new CandidateResult(candidate.getId(), (float) votersInRegionVotedForCandidate / votersInRegion);
                else
                    return new CandidateResult(candidate.getId(), 0);
            });
            result.put(region, candidateResults.collect(Collectors.toList()));
        });
        return result;
    }

}
