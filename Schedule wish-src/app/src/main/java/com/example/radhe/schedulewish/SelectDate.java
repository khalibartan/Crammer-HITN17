package com.example.radhe.schedulewish;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class SelectDate extends AppCompatActivity {
    public static List<HashMap<String,String>> list = new ArrayList<>();
    public static int date;
    public static int month;
    public static int year;
    public DatePicker datePicker;
    public Button next;

    public int getDate(){
        return date;
    }
    public int getMonth(){
        return month;
    }
    public int getYear(){
        return  year;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_date);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        date = datePicker.getDayOfMonth();
        month = datePicker.getMonth() +1 ;
        year = datePicker.getYear();
        Log.d("date",date+" "+month+" "+year);
        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date = datePicker.getDayOfMonth();
                month = datePicker.getMonth() + 1;
                year = datePicker.getYear();
                Log.d("date",date+" "+month+" "+year);

                Intent i = new Intent(getApplicationContext(),Message.class);
                startActivity(i);
            }
        });


    }
}
