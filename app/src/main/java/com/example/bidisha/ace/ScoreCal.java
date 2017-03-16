package com.example.bidisha.ace;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.widget.TextView;

/**
 * Created by Bidisha on 14-03-2017.
 */

public class ScoreCal extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scorecal);

        /**
         * @mitra00
         * print the score
         */
        TextView sc = (TextView) findViewById(R.id.scFinal);
        int a = ((StoreGlobal) getApplication()).getGlobalScore();
        String b = Integer.toString(a);
        sc.setText(b);

        /**
         * print the team name
         */
        TextView ss = (TextView) findViewById(R.id.teamName);
        String s = ((StoreGlobal) getApplication()).getTeamName();

        ss.setText(s);

    }


    /**
     * Overiding the back button of the phone to exit the app and popping an alert
     */
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();

                    }
                }).setNegativeButton("No", null).show();

    }

}