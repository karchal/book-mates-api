package com.codecool.bookclub.abuse;

public enum ProblemType {
    OFFENSIVE_CONTENT("obraźliwa zawartość"),
    VULGARISMS("wulgaryzmy"),
    HATRED("propagowanie nienawiści"),
    WRONG_INFO("fałszywe informacje"),
    SPAM("spam"),
    OTHER("inne");

    private final String problemDescription;
    ProblemType(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public String getProblemDescription(){
        return problemDescription;
    }
}
