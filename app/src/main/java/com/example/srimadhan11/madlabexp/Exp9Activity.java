package com.example.srimadhan11.madlabexp;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class Exp9Activity extends AppCompatActivity {

    EditText data;
    Button write, read, clear;

    public void verifyStoragePermissions(Activity activity) {
        int REQUEST_EXTERNAL_STORAGE = 1;
        String[] PERMISSIONS_STORAGE = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exp9);
        verifyStoragePermissions(this);

        data = findViewById(R.id.data);
        write = findViewById(R.id.write_data);
        read = findViewById(R.id.read_data);
        clear = findViewById(R.id.clear);
        write.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("ResultOfMethodCallIgnored")
            @Override
            public void onClick(View v) {
                String message = data.getText().toString();
                try {
                    File f = new File(Environment.getExternalStorageDirectory().getPath() + "/myFile.txt");
                    Log.v("Write data", Environment.getExternalStorageDirectory().getPath() + "/myFile.txt");
                    f.createNewFile();
                    FileOutputStream fout = new FileOutputStream(f);
                    fout.write(message.getBytes());
                    fout.close();
                    Toast.makeText(getBaseContext(), "Data Written in SDCARD", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message;
                StringBuilder stringBuilder = new StringBuilder();
                try {
                    File f = new File(Environment.getExternalStorageDirectory().getPath() + "/myFile.txt");
                    FileInputStream fin = new FileInputStream(f);
                    BufferedReader br = new BufferedReader(new InputStreamReader(fin));
                    while ((message = br.readLine()) != null) {
                        stringBuilder.append(message);
                    }
                    data.setText(stringBuilder.toString());
                    br.close();
                    fin.close();
                    Toast.makeText(getBaseContext(), "Data Received from SDCARD", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.setText("");
            }
        });
    }
}