package com.example.noice_service;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class jFeedbackForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jfeedback_form);

        final EditText et_type = findViewById(R.id.et_type);
        final EditText et_subject = findViewById(R.id.et_subject);
        final EditText et_message = findViewById(R.id.et_message);
        final Button  btn_submitFeedback = findViewById(R.id.btn_submitFeedback);

        DAOFeedbacks dao = new DAOFeedbacks();

        btn_submitFeedback.setOnClickListener(v->{
            Feedbacks feedback = new Feedbacks(et_type.getText().toString(), et_subject.getText().toString(), et_message.getText().toString());
            dao.add(feedback).addOnSuccessListener(suc -> {
                Toast.makeText(this, "Thank you for your valued feedback!", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(er -> {
                Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
            });
        });

    }
}
