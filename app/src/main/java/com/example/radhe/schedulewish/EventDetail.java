package com.example.radhe.schedulewish;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EventDetail extends FragmentActivity implements ContactsDisplay.OnFragmentInteractionListener {


    public static ArrayList<HashMap<String,String>> list = new ArrayList<>();
    public Button participant;
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        participant = (Button) findViewById(R.id.participants);
        participant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showContacts();


            }
        });

    }
    public List<String> getList(){
        List <String> mylist = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            HashMap<String,String>hm = new HashMap<>();
            hm = list.get(i);
            String con = hm.get("name")+"\n"+hm.get("contact");
            mylist.add(con);
        }
        Log.d("in eventdetail",mylist.toString());

        return mylist;
    }

    public void showContacts(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
            //After this point you wait for callback in onRequestPermissionsResult(int, String[], int[]) overriden method
        } else {
            // Android version is lesser than 6.0 or the permission is already granted.
             getContacts();
             Bundle bundle = new Bundle();
             bundle.putSerializable("contact",list);
            FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
            ContactsDisplay contactsDisplay = new ContactsDisplay();
            contactsDisplay.setArguments(bundle);
            ft.addToBackStack("details");
            //ft.replace(R.id.activity_event_detail,contactsDisplay);
            ft.add(R.id.fragment, contactsDisplay, "hello");
            ft.commit();

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                showContacts();
            } else {
                Toast.makeText(this, "Until you grant the permission, we canot display the names", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void getContacts(){
        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);
        while (phones.moveToNext())
        {
            String name=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            //Toast.makeText(getApplicationContext(),name, Toast.LENGTH_LONG).show();
            HashMap<String,String> Contacts = new HashMap<>();

            Contacts.put("contact",phoneNumber);
            Contacts.put("name",name);
            list.add(Contacts);
          //  Log.d("Contact ",name+" "+phoneNumber);
        }
        phones.close();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
