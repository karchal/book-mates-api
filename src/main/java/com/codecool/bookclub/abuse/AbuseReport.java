package com.codecool.bookclub.abuse;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "abuse_report")
public class AbuseReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private LocalDateTime creationTime;
    private ContentType contentType;
    private Long elementId;
    private ProblemType problemType;
    private Long reporterId;
    private ReviewStatus reviewStatus;
    private Long reviewerId;
}
