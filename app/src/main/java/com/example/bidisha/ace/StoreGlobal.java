package com.example.bidisha.ace;

import android.app.Application;

/**
 * Created by Bidisha on 14-03-2017.
 */

public class StoreGlobal extends Application {
    /**
     * @mitra00
     * to store the score of the team which is global
     */
    private int score;

    /**
     * to store time left
     */
    private double tleft;

    /**
     * To store the team name
     */
    private String name;

    /**
     * return the global value of score
     */


    public int getGlobalScore()
    {
        return score/10;
    }

    /**
     * @mitra00
     * initiating the score to be zero at each app call
     * else the value is not resetted
     */
    public void initialScore()
    {
        score=0;
    }

    /**
     *
     * change the variable score
     *
     */
    public void changeScore(int x,long y)
    {
        tleft=y/1000.0;

        double z=(y/1000.0)*.53;
        score+=x+z;
    }

    /**
     *
     * @mitra00
     * to get the team name
     */
    public String getTeamName()
    {
        return name;
    }

    /**
     *
     * @mitra00
     * to set the team name
     */
    public void teamName(String s)
    {
        name=s;
    }

    /**
     * @mitra00
     * Returning time left
     */
    public double getTimeleft()
    {
        return tleft;
    }

    /**
     * @mitra00
     * to set the time to zero if the time left is over and questions are unsolved
     */

    public void setTimeToZero()
    {
        tleft=0;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        score=0;
    }
}