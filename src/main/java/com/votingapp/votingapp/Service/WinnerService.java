package com.votingapp.votingapp.Service;

import com.votingapp.votingapp.Entity.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class WinnerService {
    @Autowired
    private ReportService reportService;
    @Autowired
    private CandidateService candidateService;

    public ArrayList<Optional<Candidate>> getElectionWinner() {
        // returning list because if there are candidates with the same voter percentage
        // we will return those candidates
        ArrayList<Optional<Candidate>> electionWinnerList = new ArrayList<>();
        HashMap<Long, Float> overallDistribution = reportService.getOverallDistribution();
        // get the candidate result which has the highest percentage and is likely to win
        Map.Entry<Long, Float> potentialWinnerResult = overallDistribution.entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .orElse(null);
        // if there is more than one candidate with the max voting percentage
        // collect them into a list
        List<Map.Entry<Long, Float>> winnersResults = overallDistribution.entrySet()
                .stream()
                .filter(candidateResult -> candidateResult.getValue().equals(potentialWinnerResult.getValue()))
                .collect(Collectors.toList());
        // get winner data from candidate service
        winnersResults
                .forEach(winningCandidate -> {
                    electionWinnerList.add(candidateService.getCandidate(winningCandidate.getKey()));
                });
        return electionWinnerList;
    }
}
