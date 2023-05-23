package com.codecool.bookclub.abuse;

import com.codecool.bookclub.forum.service.CommentService;
import com.codecool.bookclub.forum.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AbuseReportService {

    private final AbuseReportRepository abuseReportRepository;

    private final CommentService commentService;
    private final TopicService topicService;

    public ResponseEntity<String> createReport(NewAbuseReportDto newAbuseReportDto, Long userId) {
        ContentType contentType = newAbuseReportDto.getContentType();
        Long elementId = newAbuseReportDto.getElementId();
        if (abuseReportRepository.existsByReporterIdAndElementIdAndContentType(userId, elementId, contentType)) {
            return new ResponseEntity<>("Już zgłaszałeś problem", HttpStatus.OK);
        }
        ResponseEntity<String> responseEntity = reportContent(contentType, elementId);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            AbuseReport abuseReport = AbuseReport.builder()
                    .elementId(newAbuseReportDto.getElementId())
                    .contentType(newAbuseReportDto.getContentType())
                    .problemType(newAbuseReportDto.getProblemType())
                    .contentAuthorId(newAbuseReportDto.getContentAuthorId())
                    .reporterId(userId)
                    .build();
            abuseReportRepository.save(abuseReport);
        }
        return responseEntity;
    }

    private ResponseEntity<String> reportContent(ContentType contentType, Long elementId){
        return switch (contentType) {
                case COMMENT -> commentService.reportAbuse(elementId);
                case TOPIC -> topicService.reportAbuse(elementId);
                case EVENT -> null;
            };
    }


}
