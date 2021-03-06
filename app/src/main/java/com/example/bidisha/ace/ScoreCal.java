package com.example.bidisha.ace;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Bidisha on 14-03-2017.
 */

public class ScoreCal extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scorecal);

        /**
         *
         * print the score
         */
        TextView sc = (TextView) findViewById(R.id.scFinal);
        int a = ((StoreGlobal) getApplication()).getGlobalScore();
        String b = Integer.toString(a);
        sc.setText(b);

        /**
         * @mitra00
         * print the team name
         */
        TextView ss = (TextView) findViewById(R.id.teamName);
        String s = ((StoreGlobal) getApplication()).getTeamName();

        ss.setText(s);

        /**
         * @mitra00
         * to print the time left
         */
        TextView tLeft = (TextView) findViewById(R.id.timeLeft);
        double aa = ((StoreGlobal) getApplication()).getTimeleft();
        String bb = Double.toString(aa);
        tLeft.setText("Time Left \n"+bb+"s");


        ss.setText(s);

        /**
         * @mitra00
         * toast to instruct them about further actions
         */
        Toast.makeText(this, "Take ScreenShot!!", Toast.LENGTH_LONG)
                .show();

        Toast.makeText(this, "Report Back To Organizers", Toast.LENGTH_LONG)
                .show();

    }


    /**
     * Overiding the back button of the phone to exit the app and popping an alert
     */
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("EXIT?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();

                    }
                }).setNegativeButton("No", null).show();

    }

}