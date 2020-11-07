package com.codeeina.app_covid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.InputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button covidStats = findViewById(R.id.stats_main_screen);
        covidStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CovidStatsMenuActivity.class));
            }
        });
        InputStream inputStream = getResources().openRawResource(R.raw.out);
        CSVReader csvFile = new CSVReader(inputStream);
        List<String> scoreList = csvFile.read();
        System.out.println(scoreList);
    }

    public void OpenLogin(View view) {
        Intent i = new Intent(MainActivity.this, LoginScreen.class);
        startActivity(i);
    }
}