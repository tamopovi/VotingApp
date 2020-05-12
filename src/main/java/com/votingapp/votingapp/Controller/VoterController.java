package com.votingapp.votingapp.Controller;

import com.votingapp.votingapp.Entity.GeographicRegions;
import com.votingapp.votingapp.Entity.Voter;
import com.votingapp.votingapp.Service.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/voters")
public class VoterController {
    @Autowired
    private VoterService voterService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Voter>> getAllVoters() {
        return ResponseEntity.ok(voterService.getAllVoters());
    }

    // this endpoint should somehow be hidden because it would not be ethical to get access to voter data
    @GetMapping("/region/{geographicregion}")
    public ResponseEntity<List<Voter>> getVotersByGeographicRegion(@PathVariable GeographicRegions geographicregion) {
        return ResponseEntity.ok(voterService.getVotersByRegion(geographicregion));
    }

    @PostMapping("/vote")
    public void voteForCandidate(@RequestBody Voter voter) {
        try {
            voterService.vote(voter);
        } catch (Exception e) {
            // check if candidate exists
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
