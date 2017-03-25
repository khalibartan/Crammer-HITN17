package com.example.radhe.schedulewish;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MyAlarmReciever   extends BroadcastReceiver {



    public  ArrayList<HashMap<String,String>> list = new ArrayList<>();
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("onReceive", "ladskjflsakjdflskjdflskjdfslkjdflasdf");
      //  Toast.makeText(context, "OnReceive alarm test", Toast.LENGTH_LONG).show();

        Calendar c = Calendar.getInstance();
        Log.d("Current time => ",c.getTime().toString());

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = df.format(c.getTime());
        Log.d("formated date",formattedDate);
        list = new DBhelper(context).getIndividuals(formattedDate);

        for(int i=0;i<list.size();i++) {
            SMSSender ss = new SMSSender();
            ss.sendSMSMessage(list.get(i).get("number"),list.get(i).get("message"));
            Log.d("number",list.get(i).get("number"));
            Log.d("message",list.get(i).get("message"));
            Log.d("sent ","bhej diya");
        }

    }






}
