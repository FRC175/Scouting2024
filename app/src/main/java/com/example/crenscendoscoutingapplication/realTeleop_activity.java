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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class realTeleop_activity extends AppCompatActivity {
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
        Button loseButton = findViewById(R.id.loseButton);
        Button winButton = findViewById(R.id.winButton);
        RadioGroup endStates = findViewById(R.id.endStates);
        RadioButton parkedButton = findViewById(R.id.parkedButton);
        RadioButton chainButton = findViewById(R.id.chainButton);
        RadioButton harmonyButton = findViewById(R.id.harmonyButton);
        RadioButton harmonyButton2 = findViewById(R.id.harmonyButton2);
        RadioButton noneButton = findViewById(R.id.noneButton);
        CheckBox hpScore = findViewById(R.id.hpScore);
        CheckBox coopButton = findViewById(R.id.coopButton);
        CheckBox carriedButton = findViewById(R.id.carriedButton);
        EditText comments = findViewById(R.id.comments);
        CheckBox trapButton = findViewById(R.id.trapButton);

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
        carriedButton.setActivated(data.carried);
        trapButton.setActivated(data.trap);

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
                if (value < 99) {
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

        notesScoredAmpUpTeleop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = Integer.parseInt(teleopAmpScore.getText().toString());
                if (value < 99) {
                    value++;
                    data.teleopAmpScore = value;
                    teleopAmpScore.setText(String.valueOf(value));
                }

            }
        });

        loseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                data.won = false;

                Intent autoIntent = new Intent(realTeleop_activity.this, MainActivity.class);
                autoIntent.putExtra("ScoutData", data.toArray());

                saveToCsv(data);

                startActivity(autoIntent);
            }
        });

        winButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.won = true;

                Intent autoIntent = new Intent(realTeleop_activity.this, MainActivity.class);
                autoIntent.putExtra("ScoutData", data.toArray());

                saveToCsv(data);

                startActivity(autoIntent);
            }
        });


        hpScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.humanScored = !data.humanScored;
            }
        });

        coopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.cooperationBonus = !data.cooperationBonus;
            }
        });

        carriedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                data.carried = carriedButton.isActivated();
                data.carried = !data.carried;
            }
        });


        trapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.trap = !data.trap;

            }
        });

       parkedButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               data.endGameState = "0";
           }
       });

       chainButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               data.endGameState = "1";
           }
       });

       harmonyButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               data.endGameState = "2";
           }
       });

       harmonyButton2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               data.endGameState = "3";
           }
       });

       noneButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               data.endGameState = "4";
           }
       });

        comments.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (data.comments.length() < 51) {
                    data.comments = charSequence.toString().toLowerCase();
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });

    }

    private void saveToCsv(ScoutData scoutData) {
        try {
            String[] dataStrings = scoutData.toArray();



            String csvFileName = "team_data_";
            File dirPath = this.getExternalFilesDir(Environment.getExternalStorageDirectory().getAbsolutePath());
            String currentQRGroup = "";
            File qrGroupFile = new File(dirPath, "qr_group.txt");
            try (BufferedReader reader = new BufferedReader(new FileReader(qrGroupFile))) {
                currentQRGroup = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            csvFileName += currentQRGroup + ".csv";
            File csvFile = new File(dirPath, csvFileName);
            FileWriter dataWriter = new FileWriter(csvFile, true);

            for (String value : scoutData.toArray()) {
                dataWriter.append(value).append(",");
            }

            dataWriter.append("\n");
            dataWriter.flush();
            dataWriter.close();

            Toast.makeText(this, "Data saved to CSV at " + dirPath.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error saving data to CSV",  Toast.LENGTH_SHORT).show();
        }
    }
}
