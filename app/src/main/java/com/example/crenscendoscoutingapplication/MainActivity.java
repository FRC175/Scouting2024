package com.example.crenscendoscoutingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int autoSpeakerScore = 0;

    int autoAmpScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView autoScoreSpeaker = (TextView) findViewById(R.id.autoScoreSpeaker);
        TextView autoScoreAmp = (TextView) findViewById(R.id.autoScoreAmp);
        Button teleopButton = (Button) findViewById(R.id.teleopButton);
        Button notesScoredSpeakerUpAuto = (Button) findViewById(R.id.notesScoredSpeakerUpAuto);
        Button notesScoredSpeakerDownAuto = (Button) findViewById(R.id.notesScoredSpeakerDownAuto);
        Button notesScoredAmpUpAuto = (Button) findViewById(R.id.notesScoredAmpUpAuto);
        Button notesScoredAmpDownAuto = (Button) findViewById(R.id.notesScoredAmpDownAuto);
        CheckBox leftStartAuto = (CheckBox) findViewById(R.id.leftStartAuto);
        EditText matchNumber = (EditText) findViewById(R.id.matchNumber);
        EditText teamNumber = (EditText) findViewById(R.id.teamNumber);


        notesScoredSpeakerUpAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autoSpeakerScore++;
                autoScoreSpeaker.setText(String.valueOf(autoSpeakerScore));
            }
        });

        notesScoredSpeakerDownAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autoSpeakerScore--;
                autoScoreSpeaker.setText(String.valueOf(autoSpeakerScore));
            }
        });

        notesScoredAmpUpAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autoAmpScore++;
                autoScoreAmp.setText(String.valueOf(autoAmpScore));
            }
        });

        notesScoredAmpDownAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autoAmpScore--;
                autoScoreAmp.setText(String.valueOf(autoAmpScore));
            }
        });

        teleopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent teleopIntent = new Intent(MainActivity.this, realTeleop_activity.class);

               startActivity(teleopIntent);
            }
        });


    }

}