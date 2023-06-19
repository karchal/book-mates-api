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
        ItemType itemType = newAbuseReportDto.getItemType();
        Long itemId = newAbuseReportDto.getItemId();
        if (abuseReportRepository.existsByReporterIdAndItemIdAndItemType(userId, itemId, itemType)) {
            return new ResponseEntity<>("Już zgłaszałeś problem związany z tym wpisem.", HttpStatus.OK);
        }
        ResponseEntity<String> responseEntity = updateItemStatusToNeedsVerification(itemType, itemId);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            AbuseReport abuseReport = AbuseReport.builder()
                    .itemId(newAbuseReportDto.getItemId())
                    .itemType(newAbuseReportDto.getItemType())
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
                abuseReportRepository.findAllByItemIdAndItemTypeAndProblemType(
                        abuseReport.getItemId(),
                        abuseReport.getItemType(),
                        abuseReport.getProblemType()
                );
        reportsToUpdate.forEach(x -> {
            x.setReviewStatus(newReviewStatus);
            x.setReviewerId(reviewerId);
        });
        abuseReportRepository.saveAll(reportsToUpdate);
        if (newReviewStatus == CONTENT_BLOCKED){
            blockContent(abuseReport.getItemType(), abuseReport.getItemId());
        }
        return new ResponseEntity<>("Status zgłoszenia został pomyślnie zaktualizowany", HttpStatus.OK);
    }

    private ResponseEntity<String> updateItemStatusToNeedsVerification(ItemType itemType, Long itemId){
        return switch (itemType) {
            case COMMENT -> commentService.reportAbuse(itemId);
            case TOPIC -> topicService.reportAbuse(itemId);
        };
    }

    private void blockContent(ItemType itemType, Long elementId){
        switch (itemType) {
            case COMMENT -> commentService.blockComment(elementId);
            case TOPIC -> topicService.blockTopic(elementId);
        }
    }

}
