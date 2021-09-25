package com.example.noice_service;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class jLogin_Admin extends AppCompatActivity {
    Button btn_login;
    Button btn_register;
    Button btn_customer;
    EditText et_email, et_password;

    private FirebaseAuth mAuth;
    ProgressDialog loadingBar;

    TextView tv_no;
    int counter = 3;

    //Validation object
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jlogin_admin);

        btn_login = findViewById(R.id.btn_login);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        btn_register = findViewById(R.id.btn_register);
        mAuth = FirebaseAuth.getInstance();
        loadingBar = new ProgressDialog(this);

        tv_no = (TextView) findViewById(R.id.tv_no);
        tv_no.setVisibility(View.GONE);

        //Initialize validation styles
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        //Add validation for name
        awesomeValidation.addValidation(this, R.id.et_email, RegexTemplate.NOT_EMPTY, R.string.empty_email);
        awesomeValidation.addValidation(this, R.id.et_password, RegexTemplate.NOT_EMPTY, R.string.empty_password);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(awesomeValidation.validate()){
                    String email = et_email.getText().toString();
                    String password = et_password.getText().toString();
                    SignIn(email, password);
                }
            }
        });

    }

    private void SignIn(String email, String password) {
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Enter your emaill address", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Enter your password", Toast.LENGTH_SHORT).show();
        }
        else{
            loadingBar.setTitle("Login");
            loadingBar.setMessage("Please wait......");
            loadingBar.show();

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        loadingBar.dismiss();
                        FirebaseDatabase.getInstance().getReference("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        GlobVar_Customer.currentUser = snapshot.getValue(User.class);
                                        Intent home = new Intent(jLogin_Admin.this, Admin_Dashboard.class);
                                        startActivity(home);
                                        Toast.makeText(jLogin_Admin.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                        Intent home = new Intent(jLogin_Admin.this, Admin_Dashboard.class);
                        startActivity(home);
                        finish();
                    }
                    else {
                        Toast.makeText(jLogin_Admin.this, "Login failed!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            tv_no.setVisibility(View.VISIBLE);
            counter--;
            tv_no.setText(Integer.toString(counter));

            if(counter == 0){
                btn_login.setEnabled(false);
            }
        }
    }
}