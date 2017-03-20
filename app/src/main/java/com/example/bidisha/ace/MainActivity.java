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

    /**
     * @mitra00
     * the variable t is for the time limit of the whole event
     */
    long t=7200000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("MainActivity","This is above activity_main");
        setContentView(R.layout.activity_main);
        Log.i("MainActivity","This is below activity_main");

        /**
         * @mitra00
         * this is for the button that keeps the check of the the checkbox
         * until the checkbox is not clicked one cannot start the app
         */
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
                /**
                 * @mitra00
                 * fetching the password form the edittext
                 */
                EditText ans = (EditText) findViewById(R.id.password);
                String ansS = ans.getText().toString();


                /**
                 * @mitra00
                 * fetching from the team name edit text
                 */
                EditText tName = (EditText) findViewById(R.id.name);
                String name=tName.getText().toString().trim();

                /**
                 * @mitra00
                 * Initializing the global team name variable
                 */
                ((StoreGlobal) getApplication()).teamName(name);

                /**
                 * @mitra00
                 * fetching the team name
                 */


                /**
                 * @mitra00
                 * check for password and the name entered is not empty
                 */
                if (ansS.compareToIgnoreCase("EXPLORIA") == 0 && name.length()>0){


                    /**
                     * @mitra00
                     * initiating the intent to the ClueScan class
                     */

                    Intent i = new Intent(this,ClueScan.class);
                     i.putExtra("question_no",0);
                     i.putExtra("time",t);

                    /**
                     * initiating the global score with zero
                     */
                    ((StoreGlobal) getApplication()).initialScore();
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