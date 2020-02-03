package com.tekion.interns.cricket.service;

public class MatchAdmin {
    private String tossResult="";
    private Team team1;
    private Team team2;
    private  String matchResult;
    private InningData i1;
    private InningData i2;
    public MatchAdmin(Team team1, Team team2){
        this.team1 = team1;
        this.team2 = team2;

    }
    public void ConductMatch(){
        team1.setTeamPlayers();  team2.setTeamPlayers();
        tossResult = Match.conductToss(this);
        i1 = new InningData(team1, team2);
        i2 = new InningData(team2, team1);
        //team1 batting first
        System.out.println("Everything Initialized");
        Match.Innings(true,i1);
        Match.Innings(false,i2);
        
        System.out.println(team1.getName()+" " +team1.getRunsScored()+" "+team2.getWicketsTaken());
        System.out.println(team2.getName()+" " +team2.getRunsScored()+" "+team1.getWicketsTaken());
        if(team1.getRunsScored() > team2.getRunsScored()){
            matchResult = team1.getName()+" won by "+ (team1.getRunsScored() - team2.getRunsScored()) + " runs";
        }
        else
            //matchResult = team2.getName()+" won by "+ (10 - team2.getWicketsFallen())+ " wickets";
            matchResult = team2.getName()+" won by "+ ((10-team2.getWicketsTaken()==1)?"1 wicket":10-team2.getWicketsTaken()+" wickets");
    }
    public String getMatchResult() {
        return matchResult;
    }
    public Team getTeam1() {
        return team1;
    }
    public Team getTeam2() {
        return team2;
    }
    public String getTossResult() {
        return tossResult;
    }
    public void setTossResult(String tossResult) {
        this.tossResult = tossResult;
    }
    public void setTeam1(Team team1) {
        this.team1 = team1;
    }
    public void setTeam2(Team team2) {
        this.team2 = team2;
    }
    public void setMatchResult(String matchResult) {
        this.matchResult = matchResult;
    }
}
