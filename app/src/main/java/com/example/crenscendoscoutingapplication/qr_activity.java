package com.example.crenscendoscoutingapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class qr_activity extends AppCompatActivity {

    String tabletID;
    String currentQrGroup;
    String maxQRGroup;
    String currentQrCode;
    int maxQrCode;

    List<String> linesToEncode = new ArrayList<>();
    File dirPath;
    File qrGroupFile;
    String csvRootFileName = "team_data_";

    protected String getMatchDataHeader() {
        StringBuilder sb = new StringBuilder();
        return sb.append( "{\"t\":")
                .append(tabletID)
                .append(",\"i\":")
                .append(currentQrGroup)
                .append(",\"d\":[")
                .toString();
    }

    protected String convCSVtoJSONS(String s) {
        String[] components = s.split(",");
        String basis = "{";
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
        basis += "}";

        return basis;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dirPath = this.getExternalFilesDir(Environment.getExternalStorageDirectory().getAbsolutePath());
        qrGroupFile = new File(dirPath, "qr_group.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(qrGroupFile))) {
            maxQRGroup = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        currentQrGroup = "0";
        currentQrCode = "0";
        maxQrCode = extractLinesToEncode();

        setContentView(R.layout.activity_qr);

        Button backButton = findViewById(R.id.backButton);
        Button qrButton = findViewById(R.id.qr);
        Button qrGroupPlus = findViewById(R.id.qrButtonRight);
        Button qrGroupMinus = findViewById(R.id.qrButtonLeft);
        Button whichQRPlus = findViewById(R.id.whichRight);
        Button whichQRMinus = findViewById(R.id.whichLeft);
        TextView whichQRIndex = findViewById(R.id.whichCount);
        TextView qrGroupIndex = findViewById(R.id.qrCurrentIndex);
        Button updateButton = findViewById(R.id.updateButton);

        File metaTabletID = new File(dirPath, "tablet_id.txt");
        tabletID = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(metaTabletID))) {
            tabletID = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
                String matchTripleData = getMatchDataHeader();

                int qrIndex = Integer.parseInt(currentQrCode) * 3;
                int endIndex = Math.min(qrIndex + 3, linesToEncode.size());
                String[] matches = new String[(endIndex - qrIndex)];
                for (int i = qrIndex; i < endIndex; ++i) {
                    matches[i - qrIndex] = convCSVtoJSONS(linesToEncode.get(i));
                }

                matchTripleData += String.join(",", matches);
                matchTripleData += "]}";

                generateAndDisplayQr(matchTripleData);
            }
        });

        qrGroupMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (maxQrCode == 0) return;

                int val = Integer.parseInt(currentQrGroup);
                int qrMAX = Integer.parseInt(maxQRGroup);
                val = (val - 1) % (qrMAX + 1);
                if (val < 0) {
                    val = val * -1;
                }
                currentQrGroup = Integer.toString(val);
                qrGroupIndex.setText(currentQrGroup);

                maxQrCode = extractLinesToEncode();
                currentQrCode = "0";
                whichQRIndex.setText(currentQrCode);
            }
        });

        qrGroupPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (maxQrCode == 0) return;

                int val = Integer.parseInt(currentQrGroup);
                int qrMAX = Integer.parseInt(maxQRGroup);
                val = (val + 1) % (qrMAX + 1);
                if (val < 0) {
                    val = val * -1;
                }
                currentQrGroup = Integer.toString(val);
                qrGroupIndex.setText(currentQrGroup);

                maxQrCode = extractLinesToEncode();
                currentQrCode = "0";
                whichQRIndex.setText(currentQrCode);
            }
        });

        whichQRMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (maxQrCode == 0) return;

                int val = Integer.parseInt(currentQrCode);
                int qrMAX = maxQrCode;
                val = (val - 1) % (qrMAX);
                if (val < 0) {
                    val = val * -1;
                }
                currentQrCode = Integer.toString(val);
                whichQRIndex.setText(currentQrCode);
            }
        });

        whichQRPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (maxQrCode == 0) return;

                int val = Integer.parseInt(currentQrCode);
                int qrMAX = maxQrCode;
                val = (val + 1) % qrMAX;
                if (val < 0) {
                    val = val * -1;
                }
                currentQrCode = Integer.toString(val);
                whichQRIndex.setText(currentQrCode);
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try (FileWriter dataWriter = new FileWriter(qrGroupFile, false)) {
                    int nextQR = Integer.parseInt(maxQRGroup) + 1;
                    dataWriter.write("" + nextQR);
                    dataWriter.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }



            }
        });

    }

    private int extractLinesToEncode() {
        File csvFile = new File(dirPath, csvRootFileName + currentQrGroup + ".csv");

        if (!csvFile.exists()) {
            linesToEncode = new ArrayList<>();
            return 0;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            linesToEncode = reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return (linesToEncode.size() % 3 == 0) ? linesToEncode.size() / 3 : linesToEncode.size() / 3 + 1;
    }

    private void generateAndDisplayQr(String matchDataTriple) {
        try {
            QRCodeWriter writer = new QRCodeWriter();
            BitMatrix bitMatrix = writer.encode(matchDataTriple, BarcodeFormat.QR_CODE, 512, 512);
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

}