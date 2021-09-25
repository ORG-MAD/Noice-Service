package com.example.noice_service;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


public class Resource_Calculation extends FirebaseRecyclerAdapter<SVCmodel,Resource_Calculation.myviewholder>
{
    public Resource_Calculation(@NonNull FirebaseRecyclerOptions<SVCmodel> options)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myviewholder holder, @SuppressLint("RecyclerView") final int position, @NonNull final SVCmodel SVCmodel)
    {
        holder.title.setText(SVCmodel.getTitle());
        holder.price.setText(SVCmodel.getPrice());

    } // End of OnBindViewMethod

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_resource_calculation,parent,false);
        return new myviewholder(view);
    }


    class myviewholder extends RecyclerView.ViewHolder
    {
        //        CircleImageView img;
        ImageView edit,delete;
        TextView title,description,price,equ,img;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.titletext);
            price=(TextView)itemView.findViewById(R.id.pricetext);
        }
    }
}