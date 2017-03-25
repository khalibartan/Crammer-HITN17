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
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ContactDisplay extends AppCompatActivity {

    public ListView listView ;
    public static List<String>  list = new ArrayList<>();
    public static List<Contacts> contacts_list = new ArrayList<>();
    public ArrayAdapter<Contacts> listAdapter;
    public List<String> mylist = new EventDetail().getList();
    public Button done;
    public View row;
    public static List<String> final_ans = new ArrayList<>();
    public List<String> getList(){
        return final_ans;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_display);

        done = (Button) findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<listAdapter.getCount();i++)
                {
                    Contacts c = listAdapter.getItem(i);
                    if(c.isChecked())
                    {
                        final_ans.add(c.getName());
                    }
                }
                Log.d("Final",final_ans.toString());
                Intent i = new Intent(getApplicationContext(),SelectDate.class);
                startActivity(i);

            }
        });



        listView = (ListView) findViewById(R.id.list);

        Log.d("list",mylist.toString());
      //  ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.list_item,R.id.ind_name,mylist);





        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contacts c = listAdapter.getItem(position);
                c.toggleChecked();
                ContactsViewHolder holder = (ContactsViewHolder)view.getTag();
                holder.getCheckBox().setChecked(c.isChecked());
            }
        });

        for(int i=0;i<mylist.size();i++)
        {
            Contacts c = new Contacts(mylist.get(i));
            contacts_list.add(c);
        }

        listAdapter = new CustomArrayAdapter(getApplicationContext(),contacts_list);
        listView.setAdapter(listAdapter);


        /*listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                String selected = mylist.get(position);
                if (list.indexOf(selected)>=0){
                    list.remove(selected);
                    listView.getChildAt(position).setBackgroundColor(Color.TRANSPARENT);
                }
                else {
                    list.add(selected);
                    view.setBackgroundColor(Color.GREEN);

                    //listView.getChildAt(position).setBackgroundColor(Color.BLUE);
/*
                    for (int i = 0; i < listView.getChildCount(); i++) {


                        if(listView.getItemAtPosition(i).toString().equals(selected)){
                            listView.getChildAt(i).setBackgroundColor(Color.BLUE);

                            Log.d("gggg",listView.getItemAtPosition(i).toString());
                            break;
                        }
                    }
                    */

               /* }
                Log.d("selected",selected);*/
               // listView.setItemChecked(position, true);
                //view.setSelected(true);
               // view.setBackgroundColor(Color.RED);
       //     }
        //});
    }

}
