package com.tekion.interns.cricket.service;

import java.util.ArrayList;
import java.util.List;

public class Team
{
    List<Player> teamPlayers;
    String teamName;
    private int bowlerIndex = 4;
    private int striker = 0;
    private int nonStriker = 1; //The Batsman who isn't Striking
    private int nextBatsman = 2;
    private BowlingInfo bowlingScoreCard;
    private BattingInfo battingScoreCard;

    public Team(String name)
    {
        this.teamName = name;
        teamPlayers = new ArrayList<>();
        bowlingScoreCard = new BowlingInfo();
        battingScoreCard = new BattingInfo();
    }
    public void setTeamPlayers() {
//        this.teamPlayers = teamPlayers;
        for(int i=0; i<11; i++)
        {
            int t = i<4 ? 1 : (i<6 ? 3 : 2 ) ;
            teamPlayers.add(new Player(""+i, 0, t));
        }

    }

    public String getName() { return teamName; }
    public void addPlayer(Player p)    { teamPlayers.add(p); }
    public Player getBowlerIndex() { return teamPlayers.get(bowlerIndex); }
    public Player getStriker() { return teamPlayers.get(striker); }
    public Player getOther() { return teamPlayers.get(nonStriker); }
    public void strikerOut()
    {
        striker = nextBatsman;
        nextBatsman++;
    }
    private void strikeChange()
    {
        int temp = striker;
        striker = nonStriker;
        nonStriker = temp;
    }
    public BattingInfo getBattingScoreCard() { return battingScoreCard; }
    public BowlingInfo getBowlingScoreCard() { return bowlingScoreCard; }
//    public int runsScored()
//    {
//        int runs = teamPlayers.get(striker).runsScored(r);
//        if(runs==7) return runs;
//
//        battingScoreCard.runsScored(runs);
//        teamPlayers.get(striker).ballPlayed();
//        if(runs!=4 && runs!=6)
//        {
//            (teamPlayers.get(other)).runsScored(runs);
//            teamPlayers.get(other).ballPlayed();;
//            if(runs%2==1)
//                strikeChange();
//        }
//        else
//            boundaryScored();
//        return runs;
//    }
    public boolean isBowlingLimitReached()
    {
        for(int i=0; i<11 && teamPlayers.get(i).isBowler(); i++)
            if(teamPlayers.get(i).getNoOfOvers()<10)
                return false;
        return true;
    }


    //Batting
    public int getNoOfBalls()       { return battingScoreCard.getBallsFaced();      }
    public int getRunsScored()        { return battingScoreCard.getRunsScored();       }
    public int getNoOfBoundaries()  { return battingScoreCard.getNoOfBoundaries(); }
    public void runsScored(int runs)
    {
        battingScoreCard.updateRunsScored(runs);
        teamPlayers.get(striker).runsScored(runs);
        teamPlayers.get(striker).ballPlayed();
        if(runs!=4 && runs!=6)
        {
            (teamPlayers.get(nonStriker)).runsScored(runs);
            teamPlayers.get(nonStriker).ballPlayed();;
            if(runs%2==1)
                strikeChange();
        }
        else
            boundaryScored();
    }
    public void ballPlayed() { battingScoreCard.updateBallsFaced(); }
    public void boundaryScored() { battingScoreCard.updateBoundaries(); teamPlayers.get(striker).boundaryScored(); }
    //Bowling
    public int getNoOfOvers()       { return bowlingScoreCard.getNoOfOvers();    }
    public int getRunsGiven()       { return bowlingScoreCard.getRunsGiven();    }
    public int getWicketsTaken()    { return bowlingScoreCard.getWicketsTaken(); }
    public int getMaidenOvers()     { return bowlingScoreCard.getMaidenOvers();  }
    public void runsGiven(int runs) { bowlingScoreCard.updateRunsGiven(runs); teamPlayers.get(bowlerIndex).runsGiven(runs); }
    public void wicketTaken()       { bowlingScoreCard.updateWicketsTaken(); teamPlayers.get(bowlerIndex).wicketTaken(); }
    public void maidenOver()        { bowlingScoreCard.updateMaidenOvers(); teamPlayers.get(bowlerIndex).maidenOver(); }
    public void overStarted()       { teamPlayers.get(bowlerIndex).overPlayed(); bowlingScoreCard.updateOversBowled(); }
    public void overPlayed()
    {
        bowlerIndex = (bowlerIndex+1)%11;
        if(!isBowlingLimitReached())
            while(!teamPlayers.get(bowlerIndex).isBowler() || teamPlayers.get(bowlerIndex).getNoOfOvers()>10)
                bowlerIndex = (bowlerIndex+1)%11;
        else
            while (teamPlayers.get(bowlerIndex).getNoOfOvers()>10)
                bowlerIndex = (bowlerIndex+1)%11;
    }
}
