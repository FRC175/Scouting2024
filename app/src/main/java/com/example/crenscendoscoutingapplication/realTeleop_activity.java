package com.example.crenscendoscoutingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class realTeleop_activity extends AppCompatActivity {

    int teleopScoreSpeaker = 0;

    int teleopScoreAmp = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_teleop);

        TextView teleopSpeakerScore = (TextView) findViewById(R.id.teleopSpeakerScore);
        TextView teleopAmpScore = (TextView) findViewById(R.id.teleopAmpScore2);
        Button notesScoredSpeakerUpTeleop = (Button) findViewById(R.id.notesScoredSpeakerUpTeleop);
        Button notesScoredSpeakerDownTeleop = (Button) findViewById(R.id.notesScoredSpeakerDownTeleop);
        Button notesScoredAmpUpTeleop = (Button) findViewById(R.id.notesScoredAmpUpTeleop);
        Button notesScoredAmpDownTeleop = (Button) findViewById(R.id.notesScoredAmpDownTeleop);
        Button endButton = (Button) findViewById(R.id.endButton);
        RadioGroup endStates = (RadioGroup) findViewById(R.id.endStates);
        RadioButton parkedButton = (RadioButton) findViewById(R.id.parkedButton);
        RadioButton chainButton = (RadioButton) findViewById(R.id.chainButton);
        RadioButton harmonyButton = (RadioButton) findViewById(R.id.harmonyButton);
        RadioButton harmonyButton2 = (RadioButton) findViewById(R.id.harmonyButton2);
        RadioButton noneButton = (RadioButton) findViewById(R.id.noneButton);
        CheckBox hpScore = (CheckBox) findViewById(R.id.hpScore);
        CheckBox coopButton = (CheckBox) findViewById(R.id.coopButton);
        EditText comments = (EditText) findViewById(R.id.comments);

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

        endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent autoIntent = new Intent(realTeleop_activity.this, MainActivity.class);
                String[] test = new String[] {"123"};
                autoIntent.putExtra("ScoutData", test);
                startActivity(autoIntent);
            }
        });



    }
}
