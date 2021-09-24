package com.example.noice_service;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
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

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import de.hdodenhof.circleimageview.CircleImageView;

public class jEditProfile_Customer extends AppCompatActivity {

    private CircleImageView profile_image;
    private Button btn_close, btn_save, btn_reset;
    private TextView changepic, tv_regdate, tv_days;
    private EditText et_name, et_phone, et_date;
    private TextView tv_Country;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    Date date1;
    private Uri imageUri;
    private String myUri = "";
    private StorageTask uploadTask;
    private StorageReference storageProfilePicsRef;

    ArrayList<String> arrayList;
    Dialog dialog;

    ProgressDialog loadingBar;
    DatePickerDialog.OnDateSetListener setListener;

    String userId;
    FirebaseUser user;

    //Validation object
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jedit_profile_customer);

        //Init
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("User");
        storageProfilePicsRef = FirebaseStorage.getInstance().getReference().child("Profile Pic");

        profile_image = findViewById(R.id.profile_image);
        btn_close = findViewById(R.id.btn_close);
        btn_save = findViewById(R.id.btn_save);
        btn_reset = findViewById(R.id.btn_reset);
        et_name = findViewById(R.id.et_name);
        et_date = findViewById(R.id.et_date);
        et_phone = findViewById(R.id.et_phone);
        tv_Country = findViewById(R.id.tv_Country);
        tv_regdate =findViewById(R.id.tv_regdate);
        tv_days = findViewById(R.id.tv_days);
        changepic = findViewById(R.id.changepic);

        userId = mAuth.getCurrentUser().getUid();
        user = mAuth.getCurrentUser();

        //Initialize validation styles
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        //Add validation for name
        awesomeValidation.addValidation(this, R.id.et_Name, RegexTemplate.NOT_EMPTY, R.string.invalid_name);
        awesomeValidation.addValidation(this, R.id.et_phoneNo, "[0][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]", R.string.invalid_mobile);
        awesomeValidation.addValidation(this, R.id.et_email, Patterns.EMAIL_ADDRESS, R.string.invalid_email);
        awesomeValidation.addValidation(this, R.id.et_date, RegexTemplate.NOT_EMPTY, R.string.invalid_bday);
        awesomeValidation.addValidation(this, R.id.et_password, "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$", R.string.invalid_password);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        et_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(jEditProfile_Customer.this, new DatePickerDialog.OnDateSetListener() {
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
                dialog = new Dialog(jEditProfile_Customer.this);

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
                ArrayAdapter<String> adapter = new ArrayAdapter<>(jEditProfile_Customer.this, android.R.layout.simple_list_item_1,arrayList);

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

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(jEditProfile_Customer.this, jMainInterface_Customer.class));
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(awesomeValidation.validate()){
                    ValidateAndSave();
                }
            }
        });

        changepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CropImage.activity().setAspectRatio(1, 1).start(jEditProfile_Customer.this);
            }
        });

        btn_reset.setOnClickListener((v) -> {
            final EditText resetPassword = new EditText(v.getContext());

            final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
            passwordResetDialog.setTitle("Reset Password?");
            passwordResetDialog.setMessage("Enter new password > 6 characters long");
            passwordResetDialog.setView(resetPassword);

            passwordResetDialog.setPositiveButton("Yes", (dialog, which) -> {
                //extract the email and send reset link
                String newPassword = resetPassword.getText().toString();
                user.updatePassword(newPassword).addOnSuccessListener(new OnSuccessListener<Void>() {
                    public void onSuccess(Void aVoid){
                        Toast.makeText(jEditProfile_Customer.this, "Password reset succesful", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(jEditProfile_Customer.this, "Password reset failed", Toast.LENGTH_SHORT).show();
                    }
                });
            });
            passwordResetDialog.setNegativeButton("No", (dialog, which) -> {
                //close
            });
            passwordResetDialog.create().show();
        });

        getUserInfo();
    }

    private void ValidateAndSave() {
        if(TextUtils.isEmpty(et_name.getText().toString())){
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(et_phone.getText().toString())){
            Toast.makeText(this, "Please enter your mobile number", Toast.LENGTH_SHORT).show();
        }else{
            HashMap<String, Object> userMap = new HashMap<>();
            userMap.put("name", et_name.getText().toString());
            userMap.put("phone", et_phone.getText().toString());
            userMap.put("dob", et_date.getText().toString());
            userMap.put("country", tv_Country.getText().toString());



            databaseReference.child(mAuth.getCurrentUser().getUid()).updateChildren(userMap);

            uploadProfileImage();
        }
    }

    private void getUserInfo() {
        databaseReference.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists() && snapshot.getChildrenCount() > 0){
                    String name = snapshot.child("name").getValue().toString();
                    String phone = snapshot.child("phone").getValue().toString();
                    String dob = snapshot.child("dob").getValue().toString();
                    String country = snapshot.child("country").getValue().toString();
                    String regDate = snapshot.child("regDate").getValue().toString();

                    et_name.setText(name);
                    et_phone.setText(phone);
                    et_date.setText(dob);
                    tv_Country.setText(country);
                    tv_regdate.setText(regDate);
//
//                    SimpleDateFormat format = new SimpleDateFormat("dd, MM, yyyy");
//                    try {
//                        date1 = format.parse(regDate);
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
                    

//                    long msDiff = Calendar.getInstance().getTimeInMillis() - regDate.getTimeInMillis();
//                    long daysDiff = TimeUnit.MILLISECONDS.toDays(msDiff);
//                    tv_days.setText(daysDiff);


                    try {
                        String CurrentDate= regDate;
                        String FinalDate= "26/09/2019";
                        Date date1;
                        Date date2;
                        SimpleDateFormat dates = new SimpleDateFormat("dd/MM/yyyy");
                        date1 = dates.parse(CurrentDate);
                        date2 = dates.parse(FinalDate);
                        long difference = Math.abs(date1.getTime() - date2.getTime());
                        long differenceDates = difference / (24 * 60 * 60 * 1000);
                        String dayDifference = Long.toString(differenceDates);
                        tv_days.setText(dayDifference + " days");
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }

                    if(snapshot.hasChild("image")){
                        String image = snapshot.child("image").getValue().toString();
                        Picasso.get().load(image).into(profile_image);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE &&  resultCode == RESULT_OK && data != null){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            imageUri = result.getUri();

            profile_image.setImageURI(imageUri);
        }
        else{
            Toast.makeText(this, "Error! Please try again", Toast.LENGTH_SHORT).show();
        }
    }

    private void uploadProfileImage() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Set your profile");
        progressDialog.setMessage("The image is being uploaded");
        progressDialog.show();

        if(imageUri != null){
            final StorageReference fileRef = storageProfilePicsRef.child(mAuth.getCurrentUser().getUid() + ".jpg");

            uploadTask = fileRef.putFile(imageUri);

            uploadTask.continueWithTask(new Continuation() {
                @Override
                public Object then(@NonNull Task task) throws Exception {
                    if(!task.isSuccessful()){
                        throw task.getException();
                    }
                    return fileRef.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if(task.isSuccessful()){
                        Uri downloadUri = task.getResult();
                        myUri = downloadUri.toString();

                        HashMap<String, Object> userMap = new HashMap<>();
                        userMap.put("image", myUri);

                        databaseReference.child(mAuth.getCurrentUser().getUid()).updateChildren(userMap);

                        progressDialog.dismiss();
                    }
                }
            });
        }
        else{
            progressDialog.dismiss();
            Toast.makeText(this, "Image not selected", Toast.LENGTH_SHORT).show();
        }
    }
}