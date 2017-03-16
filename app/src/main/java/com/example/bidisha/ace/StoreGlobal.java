package com.example.bidisha.ace;

import android.app.Application;

/**
 * Created by Bidisha on 14-03-2017.
 */

public class StoreGlobal extends Application {
    private int score;
    public int getGlobalScore()
    {
        return score;
    }
    public void initialScore()
    {
        score=0;
    }
    public void changeScore(int x,float y)
    {
        double z=(y/1000)*0.1;
        score+=x+z;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        score=0;
    }
}