package com.codeeina.app_covid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.security.NoSuchAlgorithmException;

public class LoginScreen extends AppCompatActivity {
    String name, pass;
    EditText name_tv, pass_tv;
    TextView login_title_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        name_tv = findViewById(R.id.LoginNameTV);
        pass_tv = findViewById(R.id.LoginPassTV);
        login_title_tv = findViewById(R.id.LoginTitle);
    }

    public void Log_in(View view) {
        name = name_tv.getText().toString();
        try {
            pass = HashHelper.get_SecurePassword(pass_tv.getText().toString());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}