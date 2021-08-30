package com.example.noice_service;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class AddServices extends AppCompatActivity {

    int SELECT_PHOTO=1;
    Uri uri;
    ImageView imageView;
    TextView tvDay;
    TextView textView;
    boolean[] selectedEquipment;
    ArrayList<Integer> eqList = new ArrayList<>();
    String[] eqArray = {"Mobil", "Caltex", "Valvoline™ Full Synthetic Grease"
            ,"Valvoline™ General Purpose Grease","Engine Tune",
            "Engine wash Liquid","Upholstry cleaner wash"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_services);

        textView = (TextView) findViewById(R.id.tv_heading);
        String text = "NOICE SERVICE";
        SpannableString ss = new SpannableString(text);
        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
        ss.setSpan(boldSpan, 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);

        Button Choose = findViewById(R.id.choose);
        imageView = findViewById(R.id.image);

        Choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,SELECT_PHOTO);
            }
        });

        //quipment select
        tvDay= findViewById(R.id.tv_day);

        //initialize selected equipments
        selectedEquipment=new boolean[eqArray.length];
        tvDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //initialize alert dialog box
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        AddServices.this
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
                        tvDay.setText(stringBuilder.toString());

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
                            tvDay.setText("");
                        }

                    }
                });
                //show dialog
                builder.show();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_PHOTO && resultCode==RESULT_OK && data !=null && data.getData() !=null){
            uri = data.getData();
            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}