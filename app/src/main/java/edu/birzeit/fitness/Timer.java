package edu.birzeit.fitness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class Timer extends AppCompatActivity {
    private TextView timerText;
    private EditText timerEditM;
    private EditText timerEditS;

    private Button timerBtn;
    private Button resetBtn;

    private CountDownTimer countDownTimer;
    private long timeLeftInMilliseconds = 0;
    private Boolean timerRunning = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        Intent intent = getIntent();

        if(savedInstanceState != null){
            timeLeftInMilliseconds = savedInstanceState.getLong("millisLeft");
            timerRunning = savedInstanceState.getBoolean("timerRunning");
        }
        timerText = findViewById(R.id.timerText);
        timerEditM = findViewById(R.id.timerEditM);
        timerEditS = findViewById(R.id.timerEditS);

        timerBtn = findViewById(R.id.btnStart);
    //    timerText.setVisibility(View.INVISIBLE);
        resetBtn = findViewById(R.id.btnReset);

        timerBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            startStop();
            }
        });
        resetBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                reset();
            }
        });
    }

        @Override
        protected void onSaveInstanceState(Bundle bundle) {
            super.onSaveInstanceState(bundle);
            bundle.putLong("millisLeft", timeLeftInMilliseconds);
            bundle.putBoolean("timerRunning", timerRunning);
    }


    public void reset(){
        timerRunning = false;
        timeLeftInMilliseconds = 0;
        timerBtn.setText("START");
        timerText.setVisibility(View.INVISIBLE);
        timerEditM.setVisibility(View.VISIBLE);
        timerEditS.setVisibility(View.VISIBLE);

    }

    public void startStop(){
        if (timerRunning){
        stopTimer();

        } else {
            startTimer();
        }
    }

    public void startTimer(){
        timerEditM.setVisibility(View.INVISIBLE);
        timerEditS.setVisibility(View.INVISIBLE);
        timeLeftInMilliseconds = Long.valueOf(timerEditM.getText().toString()) * 60000;
        if (timeLeftInMilliseconds == 0){

            timerText.setText("Reset and enter timer value");

        }

        timerText.setVisibility(View.VISIBLE);
        countDownTimer = new CountDownTimer(timeLeftInMilliseconds, 1000) {
            @Override
            public void onTick(long l) {
                timeLeftInMilliseconds = l;
                updateTimer();
            }

            @Override
            public void onFinish() {

            }
        }.start();
        timerBtn.setText("PAUSE");
        timerRunning = true;
    }
    public void stopTimer(){
        countDownTimer.cancel();
        timerBtn.setText("START");

        timerRunning = false;
    }
    public void updateTimer(){


        int minutes = (int) timeLeftInMilliseconds / 60000;

        int seconds = (int) timeLeftInMilliseconds % 60000 / 1000;
    //   seconds += Long.valueOf(timerEditS.getText().toString());

        String timeLeftText;
        timeLeftText = ""+ minutes;
        timeLeftText += ":";

        if (seconds < 10)
            timeLeftText += "0";
        timeLeftText += seconds;
        timerText.setText(timeLeftText);
    }
}