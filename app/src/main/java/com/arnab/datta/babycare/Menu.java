package com.arnab.datta.babycare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;

public class Menu extends AppCompatActivity {
   // GridLayout mainGrid ;
    private ImageView t1,t2,t3,t4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        t1= findViewById(R.id.imgtemp);
        t2=findViewById(R.id.imagebrething);
        t3=findViewById(R.id.imageposition);
        t4=findViewById(R.id.imagehumidity);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivitytemperature();
            }
        });
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivitybreathing ();
            }
        });
        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityposition();
            }
        });
        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityHumedity();
            }
        });

      /*  mainGrid = findViewById(R.id.mainGrid);
        setSingleEvent(mainGrid);
    }
    private  void setSingleEvent(GridLayout mainGrid){
        for(int i =0;i < mainGrid.getChildCount();i++ )
        {
            CardView cardView = (CardView)mainGrid.getChildAt(i);
            final int finalI = i ;


                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openActivitytemperature();
                    }
                });



        }*/
    }
    private void openActivitytemperature (){
        Intent intent = new Intent(this,Temperature.class);
        startActivity(intent);

    }
    private void OpenActivitybreathing (){
        Intent intent = new Intent(this,Breathing.class);
        startActivity(intent);

    }
    private void openActivityposition(){
        Intent intent = new Intent(this,position.class);
        startActivity(intent);

    }
    private void openActivityHumedity(){
        Intent intent = new Intent(this,Humedity.class);
        startActivity(intent);
    }
}
