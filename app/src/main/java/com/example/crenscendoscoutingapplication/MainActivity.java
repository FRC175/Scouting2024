package com.example.crenscendoscoutingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    ScoutData data = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView autoScoreSpeaker = findViewById(R.id.autoScoreSpeaker);
        TextView autoScoreAmp = findViewById(R.id.autoScoreAmp);
        Button teleopButton = findViewById(R.id.teleopButton);
        Button notesScoredSpeakerUpAuto = findViewById(R.id.notesScoredSpeakerUpAuto);
        Button notesScoredSpeakerDownAuto = findViewById(R.id.notesScoredSpeakerDownAuto);
        Button notesScoredAmpUpAuto = findViewById(R.id.notesScoredAmpUpAuto);
        Button notesScoredAmpDownAuto = findViewById(R.id.notesScoredAmpDownAuto);
        CheckBox leftStartAuto = findViewById(R.id.leftStartAuto);
        EditText matchNumber = findViewById(R.id.matchNumber);
        EditText teamNumber = findViewById(R.id.teamNumber);
        EditText yourName = findViewById(R.id.yourName);

        Intent intent = getIntent();
        data = new ScoutData();
        if (intent.hasExtra("ScoutData")) {
            String[] arrayData = intent.getStringArrayExtra("ScoutData");
            data = ScoutData.fromArray(arrayData);
        }

        autoScoreSpeaker.setText(String.valueOf(data.autoSpeakerScore));
        autoScoreAmp.setText(String.valueOf(data.autoAmpScore));
        leftStartAuto.setActivated(data.leftZone);
        matchNumber.setText(data.matchNumber);
        teamNumber.setText(data.teamNumber);
        yourName.setText(data.yourName);

        matchNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                data.matchNumber = charSequence.toString();
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });

        teamNumber.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                data.teamNumber = charSequence.toString();
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });

        yourName.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                data.yourName = charSequence.toString();
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });

        leftStartAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (leftStartAuto.isChecked()) {
                    data.leftZone = true;

                } else {
                    data.leftZone = false;
                }
            }
        });


        notesScoredSpeakerUpAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = Integer.parseInt(autoScoreSpeaker.getText().toString());
                if (value < 99) {
                    value++;
                    data.autoSpeakerScore = value;
                    autoScoreSpeaker.setText(String.valueOf(value));
                }
            }
        });

        notesScoredSpeakerDownAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = Integer.parseInt(autoScoreSpeaker.getText().toString());
                if (value > 0) {
                    value--;
                    data.autoSpeakerScore = value;
                    autoScoreSpeaker.setText(String.valueOf(value));
                }

            }
        });

        notesScoredAmpUpAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = Integer.parseInt(autoScoreAmp.getText().toString());
                if (value < 99) {
                    value++;
                    data.autoAmpScore = value;
                    autoScoreAmp.setText(String.valueOf(value));
                }
            }
        });

        notesScoredAmpDownAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = Integer.parseInt(autoScoreAmp.getText().toString());
                if (value > 0) {
                    value--;
                    data.autoAmpScore = value;
                    autoScoreAmp.setText(String.valueOf(value));
                }
            }
        });

        teleopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent teleopIntent = new Intent(MainActivity.this, realTeleop_activity.class);
               String[] dataArray = data.toArray();
               teleopIntent.putExtra("ScoutData", dataArray);

               String teamNumbers = teamNumber.getText().toString();
               saveToCsv(teamNumbers);

               startActivity(teleopIntent);
            }
        });
    }

    private void saveToCsv(String teamNumbers) {
        try {
//            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//            if (!(grantResults.length > 0) || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
//                Toast.makeText(this, "Permission denied. Couldn't save data to CSV.", Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//                ActivityCompat.requestPermissions(this,
//                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_STORAGE);
//            }

            String csvFileName = "team_data_.csv";
            File dirPath = this.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
            File csvFile = new File(dirPath, csvFileName);
            FileWriter dataWriter = new FileWriter(csvFile);
            dataWriter.append(teamNumbers);
            dataWriter.append("\n");
            dataWriter.flush();
            dataWriter.close();
//            try (BufferedReader reader = new BufferedReader(new FileReader(dataFile))) {
//                String line = null;
//                while((line = reader.readLine()) != null) {
//                    line = line;
//                }
//            }

            Toast.makeText(this, "Data saved to CSV at " + dirPath.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
          e.printStackTrace();
          Toast.makeText(this, "Error saving data to CSV",  Toast.LENGTH_SHORT).show();
        }
    }
}