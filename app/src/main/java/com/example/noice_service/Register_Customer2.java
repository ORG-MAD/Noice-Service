package com.example.noice_service;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Register_Customer2 extends AppCompatActivity {
    //Initialize variables
    TextView textView;
    TextView tv_Country;
    Dialog dialog;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_customer2);

        textView = (TextView) findViewById(R.id.tv_heading);
        String text = "NOICE SERVICE";
        SpannableString ss = new SpannableString(text);
        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
        ss.setSpan(boldSpan, 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);

        //Assign variables
        tv_Country = findViewById(R.id.tv_Country);

        //Initialize array List
        arrayList = new ArrayList<>();

        //Add values to array list
        arrayList.add("Afghanistan");
        arrayList.add("America");
        arrayList.add("Argentina");
        arrayList.add("Australia");
        arrayList.add("Bangladesh");
        arrayList.add("Belarus");
        arrayList.add("Canada");
        arrayList.add("Cuba");
        arrayList.add("Cyprus");
        arrayList.add("France");
        arrayList.add("Germany");
        arrayList.add("Hong Kong");
        arrayList.add("Italy");
        arrayList.add("Jamaica");
        arrayList.add("Japan");
        arrayList.add("Korea North");
        arrayList.add("Korea South");
        arrayList.add("Malaysia");
        arrayList.add("Maldives");
        arrayList.add("Nepal");
        arrayList.add("Oman");
        arrayList.add("Poland");
        arrayList.add("Spain");
        arrayList.add("Sri Lanka");
        arrayList.add("Thailand");
        arrayList.add("United Kingdom");
        arrayList.add("Vietnam");

        tv_Country.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //Initialize dialog
                dialog = new Dialog(Register_Customer2.this);

                //Set custom dialog
                dialog.setContentView(R.layout.dialog_searchable_spinner);

                //Set custom height and width
                dialog.getWindow().setLayout(650, 800);

                //Set transparent background
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                //Show dialog
                dialog.show();

                //Initialize and assign variables
                EditText editText = dialog.findViewById(R.id.et_search);
                ListView listView = dialog.findViewById(R.id.list);

                //Initialize array adapter
                ArrayAdapter<String> adapter = new ArrayAdapter<>(Register_Customer2.this, android.R.layout.simple_list_item_1,arrayList);

                //Set adapter
                listView.setAdapter(adapter);

                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        //Filter array list
                        adapter.getFilter().filter(charSequence);
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        //When item is selected from the list, set the selected item on text view
                        tv_Country.setText(adapter.getItem(i));

                        //Dismiss dialog
                        dialog.dismiss();
                    }
                });
            }
        });


    }
}