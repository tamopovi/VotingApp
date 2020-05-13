package com.votingapp.votingapp.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CandidateResult {
    private Long candidateId;
    private float candidateVoterResult;
    public CandidateResult(){

    }
    public CandidateResult(Long candidateId, float candidateVoterResult){
        this.candidateId = candidateId;
        this.candidateVoterResult = candidateVoterResult;
    }
}
