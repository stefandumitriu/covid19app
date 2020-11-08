package com.codeeina.app_covid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    FirebaseUser user;
    FirebaseFirestore fStore;
    String userID;
    UserData loggedUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        user = FirebaseAuth.getInstance().getCurrentUser();
        fStore = FirebaseFirestore.getInstance();
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

        Button btnNewFriend = findViewById(R.id.buttonNewFriend);
        btnNewFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this, AddFriendActivity.class));
                fStore = FirebaseFirestore.getInstance();
            }
        });

        Button logoutBtn = findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loggedUser.setLogged(false);
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Profile.this, LoginScreen.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void ShowList(View view) {
        mAdapter = new ButtonListAdapter(this, (ArrayList<String>) loggedUser.getFriends());
        mRecyclerView.setAdapter(mAdapter);
    }
}