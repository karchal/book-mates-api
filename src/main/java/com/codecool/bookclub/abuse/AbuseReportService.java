package com.codecool.bookclub.abuse;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AbuseReportService {

    private final AbuseReportRepository abuseReportRepository;

    public ResponseEntity<String> createReport(NewAbuseReportDto newAbuseReportDto, Long userId) {
        if (abuseReportRepository.existsByReporterIdAndElementId(userId, newAbuseReportDto.getElementId())) {
            return new ResponseEntity<>("Already reported", HttpStatus.OK);
        }
        AbuseReport abuseReport = AbuseReport.builder()
                .elementId(newAbuseReportDto.getElementId())
                .contentType(newAbuseReportDto.getContentType())
                .problemType(newAbuseReportDto.getProblemType())
                .reporterId(userId)
                .build();
        abuseReportRepository.save(abuseReport);
        return new ResponseEntity<>("Already reported", HttpStatus.OK);
    }


}
