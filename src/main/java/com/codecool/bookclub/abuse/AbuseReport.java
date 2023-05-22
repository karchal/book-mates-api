package com.codecool.bookclub.abuse;

import com.codecool.bookclub.user.model.User;
import jakarta.persistence.*;
import lombok.*;

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
    private ContentType contentType;
    private Long elementId;
    private ProblemType problemType;
    @ManyToOne
    private User reporter;
}
