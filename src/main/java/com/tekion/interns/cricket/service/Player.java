package com.tekion.interns.cricket.service;

public class Player
{
    private final String name;
    private BowlingInfo bowlingInfo;
    private BattingInfo battingInfo;
    private int rating;
    private Role type;

    public Player(String name, int rating, int type) {
        this.name = name;
        this.rating = rating;
//        this.type = type;
        switch (type)
        {
            case 1:
                this.type = Role.BATSMAN;
                break;
            case 2:
                this.type = Role.BOWLER;
                break;
            default:
                this.type = Role.ALLROUNDER;
        }
        this.battingInfo = new BattingInfo();
        this.bowlingInfo = new BowlingInfo();
    }

    private enum Role { BATSMAN, BOWLER, ALLROUNDER }
    public boolean isBowler() { return type == Role.BOWLER || type==Role.ALLROUNDER; }
    public void runsScored(int runs){ battingInfo.updateRunsScored(runs);        }
    public void ballPlayed()        { battingInfo.updateBallsFaced();           }
    public void boundaryScored()    { battingInfo.updateBoundaries();      }
    public void runsGiven(int runs) { bowlingInfo.updateRunsGiven(runs);     }
    public void overPlayed()        { bowlingInfo.updateOversBowled();         }
    public void wicketTaken()       { bowlingInfo.updateWicketsTaken();      }
    public void maidenOver()        { bowlingInfo.updateMaidenOvers();       }

    public int getNoOfOvers()    { return bowlingInfo.getNoOfOvers();    }

}
