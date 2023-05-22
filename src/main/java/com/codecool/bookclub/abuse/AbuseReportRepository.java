package com.codecool.bookclub.abuse;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AbuseReportRepository extends JpaRepository<AbuseReport, Long> {

    boolean existsByReporterIdAndElementId(Long reporterId, Long elementId);

}
