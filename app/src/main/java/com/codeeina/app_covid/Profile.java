package com.codeeina.app_covid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;

public class Profile extends AppCompatActivity {
    TextView nameTV;
    private RecyclerView mRecyclerView;
    private ButtonListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!UserData.isLogged())
            startActivity(new Intent(this, LoginScreen.class));

        setContentView(R.layout.activity_profile);
        nameTV = findViewById(R.id.Profile_nameTV);
        nameTV.setText(UserData.getName());

        mRecyclerView = findViewById(R.id.recylerProfile);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
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

    public void ShowList(View view) {
        mAdapter = new ButtonListAdapter(this, UserData.getFriends());
        mRecyclerView.setAdapter(mAdapter);
    }

    public void addFriend(View view) {
        UserData.addFriends("Matei");
    }
}