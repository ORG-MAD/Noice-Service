package com.example.noice_service;



import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class DAORequest {
    private DatabaseReference databaseReference;

    //Create database instance
    public DAORequest(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(ReqModel.class.getSimpleName());
    }

    //Create function
    public Task<Void> add(ReqModel req){
        return databaseReference.push().setValue(req);
    }

    //Read Function
    public Query get(String key){
        if(key==null){
            return databaseReference.orderByKey().limitToFirst(8);
        }
        return databaseReference.orderByKey().startAfter(key).limitToFirst(8);
    }

    //Update function
    public Task<Void> update(String key, HashMap<String, Object> hashMap){
        return databaseReference.child(key).updateChildren(hashMap);
    }

    //Delete function
    public Task<Void> remove(String key){
        return databaseReference.child(key).removeValue();
    }

}
