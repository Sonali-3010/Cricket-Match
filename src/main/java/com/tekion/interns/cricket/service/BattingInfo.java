package com.tekion.interns.cricket.service;

public class BattingInfo
{
    private int ballsFaced;
    private int runsScored;
    private int noOfBoundaries;
    public BattingInfo()
    {
        ballsFaced = 0;
        runsScored = 0;
        noOfBoundaries = 0;
    }
    public int getBallsFaced()       { return ballsFaced;      }
    public int getRunsScored()        { return runsScored;       }
    public int getNoOfBoundaries()  { return noOfBoundaries; }
    public void updateRunsScored(int runs){ runsScored +=runs;        }
    public void updateBallsFaced()        { ballsFaced++;           }
    public void updateBoundaries()    { noOfBoundaries++;      }
}

