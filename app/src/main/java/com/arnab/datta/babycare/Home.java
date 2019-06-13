package com.arnab.datta.babycare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home extends AppCompatActivity {

    private Button bt1,bt2,bt3,bt4,bt5;
    private FirebaseAuth FirebaseAuth ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent intent=getIntent();
        String x=intent.getStringExtra("userName");
        FirebaseAuth = com.google.firebase.auth.FirebaseAuth.getInstance();
      /*  if (FirebaseAuth.getCurrentUser() == null){
            finish();
            openActivityLogin();
        }*/
      //  FirebaseUser User = FirebaseAuth.getCurrentUser();

        bt1=findViewById(R.id.buttonmp);
        bt2=findViewById(R.id.buttonvd);
        bt3=findViewById(R.id.buttonhc);
        bt4=findViewById(R.id.buttonlo);
        bt5=findViewById(R.id.buttondet);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=getIntent();
                openActivityMyProfile(intent.getStringExtra("userName"),intent.getStringExtra("babyName"));
            }

        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityVaccineDetails();
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityHealthCare();
            }
        });
        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityMenu();
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.signOut();
                finish();
                openActivityLogin();

            }
        });



    }
    public  void openActivityMyProfile(String Username , String babyname){
        Intent intent = new Intent(this, MyProfile.class);
        intent.putExtra("userName",Username);
        intent.putExtra("Babyname",babyname);
        startActivity(intent);
    }

    public  void openActivityVaccineDetails(){
        Intent intent = new Intent(this, Vaccine.class);
        startActivity(intent);
    }

    public  void openActivityHealthCare(){
        Intent intent = new Intent(this, HealthCare.class);
        startActivity(intent);
    }
    public  void openActivityLogin(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
    public void openActivityMenu (){
        Intent intent = new Intent(this,Menu.class);
        startActivity(intent);
    }
}
