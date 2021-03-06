package com.votingapp.votingapp.Service;

import com.votingapp.votingapp.Entity.Candidate;
import com.votingapp.votingapp.Repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Service
@RequestMapping("/api")
public class CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;

    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    public Optional<Candidate> getCandidate(Long id) {
        return candidateRepository.findById(id);
    }

    public void createCandidate(Candidate candidate) throws Exception {
        if (candidateRepository.findById(candidate.getId()).isPresent())
            throw new Exception("Candidate with provided ID already exists!");
        if (candidateRepository.findByNumberInList(candidate.getNumberInList()) != null)
            throw new Exception("Candidate with provided number in list already exists!");
            candidateRepository.save(candidate);
    }
}
