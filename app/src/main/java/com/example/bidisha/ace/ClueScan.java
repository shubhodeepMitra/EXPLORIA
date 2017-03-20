package com.example.bidisha.ace;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

public class ClueScan extends AppCompatActivity {
    public long time_left,sec,mins,timeValue;
    private static final String LOG_TAG = "Barcode Scanner API";
    private static final int PHOTO_REQUEST = 10;
    private TextView scanResults;
    private BarcodeDetector detector;
    private Uri imageUri;
    private static final int REQUEST_WRITE_PERMISSION = 20;
    private static final String SAVED_INSTANCE_URI = "uri";
    private static final String SAVED_INSTANCE_RESULT = "result";
    private int qNo = 0;
    private  int i=0;
    TextView currQ,question;
    public static String[] clues = new String[20];
    //first clue
    String clue1;
    static String[]  rando=new String[10];



    /***
     * @mitra00
     * function to generate the random clues
     * @param //savedInstanceState
     */
    void gen_clues()
    {
        /**
         * @mitra00
         * creatiing the random clues array
         */
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int ii=0; ii<19; ii++) {
            list.add(new Integer(ii));
        }
        Collections.shuffle(list);
        for (int ii=0; ii<10; ii++) {
            //System.out.println(list.get(i));
            rando[ii]=clues[list.get(ii)];
            //Toast.makeText(ClueScan.this,rando[ii],Toast.LENGTH_LONG).show();

        }
        //first clue same for all
        rando[0]="This app was recently inaugurated by Mysore MLA";
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cluescan);

        /**
         * @mitra00
         * receiving the values from the calling activity
         */

        Bundle b1 = getIntent().getExtras();
        if(b1 !=null) {
            timeValue = b1.getLong("time");
            i=b1.getInt("question_no");

        }
        else {
            /**
             * @mitra00
             * the limit for the time of the event
             */
            timeValue = 7200000;
        }
        clues[0]="Careers like rockets don’t always take on time! The trick is to keep them running always.";
        clues[1]="The place doesn’t smell pleasant! A new journey starts and the Earth Feels Happy..";
        clues[2]="I am dark and produce light and i can make something or destroy everything. Inspite of that i’m used everywhere.";
        clues[3]="akivuy is a place where everyone have their eyes on!";
        clues[4]="No one presses my first button now a days and i’m on top of all";
        clues[5]="Find me in the center of the place where sounds start and turn off";
        clues[6]="Take away standing infinity from Dhaku Mangal Singh";
        clues[7]="Under pressure is the only way i work and by myself is the only way i’m hurt and mysore express inaugurated me";
        clues[8]="Hercules has made his mark in sports";
       // clue1="This app was recently inaugurated by Mysore MLA";
        clues[10]="Some like me hot, Some like me cold, Some like me mild,Some like me bold";
        clues[11]="All husbands want their wives to be this";
        clues[12]="The best solution to attract the girls";
        clues[13]="I can tell you where we stand in the country";
        clues[14]="I start when the VVCE fest stops";
        clues[15]="The lead executive";
        clues[16]="I’m the lab which contains THINGS";
        clues[17]="Railway Workshop, BEML, Silk Factory and NIE have this incommon";
        clues[18]="There’s a place near by you will want to visit A portal place where people greet";
        clues[9]="I’m the only place whose identity remains the same even after you flip me horizontally";



        /**
         * @mitra00
         * to call the random generator only when the value of i is zero
         */
        if(i==0)
            gen_clues();


        /**
         * @mitra00
         * updating the field of the textview
         */
        String valueS = Integer.toString(i+1);
        currQ = (TextView) findViewById(R.id.currentQ);

        question=(TextView)findViewById(R.id.clue);
        question.setText(rando[i]);
        currQ.setText(valueS);





        Button button = (Button) findViewById(R.id.scanQR);
        scanResults = (TextView) findViewById(R.id.scanResults);
        if (savedInstanceState != null) {
            imageUri = Uri.parse(savedInstanceState.getString(SAVED_INSTANCE_URI));
            //scanResults.setText(savedInstanceState.getString(SAVED_INSTANCE_RESULT));
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ContextCompat.checkSelfPermission(ClueScan.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(ClueScan.this, new
                            String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_PERMISSION);

                }
                else
                {
                    takePicture();
                }

                }
        });



       /* TextView question=(TextView)findViewById(R.id.clue);
        switch (q)
        {
            case 1:
                question.setText(getString(R.string.clue1));
                break;
            case 2:
                question.setText(getString(R.string.clue2));
                break;
            case 3:
                question.setText(getString(R.string.clue3));
                break;
            case 4:
                question.setText(getString(R.string.clue4));
                break;
            case 5:
                question.setText(getString(R.string.clue5));
                break;

        }*/


                /*This is the code for timer*/



        final TextView time_mins= (TextView) findViewById(R.id.time_mins);
        final TextView time_sec= (TextView) findViewById(R.id.time_sec);


        new CountDownTimer(timeValue, 1000) {

            public void onTick(long millisUntilFinished) {
                time_left=millisUntilFinished;
                sec=(millisUntilFinished / 1000)%60;
                mins=millisUntilFinished / (1000*60);
                if(mins<10)
                    time_mins.setText("0"+millisUntilFinished / (1000*60));
                else
                    time_mins.setText(""+millisUntilFinished / (1000*60));
                if(sec<10)
                    time_sec.setText(": 0" + (millisUntilFinished / 1000)%60);
                else
                    time_sec.setText(": " + (millisUntilFinished / 1000)%60);

            }


            public void onFinish() {
                Intent i=new Intent(ClueScan.this,ScoreCal.class);
                /**
                 * @mitra00
                 * setting the time left to zero
                 */
                ((StoreGlobal) getApplication()).setTimeToZero();
                startActivity(i);
                ClueScan.this.finish();

            }
        }.start();


        detector = new BarcodeDetector.Builder(getApplicationContext())
                .setBarcodeFormats(Barcode.DATA_MATRIX | Barcode.QR_CODE | Barcode.ALL_FORMATS)
                .build();
        if (!detector.isOperational()) {
            scanResults.setText("Could not set up the detector!");
            return;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_WRITE_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    takePicture();
                } else {
                    Toast.makeText(ClueScan.this, "Permission Denied!", Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == PHOTO_REQUEST && resultCode == RESULT_OK) {
            launchMediaScanIntent();
            try {
                Bitmap bitmap = decodeBitmapUri(this, imageUri);
                if (detector.isOperational() && bitmap != null) {
                    Frame frame = new Frame.Builder().setBitmap(bitmap).build();
                    SparseArray<Barcode> barcodes = detector.detect(frame);
                    for (int index = 0; index < barcodes.size(); index++) {
                        Barcode code = barcodes.valueAt(index);
                        //scanResults.setText(/*scanResults.getText() +*/code.displayValue + "\n");


                        // TextView currQ = (TextView) findViewById(R.id.currentQ);

                        /**
                         * Comparing with the questions
                         *
                         */
                        String valueS = Integer.toString(i+1);


                        switch (i) {
                            case 0:
                                //setting the current question value
                                currQ.setText(valueS);
                                //Log.d("mesg",rando[i]);
                                question.setText(rando[i]);


                                if (code.displayValue.compareToIgnoreCase(rando[i]) == 0) {
                                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT)
                                            .show();
                                    ((StoreGlobal) getApplication()).changeScore(5, time_left);
                                    i++;

                                    Intent in = new Intent(this, ClueScan.class);
                                    //time left value
                                    in.putExtra("time",time_left-500);
                                    //question number
                                    in.putExtra("question_no",i);
                                    startActivity(in);
                                    ClueScan.this.finish();
                                } else {
                                    Toast.makeText(this, "You are at wrong place", Toast.LENGTH_LONG)
                                            .show();
                                }
                                break;
                            case 1:

                                currQ.setText(valueS);
                                // question.setText(rando[i]);
                                question.setText(rando[i]);

                                if (code.displayValue.compareToIgnoreCase(rando[i]) == 0) {
                                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT)
                                            .show();
                                    ((StoreGlobal) getApplication()).changeScore(5, time_left);
                                    ++i;
                                    Intent in = new Intent(this, ClueScan.class);
                                    //time left value
                                    in.putExtra("time",time_left-500);
                                    in.putExtra("question_no",i);

                                    startActivity(in);
                                    ClueScan.this.finish();
                                } else {
                                    Toast.makeText(this, "You are at wrong place", Toast.LENGTH_LONG)
                                            .show();
                                }
                                break;
                            case 2:
                                currQ.setText(valueS);
                                // question.setText(rando[i]);
                                question.setText(rando[i]);
                                if (code.displayValue.compareToIgnoreCase(rando[i]) == 0) {
                                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT)
                                            .show();
                                    ((StoreGlobal) getApplication()).changeScore(5, time_left);
                                    ++i;
                                    Intent in = new Intent(this, ClueScan.class);
                                    //time left value
                                    in.putExtra("time",time_left-500);
                                    //question number
                                    in.putExtra("question_no",i);

                                    startActivity(in);
                                    ClueScan.this.finish();
                                } else {
                                    Toast.makeText(this, "You are at wrong place", Toast.LENGTH_LONG)
                                            .show();
                                }
                                break;
                            case 3:
                                currQ.setText(valueS);
                                // question.setText(rando[i]);
                                question.setText(rando[i]);
                                if (code.displayValue.compareToIgnoreCase(rando[i]) == 0) {
                                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT)
                                            .show();
                                    ((StoreGlobal) getApplication()).changeScore(5, time_left);
                                    ++i;

                                    Intent in = new Intent(this, ClueScan.class);
                                    //time left value
                                    in.putExtra("time",time_left-500);
                                    //question number
                                    in.putExtra("question_no",i);

                                    startActivity(in);
                                    ClueScan.this.finish();
                                } else {
                                    Toast.makeText(this, "You are at wrong place", Toast.LENGTH_LONG)
                                            .show();
                                }
                                break;
                            case 4:
                                currQ.setText(valueS);
                                // question.setText(rando[i]);
                                question.setText(rando[i]);
                                if (code.displayValue.compareToIgnoreCase(rando[i]) == 0) {
                                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT)
                                            .show();
                                    ((StoreGlobal) getApplication()).changeScore(5, time_left);
                                    ++i;

                                    Intent in = new Intent(this, ClueScan.class);
                                    //time left value
                                    in.putExtra("time",time_left-500);
                                    //question number
                                    in.putExtra("question_no",i);

                                    startActivity(in);
                                    ClueScan.this.finish();
                                } else {
                                    Toast.makeText(this, "You are at wrong place", Toast.LENGTH_LONG)
                                            .show();
                                }
                                break;
                            case 5:
                                //setting the current question value
                                currQ.setText(valueS);
                                //Log.d("mesg",rando[i]);
                                question.setText(rando[i]);


                                if (code.displayValue.compareToIgnoreCase(rando[i]) == 0) {
                                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT)
                                            .show();
                                    ((StoreGlobal) getApplication()).changeScore(5, time_left);
                                    i++;

                                    Intent in = new Intent(this, ClueScan.class);
                                    //time left value
                                    in.putExtra("time",time_left-500);
                                    //question number
                                    in.putExtra("question_no",i);
                                    startActivity(in);
                                    ClueScan.this.finish();
                                } else {
                                    Toast.makeText(this, "You are at wrong place", Toast.LENGTH_LONG)
                                            .show();
                                }
                                break;
                            case 6:
                                //setting the current question value
                                currQ.setText(valueS);
                                //Log.d("mesg",rando[i]);
                                question.setText(rando[i]);


                                if (code.displayValue.compareToIgnoreCase(rando[i]) == 0) {
                                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT)
                                            .show();
                                    ((StoreGlobal) getApplication()).changeScore(5, time_left);
                                    i++;

                                    Intent in = new Intent(this, ClueScan.class);
                                    //time left value
                                    in.putExtra("time",time_left-500);
                                    //question number
                                    in.putExtra("question_no",i);
                                    startActivity(in);
                                    ClueScan.this.finish();
                                } else {
                                    Toast.makeText(this, "You are at wrong place", Toast.LENGTH_LONG)
                                            .show();
                                }
                                break;
                            case 7:
                                //setting the current question value
                                currQ.setText(valueS);
                                //Log.d("mesg",rando[i]);
                                question.setText(rando[i]);


                                if (code.displayValue.compareToIgnoreCase(rando[i]) == 0) {
                                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT)
                                            .show();
                                    ((StoreGlobal) getApplication()).changeScore(5, time_left);
                                    i++;

                                    Intent in = new Intent(this, ClueScan.class);
                                    //time left value
                                    in.putExtra("time",time_left-500);
                                    //question number
                                    in.putExtra("question_no",i);
                                    startActivity(in);
                                    ClueScan.this.finish();
                                } else {
                                    Toast.makeText(this, "You are at wrong place", Toast.LENGTH_LONG)
                                            .show();
                                }
                                break;
                            case 8:
                                //setting the current question value
                                currQ.setText(valueS);
                                //Log.d("mesg",rando[i]);
                                question.setText(rando[i]);


                                if (code.displayValue.compareToIgnoreCase(rando[i]) == 0) {
                                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT)
                                            .show();
                                    ((StoreGlobal) getApplication()).changeScore(5, time_left);
                                    i++;

                                    Intent in = new Intent(this, ClueScan.class);
                                    //time left value
                                    in.putExtra("time",time_left-500);
                                    //question number
                                    in.putExtra("question_no",i);
                                    startActivity(in);
                                    ClueScan.this.finish();
                                } else {
                                    Toast.makeText(this, "You are at wrong place", Toast.LENGTH_LONG)
                                            .show();
                                }
                                break;
                            case 9:
                                //setting the current question value
                                currQ.setText(valueS);
                                //Log.d("mesg",rando[i]);
                                question.setText(rando[i]);


                                if (code.displayValue.compareToIgnoreCase(rando[i]) == 0) {
                                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT)
                                            .show();
                                    ((StoreGlobal) getApplication()).changeScore(5, time_left);
                                    i++;

                                    Intent i=new Intent(ClueScan.this,ScoreCal.class);
                                    startActivity(i);
                                    ClueScan.this.finish();

                                } else {
                                    Toast.makeText(this, "You are at wrong place", Toast.LENGTH_LONG)
                                            .show();
                                }
                                break;


                        }
                        //Required only if you need to extract the type of barcode
                        int type = barcodes.valueAt(index).valueFormat;
                        switch (type) {
                            case Barcode.CONTACT_INFO:
                                Log.i(LOG_TAG, code.contactInfo.title);
                                break;
                            case Barcode.EMAIL:
                                Log.i(LOG_TAG, code.email.address);
                                break;
                            case Barcode.ISBN:
                                Log.i(LOG_TAG, code.rawValue);
                                break;
                            case Barcode.PHONE:
                                Log.i(LOG_TAG, code.phone.number);
                                break;
                            case Barcode.PRODUCT:
                                Log.i(LOG_TAG, code.rawValue);
                                break;
                            case Barcode.SMS:
                                Log.i(LOG_TAG, code.sms.message);
                                break;
                            case Barcode.TEXT:
                                Log.i(LOG_TAG, code.rawValue);
                                break;
                            case Barcode.URL:
                                Log.i(LOG_TAG, "url: " + code.url.url);
                                break;
                            case Barcode.WIFI:
                                Log.i(LOG_TAG, code.wifi.ssid);
                                break;
                            case Barcode.GEO:
                                Log.i(LOG_TAG, code.geoPoint.lat + ":" + code.geoPoint.lng);
                                break;
                            case Barcode.CALENDAR_EVENT:
                                Log.i(LOG_TAG, code.calendarEvent.description);
                                break;
                            case Barcode.DRIVER_LICENSE:
                                Log.i(LOG_TAG, code.driverLicense.licenseNumber);
                                break;
                            default:
                                Log.i(LOG_TAG, code.rawValue);
                                break;
                        }
                    }
                    if (barcodes.size() == 0) {
                        scanResults.setText("Scan Failed: Found nothing to scan");
                    }
                } else {
                    scanResults.setText("Could not set up the detector!");
                }
            } catch (Exception e) {
                Toast.makeText(this, "Failed to load Image", Toast.LENGTH_SHORT)
                        .show();
                Log.e(LOG_TAG, e.toString());
            }
        }
    }

    private void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File photo = new File(Environment.getExternalStorageDirectory(), "picture1.jpg");
        // imageUri = Uri.fromFile(photo);
        imageUri = FileProvider.getUriForFile(ClueScan.this,
                BuildConfig.APPLICATION_ID + ".provider",
                photo);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, PHOTO_REQUEST);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (imageUri != null) {
            outState.putString(SAVED_INSTANCE_URI, imageUri.toString());
            outState.putString(SAVED_INSTANCE_RESULT, scanResults.getText().toString());
        }
        super.onSaveInstanceState(outState);
    }

    private void launchMediaScanIntent() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        mediaScanIntent.setData(imageUri);
        this.sendBroadcast(mediaScanIntent);
    }

    private Bitmap decodeBitmapUri(Context ctx, Uri uri) throws FileNotFoundException {
        int targetW = 600;
        int targetH = 600;
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(ctx.getContentResolver().openInputStream(uri), null, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;

        return BitmapFactory.decodeStream(ctx.getContentResolver()
                .openInputStream(uri), null, bmOptions);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Are you sure you want to QUIT?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i=new Intent(ClueScan.this,ScoreCal.class);
                        startActivity(i);
                        ClueScan.this.finish();

                    }
                }).setNegativeButton("No", null).show();

    }

}