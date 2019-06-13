package com.arnab.datta.babycare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HealthCare extends AppCompatActivity {

    Button fv,cc,sa,ga,c,h;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_care);

        fv=findViewById(R.id.btnfev);
        cc=findViewById(R.id.btncc);
        sa=findViewById(R.id.btnsa);
        ga=findViewById(R.id.btnga);
        c=findViewById(R.id.btnc);
        h=findViewById(R.id.btnh);

        fv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityFever();
            }
        });
        cc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityCold();
            }
        });
        sa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivitySA();
            }
        });
        ga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityGA();
            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityConsti();
            }
        });
        h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityHicc();
            }
        });

    }
    public  void openActivityFever(){
        Intent intent = new Intent(this, Fever.class);
        startActivity(intent);
    }

    public  void openActivityCold(){
        Intent intent = new Intent(this, CommonCold.class);
        startActivity(intent);
    }

    public  void openActivitySA(){
        Intent intent = new Intent(this, StomachAche.class);
        startActivity(intent);
    }
    public  void openActivityGA(){
        Intent intent = new Intent(this, GasandAcidity.class);
        startActivity(intent);
    }

    public  void openActivityConsti(){
        Intent intent = new Intent(this, Constipation.class);
        startActivity(intent);
    }

    public  void openActivityHicc(){
        Intent intent = new Intent(this, Hiccups.class);
        startActivity(intent);
    }

}
