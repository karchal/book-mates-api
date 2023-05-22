package com.codecool.bookclub.abuse;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/abuse-report")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class AbuseReportController {

    private final AbuseReportService abuseReportService;
    @PostMapping("/")
    public ResponseEntity<String> reportAbuse(@RequestBody NewAbuseReportDto newAbuseReportDto, @AuthenticationPrincipal Long userId){
        return abuseReportService.createReport(newAbuseReportDto, userId);
    }
}
