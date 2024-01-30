package com.example.crenscendoscoutingapplication;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Objects;

public class realTeleop_activity extends AppCompatActivity {

    int teleopScoreSpeaker = 0;

    int teleopScoreAmp = 0;

    ScoutData data = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_teleop);

        TextView teleopSpeakerScore = findViewById(R.id.teleopSpeakerScore);
        TextView teleopAmpScore = findViewById(R.id.teleopAmpScore2);
        Button notesScoredSpeakerUpTeleop = findViewById(R.id.notesScoredSpeakerUpTeleop);
        Button notesScoredSpeakerDownTeleop = findViewById(R.id.notesScoredSpeakerDownTeleop);
        Button notesScoredAmpUpTeleop = findViewById(R.id.notesScoredAmpUpTeleop);
        Button notesScoredAmpDownTeleop = findViewById(R.id.notesScoredAmpDownTeleop);
        Button endButton = findViewById(R.id.endButton);
        RadioGroup endStates = findViewById(R.id.endStates);
        RadioButton parkedButton = findViewById(R.id.parkedButton);
        RadioButton chainButton = findViewById(R.id.chainButton);
        RadioButton harmonyButton = findViewById(R.id.harmonyButton);
        RadioButton harmonyButton2 = findViewById(R.id.harmonyButton2);
        RadioButton noneButton = findViewById(R.id.noneButton);
        CheckBox hpScore = findViewById(R.id.hpScore);
        CheckBox coopButton = findViewById(R.id.coopButton);
        EditText comments = findViewById(R.id.comments);

        Intent intent = getIntent();
        data = new ScoutData();
        if (intent.hasExtra("ScoutData")) {
            String[] arrayData = intent.getStringArrayExtra("ScoutData");
            data = ScoutData.fromArray(arrayData);
        }

        teleopSpeakerScore.setText(String.valueOf(data.teleopSpeakerScore));
        teleopAmpScore.setText(String.valueOf(data.teleopAmpScore));
        hpScore.setActivated(data.humanScored);
        coopButton.setActivated(data.cooperationBonus);
        comments.setText(data.comments);
        parkedButton.setActivated(data.endGameState.equals("Parked"));
        chainButton.setActivated(data.endGameState.equals("Chain"));
        harmonyButton.setActivated(data.endGameState.equals("Harmonized"));
        harmonyButton2.setActivated(data.endGameState.equals("Harmonized With 2"));
        noneButton.setActivated(data.endGameState.equals("N/A"));

        notesScoredSpeakerDownTeleop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = Integer.parseInt(teleopSpeakerScore.getText().toString());
                if (value > 0) {
                    value--;
                    data.teleopSpeakerScore = value;
                    teleopSpeakerScore.setText(String.valueOf(value));
                }

            }
        });

        notesScoredSpeakerUpTeleop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = Integer.parseInt(teleopSpeakerScore.getText().toString());
                if (value > 0) {
                    value++;
                    data.teleopSpeakerScore = value;
                    teleopSpeakerScore.setText(String.valueOf(value));
                }

            }
        });

        notesScoredAmpDownTeleop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = Integer.parseInt(teleopAmpScore.getText().toString());
                if (value > 0) {
                    value--;
                    data.teleopAmpScore = value;
                    teleopAmpScore.setText(String.valueOf(value));
                }

            }
        });

        notesScoredAmpDownTeleop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = Integer.parseInt(teleopAmpScore.getText().toString());
                if (value > 0) {
                    value++;
                    data.teleopAmpScore = value;
                    teleopAmpScore.setText(String.valueOf(value));
                }

            }
        });

        endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent autoIntent = new Intent(realTeleop_activity.this, MainActivity.class);
                autoIntent.putExtra("ScoutData", data.toArray());
                startActivity(autoIntent);
            }
        });



    }
}
