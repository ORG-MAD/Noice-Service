package com.example.noice_service;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
import com.google.common.collect.Range;

public class addService extends AppCompatActivity
{
    EditText title,description,price,totalTime,equ;
    Button submit,back;
    boolean[] selectedEquipment;
    ArrayList<Integer> eqList = new ArrayList<>();
    String[] eqArray = {"Mobil", "Caltex", "Valvoline™ Full Synthetic Grease"
            ,"Valvoline™ General Purpose Grease","Engine Tune",
            "Engine wash Liquid","Upholstry cleaner wash"};

    boolean[] selectedTime;
    ArrayList<Integer> timeList = new ArrayList<>();
    String[] timeArray = {"30 Minutes", "1 Hour", "1 Hour 30 Minutes"
            ,"2 Hours","3 Hours",
            "5 Hours","+5 Hours", "Full Day"};


    //Validation object
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_service);

        title=(EditText)findViewById(R.id.add_title);
        description=(EditText)findViewById(R.id.add_description);
        price=(EditText)findViewById(R.id.add_price);
        totalTime=(EditText)findViewById(R.id.add_totalTime);
        equ=(EditText) findViewById(R.id.add_equ);

        //Initialize validation styles
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        //Add validation for name
        awesomeValidation.addValidation(this, R.id.add_title, RegexTemplate.NOT_EMPTY, R.string.empty_title);
        awesomeValidation.addValidation(this, R.id.add_description, RegexTemplate.NOT_EMPTY, R.string.empty_description);
        awesomeValidation.addValidation(this, R.id.add_price, RegexTemplate.NOT_EMPTY, R.string.empty_price);
        awesomeValidation.addValidation(this, R.id.add_price, Range.closed(500, 15000), R.string.invalid_pricerange);
        awesomeValidation.addValidation(this, R.id.add_equ, RegexTemplate.NOT_EMPTY, R.string.empty_equ);
        awesomeValidation.addValidation(this, R.id.add_totalTime, RegexTemplate.NOT_EMPTY, R.string.empty_totalTime);
        awesomeValidation.addValidation(this, R.id.add_title, "[a-zA-Z]{0,10}$", R.string.invalid_titleA);

        submit=(Button)findViewById(R.id.add_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(awesomeValidation.validate()){
                    processinsert();
                }
            }
        });

        //time select
        totalTime= findViewById(R.id.add_totalTime);

        //initialize selected equipments
        selectedTime=new boolean[timeArray.length];
        totalTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //initialize alert dialog box
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        addService.this
                );
                //Set title
                builder.setTitle("Select estimated time for this Service");

                //set dialog non cancelable
                builder.setCancelable(false);

                builder.setMultiChoiceItems(timeArray, selectedTime, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i, boolean b) {
                        if (b){
                            //checkbox selected, Add position in equipment list
                            timeList.add(i);
                            //Sort equipment list
                            Collections.sort(timeList);
                        }
                        else {
                            //checkbox unselected, Remove position from equipment list
                            timeList.remove(i);
                        }
                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Initialize string builder
                        StringBuilder stringBuilder= new StringBuilder();
                        //loop
                        for(int j=0; j<timeList.size(); j++){
                            //Concat array value
                            stringBuilder.append(timeArray[timeList.get(j)]);
                            //check condition
                            if(j!= timeList.size()-1){
                                //when j value is not equal to equipment list size -1
                                stringBuilder.append(", ");
                            }
                        }
                        //set text on text view
                        totalTime.setText(stringBuilder.toString());

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
                            selectedTime[j]=false;
                            //clear equipment list
                            timeList.clear();
                            //clear text view value
                            totalTime.setText("");
                        }
                    }
                });
                //show dialog
                builder.show();
            }
        });


        //equipment select
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
                builder.setTitle("Select Resources and Equipments");

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
        map.put("totalTime",totalTime.getText().toString());
        FirebaseDatabase.getInstance().getReference().child("services").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        title.setText("");
                        description.setText("");
                        price.setText("");
                        totalTime.setText("");
                        equ.setText("");
                        Toast.makeText(getApplicationContext(),"Service Added Successfully",Toast.LENGTH_LONG).show();
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