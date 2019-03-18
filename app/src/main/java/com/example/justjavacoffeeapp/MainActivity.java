package com.example.justjavacoffeeapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int OneCofRs=5;
    int NoOfCoffee=0;
    String Name,FinalMsg;
    Boolean WhippedCream,Chocolate;
    int TotalPrice;


    EditText inputEd;
    Button AddBtn, SubBtn, OrderBtn;
    CheckBox Check1, Check2;
    TextView NoOfcofTv,FinalMsgTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputEd = findViewById(R.id.InputEd);
        AddBtn = findViewById(R.id.AddBtn);
        SubBtn = findViewById(R.id.SubBtn);
        OrderBtn = findViewById(R.id.Orderbtn);
        Check1 = findViewById(R.id.Check1);
        Check2 = findViewById(R.id.Check2);
        NoOfcofTv = findViewById(R.id.NoOfCofTv);
        FinalMsgTv = findViewById(R.id.FinalMsgTv);

        AddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Increament();
            }
        });


        SubBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Decreament();

            }
        });

        OrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalculatePrice();
                DisplayMsg();


            }
        });

    }

    public void Increament(){
        NoOfCoffee = NoOfCoffee + 1;
        NoOfcofTv.setText(""+NoOfCoffee);
    }


    public void Decreament(){
        if (NoOfCoffee<=0){
            return;
        }
        else {
            NoOfCoffee = NoOfCoffee - 1;
            NoOfcofTv.setText(""+NoOfCoffee);
        }

    }

    public void IsToppings(){
       WhippedCream = Check1.isChecked();
       Chocolate = Check2.isChecked();
    }


    public void CalculatePrice(){

        IsToppings();

        Name = inputEd.getText().toString();
        if (WhippedCream){
            OneCofRs=6;
        }
        if (Chocolate){
            OneCofRs=7;
        }
        if (WhippedCream&&Chocolate){
            OneCofRs=8;
        }
        TotalPrice = NoOfCoffee*OneCofRs;
        FinalMsg = "Name: " + Name +"\n"+
                "WhippedCream: "+ WhippedCream+"\n"+
                "Chocolate: "+ Chocolate + "\n"+
                "Quantity: " + NoOfCoffee + "\n"+
                "Total Price: $"+ TotalPrice;
    }

    public void DisplayMsg(){
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.putExtra(Intent.EXTRA_SUBJECT,"just java order for you: "+ Name);
        intent.putExtra(Intent.EXTRA_TEXT,FinalMsg);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        else Toast.makeText(this, "no Email App ", Toast.LENGTH_SHORT).show();

        }

    }