package com.codeeina.app_covid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CovidStatsMenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.covidstats_menu);

        Button romaniaButton = findViewById(R.id.romania_stats_covidstats_screen);
        romaniaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CovidStatsMenuActivity.this, RomaniaStatsActivity.class));
            }
        });
    }
}
