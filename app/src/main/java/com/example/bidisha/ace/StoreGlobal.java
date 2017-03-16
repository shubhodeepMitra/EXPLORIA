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
     *
     * @mitra00
     * return the global value of score
     */
    public int getGlobalScore()
    {
        return score;
    }

    /**
     * mitra00
     * initiating the score to be zero at each app call
     * else the value is not resetted
     */
    public void initialScore()
    {
        score=0;
    }

    /**
     * @mitra00
     * change the variable score
     *
     */
    public void changeScore(int x,float y)
    {
        double z=(y/1000)*0.3;
        score+=x+z;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        score=0;
    }
}