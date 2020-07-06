// Nag Pacer - jamesliulondon@gmail.com
package com.accordops.nagpacer;


import android.os.CountDownTimer;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import android.media.MediaPlayer;



import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    CountDownTimer stopWatch = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.numbers, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);



    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        final String text = parent.getItemAtPosition(position).toString();
        //Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();

        // Set variables
        final TextView _tv = (TextView) findViewById(R.id.textReadOut);
        Long interval = Long.parseLong(text) * 60 * 1000;
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.beep04);

        if (stopWatch != null) stopWatch.cancel();
        // Start Countdown


        stopWatch = new CountDownTimer(interval, 1000) {

            @Override
            public void onTick(long interval) {
                _tv.setText("minutes: " + interval / (60 * 1000) + "\nseconds: " + (interval /  1000) % 60);
            }

            @Override
            public void onFinish() {
//                _tv.setText("done");
                stopWatch.start();
                mp.start();

            }
        }.start();


    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
