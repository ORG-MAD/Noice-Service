package com.example.noice_service;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class DAOFeedbacks {
    private DatabaseReference databaseReference2;

    public DAOFeedbacks() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference2 = db.getReference(Feedbacks.class.getSimpleName());
    }

    public Task<Void> add(Feedbacks feedback){
        return databaseReference2.push().setValue(feedback);
    }

    public Query get(){
        return databaseReference2.orderByKey();
    }
}
