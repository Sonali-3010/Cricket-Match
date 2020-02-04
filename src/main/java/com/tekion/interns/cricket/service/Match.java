package com.tekion.interns.cricket.service;

public class Match
{
    static int target;
    static int ballOutcomes[] = { -1, 0, 1, 2, 3, 4, 5, 6};
    static int topOrderFreq[] =  { 1, 140, 40, 20, 10, 20, 1, 6};
    static int middleOrderFreq[]= {6, 60, 7, 5 , 2, 18, 1, 7};
    static int lowerOrderFreq[] = {12, 80, 10, 5, 2, 4, 1, 2};

    public static String conductToss(MatchAdmin admin) {
        String tossWinningTeam;
        String tossChoice;
        int temp = headsOrTails();
        if (temp == 0) {  //team 1 won toss
            tossWinningTeam = admin.getTeam1().getName();
            temp = headsOrTails();
            if (temp == 0) { tossChoice = "bat"; }//Chose to bat
            else { //chose to field
                tossChoice = "field";
                swapTeams(admin);
            }
        }
        else { //team 2 won toss
            tossWinningTeam = admin.getTeam2().getName();
            temp = headsOrTails();
            if (temp == 0) { //chose to bat
                tossChoice = "bat";
                swapTeams(admin);
            }
            else { tossChoice = "field"; } //Chose to field
        }
        return generateTossResult(tossWinningTeam, tossChoice);
    }


    public static void innings(boolean isFirst, InningData inningData) {
        int runs;
        boolean isMaiden = true;
        for(int i=0; i<300; i++)
        {
            if(i%6==1)  inningData.getBowlingTeam().overStarted();
            runs = delivery(inningData);
            if(runs==-1 && !inningData.wicketTaken())
                break;
            if(runs>0)
            {
                isMaiden = false;
                inningData.runsScored(runs);
                if(!isFirst && inningData.getInningsTotal()>target)
                    break;
            }
            if(i%6==0 && i>0)   //Over Played
            {
                if(isMaiden)//Checking if the over was a maiden over
                    inningData.getBowlingTeam().maidenOver();
                inningData.getBowlingTeam().overPlayed();
                isMaiden = true;
            }
//            if(isFirst && i==67)   break;
        }
        target=inningData.getInningsTotal();
    }

    private static int delivery(InningData inningData) {    //Generate Runs
        inningData.ballPlayed();
        int rating = inningData.getStriker().getRating();
        switch (rating)
        {
            case 1:
                return Delivery(topOrderFreq);
            case 2:
                return Delivery(lowerOrderFreq);
            default:
                return Delivery(middleOrderFreq);
        }
//        return (int) (Math.random() * 8) - 1;
    }
    private static int headsOrTails() { return (int) (Math.random() * 2); }
    private static void swapTeams(MatchAdmin admin) {
        Team tempTeam;
        tempTeam = admin.getTeam1();
        admin.setTeam1(admin.getTeam2());
        admin.setTeam2(tempTeam);
    }
    private static String generateTossResult(String team, String choice)
    {
        return (team + " won the toss and elected to " + choice + " first.");
    }
    private static int Delivery (int[] frequencyArray)
    {
        int n=8;
        int[] prefix = new int[n];
        prefix[0] = frequencyArray[0];
        for (int i = 1; i < n; ++i)
            prefix[i] = prefix[i - 1] + frequencyArray[i];
        int r = (int)(Math.random () * prefix[n - 1]);
        int index = findCeil (prefix, r, 0, n - 1);
        return ballOutcomes[index];
    }
    private static int findCeil (int arr[], int r, int l, int h)
    {
        int mid;
        while (l < h)
        {
            mid = l + ((h - l) >> 1);  // Same as mid = (l+h)/2
            if (r > arr[mid])
                l = mid + 1;
            else
                h = mid;
        }
        return (arr[l] >= r) ? l : -1;
    }


}
