package com.codecool.bookclub.abuse;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewAbuseReportDto {
    private Long elementId;
    private ContentType contentType;
    private ProblemType problemType;
    private Long contentAuthorId;

}
