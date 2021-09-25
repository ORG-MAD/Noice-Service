package com.example.noice_service;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class J_CustomerRegistration extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseDatabase db;
    DatabaseReference userRef;

    //Initialize variables
    EditText et_Name;
    EditText et_password;
    EditText et_phoneNo;
    EditText et_email;
    EditText et_date;
    Button btn_register;
    TextView tv_regdate;
    TextView tv_Country;
    ArrayList<String> arrayList;
    Dialog dialog;

    ProgressDialog loadingBar;
    DatePickerDialog.OnDateSetListener setListener;

    //Validation object
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jcustomer_registration);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        userRef = db.getReference("User");

        et_Name = findViewById(R.id.et_Name);
        et_password = findViewById(R.id.et_password);
        et_email = findViewById(R.id.et_email);
        et_phoneNo = findViewById(R.id.et_phoneNo);
        et_date = findViewById(R.id.et_date);
        tv_regdate = findViewById(R.id.tv_regdate);
        btn_register = findViewById(R.id.btn_register);

        loadingBar = new ProgressDialog(this);

        //Initialize validation styles
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        //Add validation for name
        awesomeValidation.addValidation(this, R.id.et_Name, RegexTemplate.NOT_EMPTY, R.string.invalid_name);
        awesomeValidation.addValidation(this, R.id.et_phoneNo, "[0][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]", R.string.invalid_mobile);
        awesomeValidation.addValidation(this, R.id.et_email, Patterns.EMAIL_ADDRESS, R.string.invalid_email);
        awesomeValidation.addValidation(this, R.id.et_date, RegexTemplate.NOT_EMPTY, R.string.invalid_bday);
        awesomeValidation.addValidation(this, R.id.et_password, "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$", R.string.invalid_password);
        awesomeValidation.addValidation(this, R.id.tv_Country, RegexTemplate.NOT_EMPTY, R.string.invalid_country);

        et_password = findViewById(R.id.et_password);
        btn_register = findViewById(R.id.btn_register);

        long date = System.currentTimeMillis();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = sdf.format(date);
        tv_regdate.setText(dateString);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(awesomeValidation.validate()){

                    String email = et_email.getText().toString();
                    String pass = et_password.getText().toString();

                    RegisterUser(email, pass);
                }
            }
        });

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        et_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(J_CustomerRegistration.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        String sDate = day + "/" + month + "/" + year;
                        et_date.setText(sDate);
                    }
                },year,month,day);
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

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
                dialog = new Dialog(J_CustomerRegistration.this);

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
                ArrayAdapter<String> adapter = new ArrayAdapter<>(J_CustomerRegistration.this, android.R.layout.simple_list_item_1,arrayList);

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

    private void RegisterUser(String email, String pass) {
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Enter your emaill address", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(pass)){
            Toast.makeText(this, "Enter your password", Toast.LENGTH_SHORT).show();
        }
        else{
            loadingBar.setTitle("Registration");
            loadingBar.setMessage("Please wait a moment until registration is completed");
            loadingBar.show();

            mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
//                      Toast.makeText(J_CustomerRegistration.this, "Registration is successful", Toast.LENGTH_SHORT).show();
                        //Save to db
                        User user = new User();
                        user.setName(et_Name.getText().toString());
                        user.setEmail(et_email.getText().toString());
                        user.setPhone(et_phoneNo.getText().toString());
                        user.setDOB(et_date.getText().toString());
                        user.setCountry(tv_Country.getText().toString());
                        user.setRegDate(tv_regdate.getText().toString());

                        userRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(J_CustomerRegistration.this, "Registration successful", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(J_CustomerRegistration.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    else{
                        Toast.makeText(J_CustomerRegistration.this, "Registration failed", Toast.LENGTH_SHORT).show();
                    }
                    loadingBar.dismiss();
                }
            });
        }
    }
}
