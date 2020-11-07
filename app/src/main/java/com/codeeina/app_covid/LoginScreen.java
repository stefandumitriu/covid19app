package com.codeeina.app_covid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.NoSuchAlgorithmException;

public class LoginScreen extends AppCompatActivity {
    String name, pass;
    EditText name_tv, pass_tv;
    TextView login_title_tv, wrong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        name_tv = findViewById(R.id.LoginNameTV);
        pass_tv = findViewById(R.id.LoginPassTV);
        login_title_tv = findViewById(R.id.LoginTitle);
        wrong = findViewById(R.id.textView);
    }

    public void Log_in(View view) {
        wrong.setVisibility(View.GONE);
        name = name_tv.getText().toString();
//        Toast.makeText(this, pass_tv.getText().toString(), Toast.LENGTH_SHORT).show();

        pass = HashHelper.get_SecurePassword(pass_tv.getText().toString());

        if(Validate()) {
            Intent i = new Intent(this, Profile.class);
            startActivity(i);
        } else {
            wrong.setVisibility(View.VISIBLE);
        }
    }

    private Boolean Validate() {
        return pass.equals(getHash(name));
    }

    private String getHash(String name) {
        return HashHelper.get_SecurePassword("Parola");
    }

}