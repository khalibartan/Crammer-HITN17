package com.example.radhe.schedulewish;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class Message extends AppCompatActivity {


    public TextView tv;
    public Button done;
    public static String msg;
    public List<String> recipients;
    public int date;
    public int month;
    public int year;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        tv = (TextView) findViewById(R.id.message);
        done = (Button) findViewById(R.id.ok);
        msg = tv.getText().toString();
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date = new SelectDate().getDate();
                month = new SelectDate().getMonth();
                year = new SelectDate().getYear();
                recipients = new ContactDisplay().getList();
                msg = tv.getText().toString();
                String mm = String.valueOf(month);
                if(mm.length()==1)
                    mm='0'+mm;
                String Date = String.valueOf(date) + "/" + mm + "/" + String.valueOf(year);
                DBhelper dBhelper = new DBhelper(getApplicationContext());
                for (int i = 0; i < recipients.size(); i++) {
                    String value = recipients.get(i);
                    String[] array = value.split("\n");
                    String number = array[1];
                    Log.d("yaha dekho",Date);
                    dBhelper.insertContact(msg, Date, number);
                }


                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }

        });

    }
}
