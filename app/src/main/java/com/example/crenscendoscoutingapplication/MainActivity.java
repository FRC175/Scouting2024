package com.example.crenscendoscoutingapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

  // Line of code to define this as an integer not really sure why  private static final int REQUEST_WRITE_STORAGE = 0;
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

               String teamNumbers = teamNumber.getText().toString();
               saveToCsv(teamNumbers, );

               startActivity(teleopIntent);
            }
        });


    }

    private void saveToCsv(String teamNumbers, int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        try {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            if (!(grantResults.length > 0) || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission denied. Couldn't save data to CSV.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_STORAGE);

            }

           String teamData = "team_data_.csv";
           File dataFile = new File(Environment.getExternalStorageDirectory(), teamData);
           FileWriter dataWriter = new FileWriter(dataFile, true);
           dataWriter.append(teamNumbers);
           dataWriter.append("\n");
           dataWriter.close();
            Toast.makeText(this, "Data saved to CSV", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
          e.printStackTrace();
          Toast.makeText(this, "Error saving data to CSV",  Toast.LENGTH_SHORT).show();
        }


    }

//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            saveToCsv();
//        } else {
//            Toast.makeText(this, "Permission denied. Couldn't save data to CSV.", Toast.LENGTH_SHORT).show();
//        }
//    }
}