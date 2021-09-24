package com.example.noice_service;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import android.app.AlertDialog;
import android.content.DialogInterface;
public class addService extends AppCompatActivity
{
   EditText title,description,price,imgurl,equ;
   Button submit,back;
    boolean[] selectedEquipment;
    ArrayList<Integer> eqList = new ArrayList<>();
    String[] eqArray = {"Mobil", "Caltex", "Valvoline™ Full Synthetic Grease"
            ,"Valvoline™ General Purpose Grease","Engine Tune",
            "Engine wash Liquid","Upholstry cleaner wash"};


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_service);

        title=(EditText)findViewById(R.id.add_title);
        description=(EditText)findViewById(R.id.add_description);
        price=(EditText)findViewById(R.id.add_price);
        imgurl=(EditText)findViewById(R.id.add_img);
        equ=(EditText) findViewById(R.id.add_equ);


        back=(Button)findViewById(R.id.add_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });

        submit=(Button)findViewById(R.id.add_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processinsert();
            }
        });
        //quipment select
        equ= findViewById(R.id.add_equ);

        //initialize selected equipments
        selectedEquipment=new boolean[eqArray.length];
        equ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //initialize alert dialog box
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        addService.this
                );
                //Set title
                builder.setTitle("Select Equipments");

                //set dialog non cancelable
                builder.setCancelable(false);

                builder.setMultiChoiceItems(eqArray, selectedEquipment, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i, boolean b) {
                        if (b){
                            //checkbox selected, Add position in equipment list
                            eqList.add(i);
                            //Sort equipment list
                            Collections.sort(eqList);
                        }
                        else {
                            //checkbox unselected, Remove position from equipment list
                            eqList.remove(i);
                        }
                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Initialize string builder
                        StringBuilder stringBuilder= new StringBuilder();
                        //loop
                        for(int j=0; j<eqList.size(); j++){
                            //Concat array value
                            stringBuilder.append(eqArray[eqList.get(j)]);
                            //check condition
                            if(j!= eqList.size()-1){
                                //when j value is not equal to equipment list size -1
                                stringBuilder.append(", ");
                            }
                        }
                        //set text on text view
                        equ.setText(stringBuilder.toString());

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //dismiss dialog
                        dialogInterface.dismiss();

                    }
                });
                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //use for loop
                        for(int j=0; j<selectedEquipment.length; j++ ){
                            //Remove all selection
                            selectedEquipment[j]=false;
                            //clear equipment list
                            eqList.clear();
                            //clear text view value
                            equ.setText("");
                        }

                    }
                });
                //show dialog
                builder.show();

            }
        });
    }

    private void processinsert()
    {
        Map<String,Object> map=new HashMap<>();
        map.put("title",title.getText().toString());
        map.put("description",description.getText().toString());
        map.put("price",price.getText().toString());
        map.put("equ",equ.getText().toString());
        map.put("imgurl",imgurl.getText().toString());
        FirebaseDatabase.getInstance().getReference().child("services").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        title.setText("");
                        description.setText("");
                        price.setText("");
                        imgurl.setText("");
                        equ.setText("");
                        Toast.makeText(getApplicationContext(),"Inserted Successfully",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Toast.makeText(getApplicationContext(),"Could not insert",Toast.LENGTH_LONG).show();
                    }
                });

    }
}