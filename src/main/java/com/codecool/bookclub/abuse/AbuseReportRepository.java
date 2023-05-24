package com.codecool.bookclub.abuse;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AbuseReportRepository extends JpaRepository<AbuseReport, Long> {

    boolean existsByReporterIdAndElementIdAndContentType(Long reporterId, Long elementId, ContentType contentType);
    List<AbuseReport> findAllByReviewStatus(ReviewStatus reviewStatus, Sort creationDateAndTime);
    List<AbuseReport> findAllByElementIdAndContentTypeAndProblemType(Long elementId, ContentType contentType, ProblemType problemType);

}
