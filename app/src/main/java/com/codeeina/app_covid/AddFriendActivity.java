package com.codeeina.app_covid;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

public class AddFriendActivity extends AppCompatActivity {
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String userID;
    FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    UserData loggedUser;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_friend_screen);

        Button subBtn = findViewById(R.id.submitButton);
        EditText nameField = findViewById(R.id.friendFullName);
        nameField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE) {
                    subBtn.performClick();
                    return true;
                }
                return false;
            }
        });
        subBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullNameString = nameField.getText().toString();
                if(user != null)
                    userID = user.getUid();
                DocumentReference docRef = fStore.collection("users").document(userID);
                docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        loggedUser = documentSnapshot.toObject(UserData.class);
                        if(loggedUser != null) {
                            System.out.println(loggedUser.toString());
                            loggedUser.addFriends(fullNameString);
                            docRef.set(loggedUser);
                            startActivity(new Intent(AddFriendActivity.this, Profile.class));
                            finish();
                        }
                    }
                });
            }
        });
    }
}
