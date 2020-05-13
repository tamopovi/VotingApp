package com.votingapp.votingapp.Controller;

import com.votingapp.votingapp.Entity.Candidate;
import com.votingapp.votingapp.Service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Candidate>> getCandidates() {
        return ResponseEntity.ok(candidateService.getAllCandidates());

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Candidate> getCandidate(@PathVariable Long id) {
        Optional<Candidate> candidate = candidateService.getCandidate(id);
        return candidate.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/createCandidate")
    public ResponseEntity createCandidate(@RequestBody Candidate candidate) {
        try {
            candidateService.createCandidate(candidate);
            return ResponseEntity.ok(candidateService.getCandidate(candidate.getId()));
        } catch (Exception e) {
            // might be the case that a candidate with this list number already exists
            // or that a candidate with the same id is trying to be created
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
