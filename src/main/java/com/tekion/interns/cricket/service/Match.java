package com.tekion.interns.cricket.service;

public class Match
{
    static int target;
    static int ballOutcomes[] = { 0, 1, 2, 3, 4, 5, 6, -1 };
    //    static int topOrderFreq[] =  { 1, 140, 40, 20, 10, 20, 1, 6};
//    static int middleOrderFreq[]= {6, 60, 7, 5 , 2, 18, 1, 7};
//    static int lowerOrderFreq[] = {12, 80, 10, 5, 2, 4, 1, 2};
    public static int Delivery() {
        return (int) (Math.random() * 8) - 1;
    }
//    public static int findCeil (int arr[], int r, int l, int h)
//    {
//        int mid;
//        while (l < h)
//        {
//            mid = l + ((h - l) >> 1);  // Same as mid = (l+h)/2
//            if (r > arr[mid])
//                l = mid + 1;
//            else
//                h = mid;
//        }
//        return (arr[l] >= r) ? l : -1;
//    }


//    static int Delivery ( int frequencyArray[])
//    {
//        int n=8;
//        // Create and fill prefix array
//        int prefix[] = new int[n];
//        prefix[0] = frequencyArray[0];
//        for (int i = 1; i < n; ++i)
//            prefix[i] = prefix[i - 1] + frequencyArray[i];
//        // prefix[n-1] is sum of all frequencies.
//        // Generate a random number with
//        // value from 1 to this sum
//        int r = (int)(Math.random () * prefix[n - 1]);
//        // Find index of ceiling of r in prefix array
//        int index = findCeil (prefix, r, 0, n - 1);
//        return ballOutcomes[index];
//    }

    public static int HeadsOrTails() {
        return (int) (Math.random() * 2);
    }
    public static String conductToss(MatchAdmin admin) {
        String tossResult = "";
        Team tempTeam;
        int temp = HeadsOrTails();
        if (temp == 0) {  //team 1 won toss
            tossResult = admin.getTeam1().getName() + " won the toss and elected to";
            temp = HeadsOrTails();
            if (temp == 0) { //chose to bat
                tossResult += " bat first";
            } else { //chose to field
                tossResult += " field first";
                tempTeam = admin.getTeam1();
                admin.setTeam1(admin.getTeam2());
                admin.setTeam2(tempTeam);
            }
        } else { //team 2 won toss
            tossResult = admin.getTeam2().getName() + " won the toss and elected to";
            temp = HeadsOrTails();
            if (temp == 0) { //chose to bat
                tossResult += " bat first";
                tempTeam = admin.getTeam1();
                admin.setTeam1(admin.getTeam2());
                admin.setTeam2(tempTeam);
            } else {
                tossResult += " field first";
            }
        }
        return tossResult;
    }
    public static void Innings(boolean isFirst,InningData inningData) {
        int runs;
        boolean flag = true;
        for(int i=0; i<300; i++)
        {
            if(i%6==1)  inningData.getBowlingTeam().overStarted();
            runs = Delivery();
            inningData.ballPlayed();
            if(runs==-1)
            {
                inningData.wicketTaken();
                if(inningData.allWicketsTaken()) break;
            }
            if(runs>0)
            {
                flag = false;
                inningData.runsScored(runs);
                if(!isFirst && inningData.getRunsScored()>target)
                    break;

            }
            if(i%6==0 && i>0)
            {
                if(flag) inningData.getBattingTeam().maidenOver();
                inningData.getBowlingTeam().overPlayed();
                flag = true;
            }
        }
        target=inningData.getRunsScored();
    }

}
