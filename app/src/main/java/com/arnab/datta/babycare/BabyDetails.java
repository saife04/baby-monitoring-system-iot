package com.arnab.datta.babycare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;
import java.util.Calendar;


public class BabyDetails extends AppCompatActivity  {

    private static final String TAG = "BabyDetails";

    private TextView mDisplayDate;
    private EditText Height ;
    private EditText Weight ;
    private EditText  pBirth;
    private Button btn ;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private DatabaseReference databaseReference ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_details);


        databaseReference = FirebaseDatabase.getInstance().getReference();
        Height = findViewById(R.id.editTextHeight);
        Weight = findViewById(R.id.editTextWeight);
        pBirth = findViewById(R.id.editTextPlace);
        // Blood Group
        Spinner mySpinner = findViewById(R.id.spinnerBG);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String >(BabyDetails.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.bg));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        //Next Button
        Button btn = findViewById(R.id.nextbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityHome();
                savedetails();
            }
        });

        // Date of Birth
        mDisplayDate = findViewById(R.id.textViewDob);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        BabyDetails.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date =  day + "/" + month  + "/" + year;
                mDisplayDate.setText(date);
            }
        };



    }
    public void savedetails ()
    {
        String datebirth = mDisplayDate.getText().toString().trim();
        String placebirth = pBirth.getText().toString().trim();
        int height = Integer.parseInt(Height.getText().toString().trim()) ;
        int weight = Integer.parseInt(Weight.getText().toString().trim()) ;


        DatabaseReference postsBaby = databaseReference.child("baby details");

        DatabaseReference newPostBaby = postsBaby.push();
        Intent i=getIntent();
        newPostBaby.setValue(new details(datebirth,placebirth,height,weight,i.getStringExtra("username")));
    }
    public  void openActivityLogin(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
    public  void openActivityHome(){
        Intent inentt=getIntent();

        Intent intent = new Intent(this, Home.class);
        intent.putExtra("userName",inentt.getStringExtra("username"));
        intent.putExtra("babyName",inentt.getStringExtra("babyname"));
        startActivity(intent);

    }


}
