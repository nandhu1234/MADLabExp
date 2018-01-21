package com.example.srimadhan11.madlabexp;

import android.annotation.SuppressLint;
import android.os.Message;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class Exp7Activity extends AppCompatActivity {

    private final int sleepTimeInMS = 5000;
    private int threadCounter;
    private TextView stateViewer;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            stateViewer.append("\nIn threadCounter " + msg.what);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exp7);

        threadCounter = 1;
        stateViewer = findViewById(R.id.stateViewer);
        stateViewer.setText(getResources().getText(R.string.main_thread));

        findViewById(R.id.threadKicker).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(sleepTimeInMS);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        handler.sendEmptyMessage(threadCounter++);
                    }
                }).start();
            }
        });
    }
}
