package com.codeeina.app_covid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Profile extends AppCompatActivity {
    TextView nameTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!UserData.isLogged())
            startActivity(new Intent(this, LoginScreen.class));

        setContentView(R.layout.activity_profile);
        nameTV = findViewById(R.id.Profile_nameTV);
        nameTV.setText(UserData.getName());
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void Log_out(View view) {
        UserData.setName("");
        UserData.setLogged(false);
        startActivity(new Intent(this, LoginScreen.class));
    }
}