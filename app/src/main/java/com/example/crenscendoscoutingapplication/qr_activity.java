package com.example.crenscendoscoutingapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class qr_activity extends AppCompatActivity {

    protected String convCSVtoJSONS(String s) {
        int tabletID = 5;
        int qrGroup = 1;
        String[] components = s.split(",");
        String basis = "{\"t\":";
        basis += tabletID;
        basis += ",\"i\":";
        basis += qrGroup;
        basis += ",\"d\":[{";
        basis += "\"u\":\"";
        basis += components[0];
        basis += "\",\"tN\":";
        basis += components[1];
        basis += ",\"mN\":";
        basis += components[2];
        basis += ",\"aS\":";
        basis += components[3];
        basis += ",\"aA\":";
        basis += components[4];
        basis += ",\"l\":";
        basis += components[5];
        basis += ",\"tS\":";
        basis += components[6];
        basis += ",\"tA\":";
        basis += components[7];
        basis += ",\"eG\":";
        basis += components[8];
        basis += ",\"t\":";
        basis += components[9];
        basis += ",\"h\":";
        basis += components[10];
        basis += ",\"c\":";
        basis += components[11];
        basis += ",\"f\":";
        basis += components[12];
        basis += ",\"com\":\"";
        basis += components[13];
        basis += "\",\"w\":";
        basis += components[14];
        basis += "}]}";

        return basis;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        Button backButton = findViewById(R.id.backButton);
        Button qrButton = findViewById(R.id.qr);
        ImageView qrSpace = findViewById(R.id.qrSpace);
        QRCodeWriter writer = new QRCodeWriter();

        String csvFileName = "team_data_.csv";
        File dirPath = this.getExternalFilesDir(Environment.getExternalStorageDirectory().getAbsolutePath());
        File csvFile = new File(dirPath, csvFileName);





        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backIntent = new Intent(qr_activity.this, MainActivity.class);

                startActivity(backIntent);
            }
        });

        qrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String line = null;

                try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                    line = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                try {
                    BitMatrix bitMatrix = writer.encode(convCSVtoJSONS(line), BarcodeFormat.QR_CODE, 512, 512);
                    int width = bitMatrix.getWidth();
                    int height = bitMatrix.getHeight();
                    Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
                    for (int x = 0; x < width; x++) {
                        for (int y = 0; y < height; y++) {
                            bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                        }
                    }
                    ((ImageView) findViewById(R.id.qrSpace)).setImageBitmap(bmp);

                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        });

    }

}