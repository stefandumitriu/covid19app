package com.codeeina.app_covid;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class RomaniaStatsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.romania_stats);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new CountryStatsFragment()).commit();
        BottomNavigationView nav =findViewById(R.id.bottom_navigation_romaniastats_screen);
        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment frag = null;
                switch(item.getItemId()) {
                    case R.id.ic_country:
                        frag = new CountryStatsFragment();
                        break;
                    case R.id.ic_county:
                        frag = new MyCountyStatsFragment();
                        break;
                    default:
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, frag).commit();

                return true;
            }
        });

    }
}
