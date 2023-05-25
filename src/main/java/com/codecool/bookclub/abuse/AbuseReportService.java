package com.codecool.bookclub.abuse;

import com.codecool.bookclub.forum.service.CommentService;
import com.codecool.bookclub.forum.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.codecool.bookclub.abuse.ReviewStatus.CONTENT_BLOCKED;
import static com.codecool.bookclub.abuse.ReviewStatus.WAITING_FOR_REVIEW;

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
                    .reporterId(userId)
                    .reviewStatus(WAITING_FOR_REVIEW)
                    .build();
            abuseReportRepository.save(abuseReport);
        }
        return responseEntity;
    }

    public List<AbuseReport> getReportsWaitingForReview() {
        return abuseReportRepository.findAllByReviewStatus(WAITING_FOR_REVIEW, Sort.by(Sort.Direction.ASC, "creationTime"));
    }

    public ResponseEntity<String> updateStatus(Long id, ReviewStatus newReviewStatus, Long reviewerId) {
        AbuseReport abuseReport = abuseReportRepository.findById(id).orElse(null);
        if (abuseReport == null){
            new ResponseEntity<>("Zgłoszenie o podanym id nie istnieje", HttpStatus.BAD_REQUEST);
        }
        if (newReviewStatus == abuseReport.getReviewStatus()) {
            new ResponseEntity<>("Zgłoszenie zostało już zweryfikowane", HttpStatus.BAD_REQUEST);
        }
        List<AbuseReport> reportsToUpdate =
                abuseReportRepository.findAllByElementIdAndContentTypeAndProblemType(
                        abuseReport.getElementId(),
                        abuseReport.getContentType(),
                        abuseReport.getProblemType()
                );
        reportsToUpdate.forEach(x -> {
            x.setReviewStatus(newReviewStatus);
            x.setReviewerId(reviewerId);
        });
        abuseReportRepository.saveAll(reportsToUpdate);
        if (newReviewStatus == CONTENT_BLOCKED){
            blockContent(abuseReport.getContentType(), abuseReport.getElementId());
        }
        return new ResponseEntity<>("Status zgłoszenia został pomyślnie zaktualizowany", HttpStatus.OK);
    }

    private ResponseEntity<String> reportContent(ContentType contentType, Long elementId){
        return switch (contentType) {
            case COMMENT -> commentService.reportAbuse(elementId);
            case TOPIC -> topicService.reportAbuse(elementId);
            case EVENT -> null;
        };
    }

    private void blockContent(ContentType contentType, Long elementId){
        switch (contentType) {
            case COMMENT -> commentService.blockComment(elementId);
            case TOPIC -> topicService.blockTopic(elementId);
        }
    }

}
