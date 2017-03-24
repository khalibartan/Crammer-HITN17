package com.example.radhe.schedulewish;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

public class SMSSender extends Activity {

    String message;
    String phoneNo;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 10;



   // public void send(String phone)
    {
      //  grantPermissionForCamera(phone);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void sendSMSMessage(String phone) {
        message = "kuch bhi";
        String st = "";
        for (int i = 0; i < phone.length(); i++) {
            if (phone.charAt(i) != ' ') {
                st += phone.charAt(i);
            }
            phoneNo = st;
            Log.d("Formatted phone no", phoneNo);
        }
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNo, null, "sms message", null, null);
    }




}
