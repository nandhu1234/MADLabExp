package com.example.srimadhan11.madlabexp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class Exp8Activity extends AppCompatActivity {

    private TextView latitude;
    private TextView longitude;
    private TextView choice;
    private CheckBox fineAcc;
    private TextView provText;
    private String provider;
    private Criteria criteria;
    private Activity activity;
    private final int REQUEST_LOCATION_ACCESS = 1;

    @SuppressLint({"MissingPermission", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exp8);
        activity = this;
        verifyLocationPermissions(activity);

        latitude = findViewById(R.id.lat);
        longitude = findViewById(R.id.lon);
        provText = findViewById(R.id.prov);
        choice = findViewById(R.id.choice);
        fineAcc = findViewById(R.id.fineAccuracy);
        Button choose = findViewById(R.id.chooseRadio);

        latitude.setText(getResources().getText(R.string.lat) + ": -");
        longitude.setText(getResources().getText(R.string.lon) + ": -");

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);

        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyLocationPermissions(activity);
                if (fineAcc.isChecked()) {
                    criteria.setAccuracy(Criteria.ACCURACY_FINE);
                    choice.setText(getResources().getText(R.string.fine_accuracy_selected));
                } else {
                    criteria.setAccuracy(Criteria.ACCURACY_COARSE);
                    choice.setText(getResources().getText(R.string.coarse_accuracy_selected));
                }
            }
        });
        criteria.setCostAllowed(false);
        if (locationManager == null) {
            Toast.makeText(this, "loactionManager is null", Toast.LENGTH_SHORT).show();
            return;
        }
        provider = locationManager.getBestProvider(criteria, false);

        MyLocationListener mylistener = new MyLocationListener();

        if (provider != null) {
            Location location = locationManager.getLastKnownLocation(provider);
            if (location != null) {
                mylistener.onLocationChanged(location);
            } else {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
            locationManager.requestLocationUpdates(provider, 200, 1, mylistener);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_LOCATION_ACCESS: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission granted
                    recreate();
                }
                else {
                    // Permission denied
                    Toast.makeText(this, "Location can't be determined!\nPermission denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void verifyLocationPermissions(Activity activity) {
        String[] PERMISSIONS_LOCATION = {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        };
        // Check if we have location permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, PERMISSIONS_LOCATION, REQUEST_LOCATION_ACCESS);
        }
    }

    private class MyLocationListener implements LocationListener {

        @SuppressLint("SetTextI18n")
        @Override
        public void onLocationChanged(Location location) {
            latitude.setText(getResources().getText(R.string.lon) + ": " + Double.toString(location.getLatitude()));
            longitude.setText(getResources().getText(R.string.lon) + ": " + Double.toString(location.getLongitude()));
            provText.setText(provider + " provider has been selected.");

            Toast.makeText(getApplicationContext(), "Location changed!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Toast.makeText(getApplicationContext(), provider + "'s status changed to " + status + "!",
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onProviderEnabled(String provider) {
            Toast.makeText(getApplicationContext(), "Provider " + provider + " enabled!",
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onProviderDisabled(String provider) {
            Toast.makeText(getApplicationContext(), "Provider " + provider + " disabled!",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
