package com.example.noice_service;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class J_CustomerRegistration extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseDatabase db;
    DatabaseReference userRef;

    //Initialize variables
    EditText et_Name;
    EditText et_password;
    EditText et_phoneNo;
    EditText et_email;
    Button btn_register;

    ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jcustomer_registration);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        userRef = db.getReference("User");

        et_Name = findViewById(R.id.et_Name);
        et_password = findViewById(R.id.et_password);
        et_email = findViewById(R.id.et_email);
        et_phoneNo = findViewById(R.id.et_phoneNo);
        btn_register = findViewById(R.id.btn_register);

        loadingBar = new ProgressDialog(this);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = et_email.getText().toString();
                String pass = et_password.getText().toString();

                RegisterUser(email, pass);
            }
        });
    }

    private void RegisterUser(String email, String pass) {
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Enter your emaill address", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(pass)){
            Toast.makeText(this, "Enter your password", Toast.LENGTH_SHORT).show();
        }
        else{
            loadingBar.setTitle("Registration");
            loadingBar.setMessage("Please wait a moment until registration is completed");
            loadingBar.show();

            mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
//                      Toast.makeText(J_CustomerRegistration.this, "Registration is successful", Toast.LENGTH_SHORT).show();
                        //Save to db
                        User user = new User();
                        user.setName(et_Name.getText().toString());
                        user.setEmail(et_email.getText().toString());
                        user.setPhone(et_phoneNo.getText().toString());

                        userRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(J_CustomerRegistration.this, "Registration successful", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(J_CustomerRegistration.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                   }
                    else{
                        Toast.makeText(J_CustomerRegistration.this, "Registration failed", Toast.LENGTH_SHORT).show();
                    }
                    loadingBar.dismiss();
                }
            });
        }
    }
}