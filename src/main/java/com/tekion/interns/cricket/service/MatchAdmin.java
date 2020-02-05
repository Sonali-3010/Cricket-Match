package com.tekion.interns.cricket.service;

public class MatchAdmin {
    private String tossResult="";
    private Team team1;
    private Team team2;
    private  String matchResult;
    private InningData innings1;
    private InningData innings2;
    public MatchAdmin(Team team1, Team team2){
        this.team1 = team1;
        this.team2 = team2;
    }
    public void ConductMatch(){
        team1.setTeamPlayers();  team2.setTeamPlayers();

        tossResult = Match.conductToss(this);
        innings1 = new InningData(team1, team2); innings2 = new InningData(team2, team1);
        //team1 batting first
        Match.innings(true,innings1);
        Match.innings(false,innings2);
        generateMatchResult();
//        System.out.println(team1.getName()+" " +team1.getRunsScored()+" "+team2.getWicketsTaken());
//        System.out.println(team2.getName()+" " +team2.getRunsScored()+" "+team1.getWicketsTaken());
    }
    private void generateMatchResult()
    {
        if(innings1.getInningsTotal() > innings2.getInningsTotal()){
            matchResult = team1.getName()+" won by "+ (innings1.getInningsTotal() - innings2.getInningsTotal()) + " runs";
        }
        else
            matchResult = team2.getName()+" won by "+ ((10-innings2.getWicketsFallen()==1)?"1 wicket":10-innings2.getWicketsFallen()+" wickets");
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
    public InningData getInnings1() { return innings1; }
    public InningData getInnings2() { return innings2; }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }
    public void setTeam2(Team team2) { this.team2 = team2; }


}
