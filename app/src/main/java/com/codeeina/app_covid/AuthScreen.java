package com.codeeina.app_covid;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AuthScreen extends AppCompatActivity {

    final static public String TAG = "My activity";
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    public EditText mFirstName, mLastName, mEmail, mPassword;
    public Button mSignUp;
    String userID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_screen);

        mFirstName = findViewById(R.id.firstNameTV);
        mLastName = findViewById(R.id.lastNameTV);
        mEmail = findViewById(R.id.emailAuthTV);
        mPassword = findViewById(R.id.passAuthTV);
        mSignUp = findViewById(R.id.signBtn);

        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String firstName = mFirstName.getText().toString();
                String lastName = mLastName.getText().toString();

                fAuth = FirebaseAuth.getInstance();
                fStore = FirebaseFirestore.getInstance();

                if(TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is Required");
                    return;
                }
                if(TextUtils.isEmpty(password)) {
                    mPassword.setError("Password is Required");
                    return;
                }
                if(password.length() < 6) {
                    mPassword.setError("Password must be at least 6 characters");
                    return;
                }
                if(TextUtils.isEmpty(firstName)) {
                    mFirstName.setError("First Name is Required");
                    return;
                }
                if(TextUtils.isEmpty(lastName)) {
                    mLastName.setError("Last Name is Required");
                    return;
                }
                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            userID = fAuth.getCurrentUser().getUid();
                            System.out.println(userID);
                            DocumentReference documentReference = fStore.collection("users")
                                    .document(userID);
                            UserData user = new UserData(firstName, lastName, email, true);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "User profile created for " + userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "Failed: " + e.toString());
                                }
                            });
                            startActivity(new Intent(AuthScreen.this, Profile.class));
                        } else {
                            System.out.println("Task Failed! " + task.getException().getMessage());
                        }
                    }
                });
            }
        });
    }
}
