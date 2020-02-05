package com.tekion.interns.cricket.service;

public class BattingInfo
{
    private int ballsFaced;
    private int runsScored;
    private int boundariesHit;
    public BattingInfo()
    {
        ballsFaced = 0;
        runsScored = 0;
        boundariesHit = 0;
    }
    public int getBallsFaced()       { return ballsFaced;      }
    public int getRunsScored()        { return runsScored;       }
    public int getBoundariesHit()  { return boundariesHit; }
    public void updateRunsScored(int runs){ runsScored +=runs;        }
    public void updateBallsFaced()        { ballsFaced++;           }
    public void updateBoundaries()    { boundariesHit++;      }
}

