package com.example.srimadhan11.madlabexp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_restart:
                recreate();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()){
            case R.id.nav_exp_1:
                intent = new Intent(this, Exp1Activity.class);
                break;
            case R.id.nav_exp_2:
                intent = new Intent(this, Exp2Activity.class);
                break;
            case R.id.nav_exp_3:
                intent = new Intent(this, Exp3Activity.class);
                break;
            case R.id.nav_exp_4:
                intent = new Intent(this, Exp4Activity.class);
                break;
            case R.id.nav_exp_5:
                intent = new Intent(this, Exp5Activity.class);
                break;
            case R.id.nav_exp_6:
                intent = new Intent(this, Exp6Activity.class);
                break;
            case R.id.nav_exp_7:
                intent = new Intent(this, Exp7Activity.class);
                break;
            case R.id.nav_exp_8:
                intent = new Intent(this, Exp8Activity.class);
                break;
            case R.id.nav_exp_9:
                intent = new Intent(this, Exp9Activity.class);
                break;
            case R.id.nav_exp_10:
                intent = new Intent(this, Exp10Activity.class);
                break;
            case R.id.nav_exp_11:
                intent = new Intent(this, Exp11Activity.class);
                break;
        }
        if (intent != null){
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
