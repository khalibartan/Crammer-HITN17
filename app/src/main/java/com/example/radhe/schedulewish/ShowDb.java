package com.example.radhe.schedulewish;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class ShowDb extends AppCompatActivity {

    public ArrayList<String > arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_db);
        arrayList = new DBhelper(getApplicationContext()).getAllContacts();
        Log.d("ADDED",arrayList.toString());


    }
}
