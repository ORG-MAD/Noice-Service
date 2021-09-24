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


public class myadapter extends FirebaseRecyclerAdapter<SVCmodel,myadapter.myviewholder>
{
    public myadapter(@NonNull FirebaseRecyclerOptions<SVCmodel> options)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myviewholder holder, @SuppressLint("RecyclerView") final int position, @NonNull final SVCmodel SVCmodel)
    {
        holder.title.setText(SVCmodel.getTitle());
        holder.description.setText(SVCmodel.getDescription());
        holder.price.setText(SVCmodel.getPrice());
        holder.equ.setText(SVCmodel.getEqu());
        holder.img.setText(SVCmodel.getTotalTime());
//       Glide.with(holder.img.getContext()).load(SVCmodel.getImgurl()).into(holder.img);

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus=DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.dialogcontent))
                        .setExpanded(true,1100)
                        .create();

                View myview=dialogPlus.getHolderView();
                final EditText totalTime=myview.findViewById(R.id.uimgurl);
                final EditText title=myview.findViewById(R.id.utitle);
                final EditText description=myview.findViewById(R.id.udescription);
                final EditText price=myview.findViewById(R.id.uprice);
                final EditText equ =myview.findViewById(R.id.uequ);
                Button submit=myview.findViewById(R.id.usubmit);

                totalTime.setText(SVCmodel.getTotalTime());
                title.setText(SVCmodel.getTitle());
                description.setText(SVCmodel.getDescription());
                price.setText(SVCmodel.getPrice());
                equ.setText(SVCmodel.getEqu());

                dialogPlus.show();

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map=new HashMap<>();
                        map.put("totalTime",totalTime.getText().toString());
                        map.put("title",title.getText().toString());
                        map.put("description",description.getText().toString());
                        map.put("price",price.getText().toString());
                        map.put("equ",equ.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("services")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });


            }
        });


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.img.getContext());
                builder.setTitle("Delete Service");
                builder.setMessage("Are you Sure to want delete this service?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("services")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.show();
            }
        });

    } // End of OnBindViewMethod

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
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
            img=(TextView) itemView.findViewById(R.id.img1);
            title=(TextView)itemView.findViewById(R.id.titletext);
            description=(TextView)itemView.findViewById(R.id.descriptiontext);
            price=(TextView)itemView.findViewById(R.id.pricetext);
            equ=(TextView)itemView.findViewById(R.id.equtext);

            edit=(ImageView)itemView.findViewById(R.id.editicon);
            delete=(ImageView)itemView.findViewById(R.id.deleteicon);
        }
    }
}