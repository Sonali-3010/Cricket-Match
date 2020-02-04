package com.tekion.interns.cricket.service;

public class FinalMatchReport {
    private String tossResult;
    private InningData firstInnings;
    private InningData secondInnings;
    private String matchResult;

    public FinalMatchReport(String tossResult, InningData firstInnings, InningData secondInnings, String matchResult) {
        this.tossResult = tossResult;
        this.firstInnings = firstInnings;
        this.secondInnings = secondInnings;
        this.matchResult = matchResult;
    }
    public String getTossResult() { return tossResult; }
    public InningData getFirstInnings() { return firstInnings; }
    public InningData getSecondInnings() { return secondInnings; }
    public String getMatchResult() { return matchResult; }
}
