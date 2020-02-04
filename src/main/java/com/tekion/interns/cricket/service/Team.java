package com.tekion.interns.cricket.service;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Team
{
    List<Player> teamPlayers;
    String teamName;

    Map< String,BattingInfo> battingMap = new HashMap< String,BattingInfo>();
    Map< String,BowlingInfo> bowlingMap = new HashMap< String,BowlingInfo>();

    private int bowlerIndex = 4;
    private int striker = 0;
    private int nonStriker = 1; //The Batsman who isn't Striking
    private int nextBatsman = 2;

    public Team(String name)
    {
        this.teamName = name;
        teamPlayers = new ArrayList<>();
    }
    public void setTeamPlayers() {
//        this.teamPlayers = teamPlayers;
        for(int i=0; i<11; i++)
        {
            int t = i<4 ? 1 : (i<6 ? 3 : 2 ) ;
            teamPlayers.add(new Player(""+i, t, t));
        }
        battingMap.put(teamPlayers.get(striker).getName(), teamPlayers.get(striker).getBattingInfo());
        battingMap.put(teamPlayers.get(nonStriker).getName(), teamPlayers.get(nonStriker).getBattingInfo());
    }

    public Map<String, BattingInfo> getBattingMap() {
        return battingMap;
    }

    public Map<String, BowlingInfo> getBowlingMap() {
        return bowlingMap;
    }

    public String getName() { return teamName; }
    public Player getStriker(){ return teamPlayers.get(striker);}
    public void runsScored(int runs)
    {
        teamPlayers.get(striker).runsScored(runs);
        if(runs%2==1)
            strikeChange();
        if(runs==4 || runs==6)
            boundaryScored();
    }
    public void ballPlayed()    {teamPlayers.get(striker).ballPlayed();}
    public void boundaryScored() { teamPlayers.get(striker).boundaryScored(); }
    public void runsGiven(int runs) { teamPlayers.get(bowlerIndex).runsGiven(runs); }
    public void wicketTaken()       { teamPlayers.get(bowlerIndex).wicketTaken(); }
    public void maidenOver()        { teamPlayers.get(bowlerIndex).maidenOver(); }
    public void overStarted()
    {
        if(teamPlayers.get(bowlerIndex).getNoOfOvers()==0)
            bowlingMap.put(teamPlayers.get(bowlerIndex).getName(), teamPlayers.get(bowlerIndex).getBowlingInfo());
        teamPlayers.get(bowlerIndex).overPlayed();
    }
    public void overPlayed()
    {
        bowlerIndex = (bowlerIndex+1)%11;
        while(!teamPlayers.get(bowlerIndex).isBowler())
            bowlerIndex = (bowlerIndex+1)%11;
//        if(!isBowlingLimitReached())
//            while(!teamPlayers.get(bowlerIndex).isBowler() || teamPlayers.get(bowlerIndex).getNoOfOvers()>10)
//                bowlerIndex = (bowlerIndex+1)%11;
//        else
//            while (teamPlayers.get(bowlerIndex).getNoOfOvers()>10)
//                bowlerIndex = (bowlerIndex+1)%11;
        strikeChange();
    }
    public void strikerOut()
    {
        striker = nextBatsman;
        battingMap.put(teamPlayers.get(striker).getName(), teamPlayers.get(striker).getBattingInfo());
        nextBatsman++;
    }
//
//    private boolean isBowlingLimitReached()
//    {
//        for(int i=0; i<11 && teamPlayers.get(i).isBowler(); i++)
//            if(teamPlayers.get(i).getNoOfOvers()<10)
//                return false;
//        return true;
//    }
    private void strikeChange()
    {
        int temp = striker;
        striker = nonStriker;
        nonStriker = temp;
    }
}
