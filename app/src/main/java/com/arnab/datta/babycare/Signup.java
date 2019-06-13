package com.arnab.datta.babycare;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Signup extends AppCompatActivity implements View.OnClickListener {

    private Button btn;
    private EditText edittextbabyname;
    private EditText edittextmothername;
    private EditText edittextfathername;
    private EditText edittextuusername;
    private EditText editpassword;
    private EditText editemailid;
    private EditText editmobilenumber;

    //TextView textviewbabyname ;
    private FirebaseAuth FirebaseAuth;

    private ProgressDialog ProgressDialog;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        FirebaseAuth = com.google.firebase.auth.FirebaseAuth.getInstance();
        ProgressDialog = new ProgressDialog(this);
        edittextbabyname = findViewById(R.id.editTextBaby);
        edittextmothername = findViewById(R.id.editTextMother);
        edittextfathername = findViewById(R.id.editTextFather);
        edittextuusername = findViewById(R.id.editTextUsername);
        editpassword = findViewById(R.id.editTextPassword);
        editemailid = findViewById(R.id.editTextEmail);
        editmobilenumber = findViewById(R.id.editTextMob);
        //  textviewbabyname = findViewById(R.id.tvbabyname);

        FirebaseUser user = FirebaseAuth.getCurrentUser();

        btn = findViewById(R.id.sgnextbutton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registeruser();

            }
        });


    }


    public void openActivitySignUp() {
        String babyname = edittextbabyname.getText().toString().trim();
        String username = edittextuusername.getText().toString().trim();
        Intent intent = new Intent(this, BabyDetails.class);
        intent.putExtra("babyname", babyname);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    private void registeruser() {


        String email = editemailid.getText().toString().trim();
        String password = editpassword.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "please enter email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editemailid.setError("please enter a valid email");
            editemailid.requestFocus();
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
                List<String> array = new ArrayList();

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String userName = ds.child("username").getValue(String.class);
                    //String email = editemailid.getText().toString().trim();
                    array.add(userName);
                }

                ProgressDialog.dismiss();
                String username = edittextuusername.getText().toString().trim();
                boolean a=false;
                for (String name : array) {
                    if (name.equals(username)) {
                        Toast.makeText(Signup.this, "login failed please try again", Toast.LENGTH_SHORT).show();
                        finish();
                        openActivity();
                        a=true;
                        break;
                    }

                }
                if(!a){
                test();
                adduser();
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
            // Toast.makeText(this.Signup, "please enter email", Toast.LENGTH_SHORT).show();
        };
        usersdRef.addListenerForSingleValueEvent(eventListener);
        ProgressDialog.setMessage("Registerring User  ");
        ProgressDialog.show();


    }

    private void test(){
        String email = editemailid.getText().toString().trim();
        String password = editpassword.getText().toString().trim();
        FirebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            ProgressDialog.hide();
                            Toast.makeText(Signup.this, "succeed", Toast.LENGTH_SHORT).show();
                          /*  btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    openActivitySignUp();
                                }
                            });*/

                            finish();
                            openActivitySignUp();
                        } else {
                            Toast.makeText(Signup.this, "registred failed please try again", Toast.LENGTH_SHORT).show();
                        }
                    }

                });

    }
    private void adduser() {
        String babyname = edittextbabyname.getText().toString().trim();
        String mothername = edittextmothername.getText().toString().trim();
        String fathername = edittextfathername.getText().toString().trim();
        String username = edittextuusername.getText().toString().trim();
        String password = editpassword.getText().toString().trim();
        String email = editemailid.getText().toString().trim();
        String mobilenumber = editmobilenumber.getText().toString().trim();


        DatabaseReference postsBaby = databaseReference.child("baby");

        DatabaseReference newPostBaby = postsBaby.push();
        User myUser = new User(babyname, mothername, fathername, username, password, email, mobilenumber);
        newPostBaby.setValue(myUser);


// We can also chain the two calls together
        // postsBaby.push().setValue(new User("Chayma", "NOUR","saif","saif04","jhjsdhj","ggdg@ggd.com","255665"));


    }
    public  void openActivity(){

        Intent intent = new Intent(this, Signup.class);
        startActivity(intent);

    }
   /* @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);

                String name=user.getBabyname();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());

            }
        });
    }*/


    @Override
    public void onClick(View v) {
        if (v == btn) {
            registeruser();
        }
    }
}
