package com.example.crenscendoscoutingapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TeleopActivity extends AppCompatActivity {

    int teleopScoreSpeaker = 0;

    int teleopScoreAmp = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teleop_activity);

        TextView teleopSpeakerScore = (TextView) findViewById(R.id.teleopSpeakerScore);
        TextView teleopAmpScore = (TextView) findViewById(R.id.teleopAmpScore);
        Button notesScoredSpeakerUpTeleop = (Button) findViewById(R.id.notesScoredSpeakerUpTeleop);
        Button notesScoredSpeakerDownTeleop = (Button) findViewById(R.id.notesScoredSpeakerDownTeleop);
        Button notesScoredAmpUpTeleop = (Button) findViewById(R.id.notesScoredAmpUpTeleop);
        Button notesScoredAmpDownTeleop = (Button) findViewById(R.id.notesScoredAmpDownTeleop);
        RadioGroup endStates = (RadioGroup) findViewById(R.id.endStates);
        RadioButton parkedButton = (RadioButton) findViewById(R.id.parkedButton);
        RadioButton chainButton = (RadioButton) findViewById(R.id.chainButton);
        RadioButton harmonyButton = (RadioButton) findViewById(R.id.harmonyButton);
        RadioButton harmonyButton2 = (RadioButton) findViewById(R.id.harmonyButton2);
        RadioButton noneButton = (RadioButton) findViewById(R.id.noneButton);
        CheckBox hpScore = (CheckBox) findViewById(R.id.hpScore);
        CheckBox coopButton = (CheckBox) findViewById(R.id.coopButton);

        notesScoredSpeakerUpTeleop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teleopScoreSpeaker++;
                teleopSpeakerScore.setText(String.valueOf(teleopScoreSpeaker));
            }
        });

        notesScoredSpeakerDownTeleop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teleopScoreSpeaker--;
                teleopSpeakerScore.setText(String.valueOf(teleopScoreSpeaker));
            }
        });

        notesScoredAmpUpTeleop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teleopScoreAmp++;
                teleopAmpScore.setText(String.valueOf(teleopScoreAmp));
            }
        });

        notesScoredAmpDownTeleop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teleopScoreAmp--;
                teleopAmpScore.setText(String.valueOf(teleopScoreAmp));
            }
        });




    }
}
