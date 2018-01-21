package com.example.srimadhan11.madlabexp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;

public class Exp11Activity extends AppCompatActivity {

    private PendingIntent pendingIntent;
    private AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exp11);

        final TimePicker alarmTimePicker = findViewById(R.id.timePicker);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        final Activity activity = this;

        findViewById(R.id.toggleButton).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ShortAlarm")
            @Override
            public void onClick(View v) {
                long time;
                if (((ToggleButton) v).isChecked()) {
                    Toast.makeText(activity, "ALARM ON", Toast.LENGTH_SHORT).show();
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
                    calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());

                    Intent intent = new Intent(activity, AlarmReceiver.class);
                    pendingIntent = PendingIntent.getBroadcast(activity, 0, intent, 0);
                    time = (calendar.getTimeInMillis() - (calendar.getTimeInMillis() % 60000));
                    if (System.currentTimeMillis() > time) {
                        time = time + (1000 * 60 * 60 * 24);
                    }
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time, 1000 * 30, pendingIntent);
                } else {
                    alarmManager.cancel(pendingIntent);
                    Toast.makeText(activity, "ALARM OFF", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}