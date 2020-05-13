package com.votingapp.votingapp.Controller;

import com.votingapp.votingapp.Model.CandidateResult;
import com.votingapp.votingapp.Entity.GeographicRegions;
import com.votingapp.votingapp.Service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/report")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @GetMapping("/getOverallDistribution")
    public ResponseEntity<HashMap<Long,Float>> getOverallDistribution() {

        return  ResponseEntity.ok(reportService.getOverallDistribution());
    }

    @GetMapping("/getRegionDistribution")
    public ResponseEntity<HashMap<GeographicRegions,List<CandidateResult>>> getRegionDistribution() {
        return  ResponseEntity.ok(reportService.getRegionDistribution());
    }
}
