package com.codeeina.app_covid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.LinkedList;

public class Profile extends AppCompatActivity {
    TextView nameTV;
    private RecyclerView mRecyclerView;
    private ButtonListAdapter mAdapter;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    String userID;
    UserData loggedUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        nameTV = findViewById(R.id.Profile_nameTV);
        if(user != null)
            userID = user.getUid();
        DocumentReference docRef = fStore.collection("users").document(userID);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                loggedUser = documentSnapshot.toObject(UserData.class);
                if(loggedUser != null) {
                    nameTV.setText(loggedUser.getName());
                }
            }
        });
        mRecyclerView = findViewById(R.id.recylerProfile);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void Log_out(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this, LoginScreen.class));
        finish();
    }

    public void ShowList(View view) {
        mAdapter = new ButtonListAdapter(this, (ArrayList<String>) loggedUser.getFriends());
        mRecyclerView.setAdapter(mAdapter);
    }

    public void addFriend(View view) {
        loggedUser.addFriends("Matei");
    }
}