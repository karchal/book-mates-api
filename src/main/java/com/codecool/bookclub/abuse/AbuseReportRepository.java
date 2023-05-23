package com.codecool.bookclub.abuse;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AbuseReportRepository extends JpaRepository<AbuseReport, Long> {

    boolean existsByReporterIdAndElementIdAndContentType(Long reporterId, Long elementId, ContentType contentType);

}
