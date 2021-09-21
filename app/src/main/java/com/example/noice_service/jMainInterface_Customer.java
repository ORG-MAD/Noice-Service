package com.example.noice_service;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class jMainInterface_Customer extends AppCompatActivity {

    TextView tv_Name;
    Button btn_myaccount, btn_logout;
    FirebaseAuth mAuth;

    private CircleImageView profile_image;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jmain_interface_customer);

        tv_Name = findViewById(R.id.tv_Name);
        btn_myaccount = findViewById(R.id.btn_myaccount);
        tv_Name.setText(GlobVar_Customer.currentUser.getName());
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("User");
        btn_logout = findViewById(R.id.btn_logout);
        profile_image = findViewById(R.id.profilepic);


        btn_myaccount.setOnClickListener((v)->{
            Intent profile = new Intent(jMainInterface_Customer.this, jEditProfile_Customer.class);
            startActivity(profile);
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                SignOutUser();
            }
        });

        getUserInfo();
    }

    private void SignOutUser() {
        Intent home = new Intent(jMainInterface_Customer.this, jLogin_Customer.class);
        home.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(home);
        finish();
    }

    private void getUserInfo() {
        databaseReference.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists() && snapshot.getChildrenCount() > 0){
                    String name = snapshot.child("name").getValue().toString();
                    tv_Name.setText(name);

                    if(snapshot.hasChild("image")){
                        String image = snapshot.child("image").getValue().toString();
                        Picasso.get().load(image).into(profile_image);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}