package com.arnab.datta.babycare;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity  {
    private TextView t1;
    private Button btl;
    private EditText tu,tp;
    private ProgressDialog ProgressDialog ;
    private FirebaseAuth FirebaseAuth ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        FirebaseAuth = com.google.firebase.auth.FirebaseAuth.getInstance();
     /*   if (FirebaseAuth.getCurrentUser() != null){
            finish();
           // openActivityHome(FirebaseAuth.getCurrentUser());
       }*/
        ProgressDialog = new ProgressDialog(this);
        tu = findViewById(R.id.editTextUser);
        tp = findViewById(R.id.editTextPass);

        t1 = findViewById(R.id.textViewSignUp);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivitySignUp();
            }
        });

        btl = findViewById(R.id.buttonLogin);
        btl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userlogin();

            }
        });



    }
    public  void openActivitySignUp(){
        Intent intent = new Intent(this, Signup.class);
        startActivity(intent);
    }

    public  void openActivityHome(User user){
        String str = "You have logged in successfully";
        Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, Home.class);
        intent.putExtra("userName",user.getUsername());
        intent.putExtra("babyName",user.getBabyname());
        startActivity(intent);

    }
    private void userlogin () {
        String Username = tu.getText().toString().trim();
        String password = tp.getText().toString().trim();
        if (TextUtils.isEmpty(Username)) {
            Toast.makeText(this, "please enter email", Toast.LENGTH_SHORT).show();
            return;
        }


        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "please enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference usersdRef = rootRef.child("baby");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> array=new ArrayList();
                List<User> allUsers=new ArrayList();
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String email = ds.child("email").getValue(String.class);
                    String babyName = ds.child("babyname").getValue(String.class);
                    String pasword=ds.child("password").getValue(String.class);
                    String userName=ds.child("username").getValue(String.class);
                    User user=new User();
                    user.setEmail(email);
                    user.setBabyname(babyName);
                    user.setPassword(pasword);
                    user.setUsername(userName);

                    allUsers.add(user);
                    array.add(email);

                }
                for(User u:allUsers){
                    if((u.getUsername().equals(tu.getText().toString().trim()))&&(u.getPassword().equals(tp.getText().toString().trim()))){
                        ProgressDialog.dismiss();

                        User muUser=u;
                        finish();
                        openActivityHome(u);
                        break;
                    }
                }


               /* if (task.isSuccessful()) {
                    finish();
                    openActivityHome();


                } else {
                    Toast.makeText(Login.this, "login failed please try again", Toast.LENGTH_SHORT).show();
                }*/
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
       usersdRef.addListenerForSingleValueEvent(eventListener);





    }

   /* @Override
    public void onClick(View v) {
        if (v == btl) {
            userlogin();
        }
        if (v == t1)
            finish();
            openActivitySignUp();
    }*/
}
