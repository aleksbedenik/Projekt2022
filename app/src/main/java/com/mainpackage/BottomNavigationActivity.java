package com.mainpackage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        //nastavi prvi fragement da fragement aktivnosti
        getSupportFragmentManager().beginTransaction().replace(R.id.fragement_container, new ActivitysFragement()).commit();
        //nastavimo navigation bar na aktivnosti
        setNavigationBarAktivnosti();

    }
    public void setNavigationBarAktivnosti(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setTitle("Aktivnosti");
        actionBar.show();
    }
    public void setNavigationBarMojProfil(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setTitle("Moj Profil");
        actionBar.show();
    }
    public void setNavigationBarDrugeAplikacije(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setTitle("Druge Aplikacije");
        actionBar.show();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch(item.getItemId()){
                        case R.id.nav_home:
                            selectedFragment = new ActivitysFragement();
                            setNavigationBarAktivnosti();
                            break;
                        case R.id.nav_dashboard:
                            selectedFragment = new OtherAppsFragement();
                            setNavigationBarDrugeAplikacije();
                            break;
                        case R.id.nav_profile:
                            selectedFragment = new MyProfileFragement();
                            setNavigationBarMojProfil();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragement_container,
                            selectedFragment).commit();
                    return true;
                }
            };
}