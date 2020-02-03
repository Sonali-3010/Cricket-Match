package com.tekion.interns.cricket.service;

public class BowlingInfo {
    private int oversBowled;
    private int runsGiven;
    private int wicketsTaken;
    private int maidenOvers;
    public BowlingInfo()
    {
        this.oversBowled = 0;
        this.runsGiven = 0;
        this.wicketsTaken = 0;
        this.maidenOvers = 0;
    }

    public int getNoOfOvers()       { return oversBowled;    }
    public int getRunsGiven()       { return runsGiven;    }
    public int getWicketsTaken()    { return wicketsTaken; }
    public int getMaidenOvers()     { return maidenOvers;  }
    public void updateRunsGiven(int runs) { runsGiven+=runs;     }
    public void updateWicketsTaken()       { wicketsTaken++;      }
    public void updateMaidenOvers()        { maidenOvers++;       }
    public void updateOversBowled()        { oversBowled++; }
}
