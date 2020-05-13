package com.votingapp.votingapp.Controller;

import com.votingapp.votingapp.Service.WinnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/winner")
public class WinnerController {
    @Autowired
    private WinnerService winnerService;

    @GetMapping("/getElectionWinner")
    public ResponseEntity getElectionWinner() {
        return ResponseEntity.ok(winnerService.getElectionWinner());
    }
}
