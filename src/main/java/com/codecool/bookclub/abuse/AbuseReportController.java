package com.codecool.bookclub.abuse;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/abuse-reports")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class AbuseReportController {

    private final AbuseReportService abuseReportService;

    @GetMapping("/problem-types")
    public ResponseEntity<ProblemType[]> getProblemTypes(){
        return ResponseEntity.ok(ProblemType.values());
    }

    @PostMapping("/")
    public ResponseEntity<String> reportAbuse(@RequestBody NewAbuseReportDto newAbuseReportDto, @AuthenticationPrincipal Long userId){
        return abuseReportService.createReport(newAbuseReportDto, userId);
    }
    @GetMapping("/")
    public ResponseEntity<List<AbuseReport>> getReportsWaitingForReview(){
        return new ResponseEntity<>(abuseReportService.getReportsWaitingForReview(), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateReviewStatus(@RequestBody ReviewStatus reviewStatus, @PathVariable("id") long id, @AuthenticationPrincipal Long reviewerId){
        return abuseReportService.updateStatus(id, reviewStatus, reviewerId);
    }
}
