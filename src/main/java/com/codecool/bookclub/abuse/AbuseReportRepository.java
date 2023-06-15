package com.codecool.bookclub.abuse;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AbuseReportRepository extends JpaRepository<AbuseReport, Long> {

    boolean existsByReporterIdAndItemIdAndItemType(Long reporterId, Long elementId, ItemType itemType);
    List<AbuseReport> findAllByReviewStatus(ReviewStatus reviewStatus, Sort creationTime);
    List<AbuseReport> findAllByItemIdAndItemTypeAndProblemType(Long elementId, ItemType itemType, ProblemType problemType);

}
