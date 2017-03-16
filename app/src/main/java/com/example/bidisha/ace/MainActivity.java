package com.example.bidisha.ace;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

    int quesNo=0;
    long t=300000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("MainActivity","This is above activity_main");
        setContentView(R.layout.activity_main);
        Log.i("MainActivity","This is below activity_main");
        final Button start = (Button) findViewById(R.id.button2);
        CheckBox agree = (CheckBox) findViewById(R.id.checkBox);
        agree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                {
                    start.setEnabled(true);
                }
                else
                {
                    start.setEnabled(false);
                }
            }

        });
        start.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.button2:
                EditText ans = (EditText) findViewById(R.id.password);
                String ansS = ans.getText().toString();

                /**
                 * check for password
                 */
                if (ansS.compareToIgnoreCase("EXPLORIA") == 0){
                Intent i = new Intent(this,Questions.class);
                i.putExtra("question_no",quesNo);
                i.putExtra("time",t);
                startActivity(i);
                MainActivity.this.finish();

                }
                else
                {
                    Toast.makeText(this, "Try again!!", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
        }

    }
}