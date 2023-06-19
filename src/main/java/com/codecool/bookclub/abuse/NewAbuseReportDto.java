package com.codecool.bookclub.abuse;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewAbuseReportDto {
    private Long itemId;
    private ItemType itemType;
    private ProblemType problemType;

}
