package com.arnab.datta.babycare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyProfile extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        Intent intent=getIntent();
        String myName=intent.getStringExtra("userName");
        String babyname=intent.getStringExtra("Babyname");
        TextView textview1 = findViewById(R.id.tvuserrr);
        TextView textView2=findViewById(R.id.tvbabyname);
        textView2.setText(babyname);
        textview1.setText(myName);



        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference usersdRef = rootRef.child("baby details");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> array=new ArrayList();
                List<details> allUsers=new ArrayList();
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String name = ds.child("userName").getValue(String.class);
                    int weight = ds.child("weight").getValue(int.class);
                    String birthday = ds.child("dadtebirth").getValue(String.class);
                    int height=ds.child("height").getValue(int.class);
                    String placebirth=ds.child("placebirth").getValue(String.class);
                    allUsers.add(new details( birthday,  placebirth,  height,  weight, name));

                }
                Intent intent=getIntent();
                String myName=intent.getStringExtra("userName");
                for(details u:allUsers){
                    if(u.getUserName().equals(myName)){

                        details muUser=u;
                        TextView textvieww = findViewById(R.id.textView4);
                        textvieww.setText(muUser.getDadtebirth());
                        TextView textviee = findViewById(R.id.textView6);
                        textviee.setText(muUser.getPlacebirth());
                        TextView textvieww2 = findViewById(R.id.textView8);
                        textvieww2.setText(Integer.toString(muUser.getHeight()));
                        TextView textvieww3 = findViewById(R.id.textView12);
                        textvieww3.setText(Integer.toString(muUser.getWeight()));
                        break;
                    }
                }



            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
    } ;
    usersdRef.addListenerForSingleValueEvent(eventListener);



}}
