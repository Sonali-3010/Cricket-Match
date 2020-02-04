package com.tekion.interns.cricket.service;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.Map;

public class InningData
{
    private Team battingTeam;
    private Team bowlingTeam;

    private int inningsTotal;
    private int wicketsFallen;
    private int ballsPlayed;
    private float oversPlayed;

    Map< String,BattingInfo> battingLineUp;
    Map< String,BowlingInfo> bowlingLineUp;

    public InningData(Team battingTeam, Team bowlingTeam)
    {
        this.battingTeam = battingTeam;
        this.bowlingTeam = bowlingTeam;
        this.ballsPlayed = 0;
        this.inningsTotal = 0;
        this.wicketsFallen = 0;
        bowlingLineUp = bowlingTeam.getBowlingMap();
        battingLineUp = battingTeam.getBattingMap();
    }

    //Getters
    public Map<String, BattingInfo> getBattingLineUp() { return battingLineUp; }
    public Map<String, BowlingInfo> getBowlingLineUp() { return bowlingLineUp; }
    public int getInningsTotal()   { return inningsTotal; }
    public int getWicketsFallen()   { return wicketsFallen; }
    public float getOversPlayed() { return oversPlayed; }
    @JsonIgnore public Team getBowlingTeam() { return bowlingTeam; }
    @JsonIgnore public Player getStriker(){ return battingTeam.getStriker();}

    //Other methods
    public boolean wicketTaken()
    {
        wicketsFallen++;
        bowlingTeam.wicketTaken();
        if(allWicketsTaken())
            return false;
        battingTeam.strikerOut();
        return true;
    }
    public void ballPlayed()
    {
        ballsPlayed++;
        oversPlayed = ballsPlayed/6 + ((float) ballsPlayed%6)/10;
        battingTeam.ballPlayed();
    }
    public void runsScored(int runs)
    {
        inningsTotal += runs;
        battingTeam.runsScored(runs);
        bowlingTeam.runsGiven(runs);
    }
    private boolean allWicketsTaken() { return (wicketsFallen == 10) ;}
}
