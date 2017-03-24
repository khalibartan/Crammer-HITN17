package com.example.radhe.schedulewish;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ContactDisplay extends AppCompatActivity {

    public ListView listView ;
    public static List<HashMap<String,String>> list = new ArrayList<>();

    public Button done;

    public List<HashMap<String,String>> getList(){
        return list;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_display);

        done = (Button) findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),SelectDate.class);
                startActivity(i);

            }
        });

        listView = (ListView) findViewById(R.id.list);
        List<String> mylist = new EventDetail().getList();
        Log.d("list",mylist.toString());

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.list_item,R.id.ind_name,mylist);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String,String>hm = new HashMap<String, String>();
                TextView tv = (TextView) view.findViewById(R.id.ind_name);
                String details = tv.getText().toString();
                String []det = details.split("\n");
                hm.put("name",det[0]);
                hm.put("contact",det[1]);
                list.add(hm);
                //view.setBackgroundColor(Color.GRAY);
                view.setSelected(true);

            }
        });
    }

}
