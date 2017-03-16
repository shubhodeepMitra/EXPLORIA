package com.example.bidisha.ace;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Bidisha on 14-03-2017.
 */

public class Questions extends AppCompatActivity implements View.OnClickListener {
    String q = "", valueS;
    int value;
    public long time_left, sec, mins, timeValue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions);
        Bundle b1 = getIntent().getExtras();
        if (b1 != null)
            timeValue = b1.getLong("time");

        else
            timeValue = 300000;
        value = b1.getInt("question_no");
        value += 1;
        valueS = Integer.toString(value);
        TextView currQ = (TextView) findViewById(R.id.currentQ);
        currQ.setText(valueS);
        TextView question=(TextView)findViewById(R.id.question);
        switch (value)
        {
            case 1:
                question.setText(getString(R.string.ques1));
                break;
            case 2:
                question.setText(getString(R.string.ques2));
                break;
            case 3:
                question.setText(getString(R.string.ques3));
                break;
            case 4:
                question.setText(getString(R.string.ques4));
                break;
            case 5:
                question.setText(getString(R.string.ques5));
                break;
            case 6:
                question.setText(getString(R.string.ques6));
                break;
        }
        Button submit = (Button) findViewById(R.id.submit);
        Button hint = (Button) findViewById(R.id.hint);
        submit.setOnClickListener(this);
        hint.setOnClickListener(this);
        //  TextView score=(TextView)findViewById(R.id.score);
        int a = ((StoreGlobal) getApplication()).getGlobalScore();
        Log.i("Questions", Integer.toString(a));

        /*This is the code for timer*/


        final TextView time_mins = (TextView) findViewById(R.id.time_mins);
        final TextView time_sec = (TextView) findViewById(R.id.time_sec);


        new CountDownTimer(timeValue, 1000) {

            public void onTick(long millisUntilFinished) {
                time_left = millisUntilFinished;
                sec = (millisUntilFinished / 1000) % 60;
                mins = millisUntilFinished / (1000 * 60);
                if (mins < 10)
                    time_mins.setText("0" + millisUntilFinished / (1000 * 60));
                else
                    time_mins.setText("0" + millisUntilFinished / (1000 * 60));
                if (sec < 10)
                    time_sec.setText(": 0" + (millisUntilFinished / 1000) % 60);
                else
                    time_sec.setText(": " + (millisUntilFinished / 1000) % 60);

            }


            public void onFinish() {
                time_mins.setText("DO");
                time_sec.setText("NE!");
            }
        }.start();


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.submit:
                EditText ans = (EditText) findViewById(R.id.ansQ);
                String ansS = ans.getText().toString();
                q = "ans" + valueS;
                switch (value) {
                    case 1:
                        if (ansS.compareToIgnoreCase(getString(R.string.ans1)) == 0) {
                            Toast.makeText(this, "Success", Toast.LENGTH_SHORT)
                                    .show();
                            ((StoreGlobal) getApplication()).changeScore(5, time_left);
                            Intent i = new Intent(this, ClueScan.class);
                            i.putExtra("question_no", value);
                            i.putExtra("time", time_left - 500);
                            startActivity(i);
                            Questions.this.finish();
                        } else
                            Toast.makeText(this, "Try again!!", Toast.LENGTH_SHORT)
                                    .show();
                        break;
                    case 2:
                        if (ansS.compareToIgnoreCase(getString(R.string.ans2)) == 0) {
                            Toast.makeText(this, "Success", Toast.LENGTH_SHORT)
                                    .show();
                            ((StoreGlobal) getApplication()).changeScore(5, time_left);
                            Intent i = new Intent(this, ClueScan.class);
                            i.putExtra("question_no", value);
                            //time left value
                            i.putExtra("time", time_left - 500);
                            startActivity(i);
                            Questions.this.finish();
                        } else
                            Toast.makeText(this, "Try again!!", Toast.LENGTH_SHORT)
                                    .show();
                        break;
                    case 3:
                        if (ansS.compareToIgnoreCase(getString(R.string.ans3)) == 0) {
                            Toast.makeText(this, "Success", Toast.LENGTH_SHORT)
                                    .show();
                            ((StoreGlobal) getApplication()).changeScore(5, time_left);
                            Intent i = new Intent(this, ClueScan.class);
                            i.putExtra("question_no", value);
                            //time left value
                            i.putExtra("time", time_left - 500);
                            startActivity(i);
                            Questions.this.finish();
                        } else
                            Toast.makeText(this, "Try again!!", Toast.LENGTH_SHORT)
                                    .show();
                        break;
                    case 4:
                        if (ansS.compareToIgnoreCase(getString(R.string.ans4)) == 0) {
                            Toast.makeText(this, "Success", Toast.LENGTH_SHORT)
                                    .show();
                            ((StoreGlobal) getApplication()).changeScore(5, time_left);
                            Intent i = new Intent(this, ClueScan.class);
                            i.putExtra("question_no", value);
                            //time left value
                            i.putExtra("time", time_left - 500);
                            startActivity(i);
                            Questions.this.finish();
                        } else
                            Toast.makeText(this, "Try again!!", Toast.LENGTH_SHORT)
                                    .show();
                        break;
                    case 5:
                        if (ansS.compareToIgnoreCase(getString(R.string.ans5)) == 0) {
                            Toast.makeText(this, "Success", Toast.LENGTH_SHORT)
                                    .show();
                            ((StoreGlobal) getApplication()).changeScore(5, time_left);
                            Intent i = new Intent(this, ClueScan.class);
                            i.putExtra("question_no", value);
                            //time left value
                            i.putExtra("time", time_left - 500);
                            startActivity(i);
                            Questions.this.finish();
                        } else
                            Toast.makeText(this, "Try again!!", Toast.LENGTH_SHORT)
                                    .show();
                        break;
                    case 6:
                        if (ansS.compareToIgnoreCase(getString(R.string.ans6)) == 0) {
                            Toast.makeText(this, "Success", Toast.LENGTH_SHORT)
                                    .show();
                            ((StoreGlobal) getApplication()).changeScore(5, time_left);
                            Intent i = new Intent(this, ScoreCal.class);
                            // i.putExtra("question_no", value);
                            //time left value
                            //i.putExtra("time",time_left-500);
                            startActivity(i);
                            Questions.this.finish();
                        }
                        break;
                }
                break;
            case R.id.hint:
                Log.i("Questions", "Hint Alert");
                String hintMsg="";
                switch (value)
                {
                    case 1:
                        hintMsg=getString(R.string.hint1);
                        break;
                    case 2:
                        hintMsg=getString(R.string.hint2);
                        break;
                    case 3:
                        hintMsg=getString(R.string.hint3);
                        break;
                    case 4:
                        hintMsg=getString(R.string.hint4);
                        break;
                    case 5:
                        hintMsg=getString(R.string.hint5);
                        break;
                    case 6:
                        hintMsg=getString(R.string.hint6);
                        break;

                }
                new AlertDialog.Builder(this)
                        .setTitle("Hint")
                        .setMessage(hintMsg)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                            }
                        })
                        .show();

                break;
        }
    }


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Are you sure you want to QUIT?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(Questions.this, ScoreCal.class);
                        startActivity(i);
                        Questions.this.finish();


                    }
                }).setNegativeButton("No", null).show();

    }
}
