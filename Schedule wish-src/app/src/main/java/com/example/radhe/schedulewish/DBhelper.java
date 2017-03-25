package com.example.radhe.schedulewish;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by radhe on 24/3/17.
 */

public class DBhelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "events.db";
    public static final String CONTACTS_TABLE_NAME = "details";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_MESSAGE = "message";
    public static final String CONTACTS_COLUMN_DATE = "date";
    public static final String CONTACTS_COLUMN_CONTACT="contact";
    public static final String CONTACTS_COLUMN_FLAG="flag";

    public DBhelper(Context context){
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table details " +
                        "(id integer primary key autoincrement, message text,date text, contact text, flag integer)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }
    public boolean insertContact ( String message, String date, String contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("message",message );
        contentValues.put("date", date);
        contentValues.put("contact", contact);
        db.insert("details", null, contentValues);
        return true;
    }

    public ArrayList<String> getAllContacts(){
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from details", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_CONTACT)));
            res.moveToNext();
        }
        return array_list;
    }


    public ArrayList<HashMap<String,String>> getIndividuals(String date){

        ArrayList<HashMap<String,String >> array_list = new ArrayList<HashMap<String, String>>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from details where date = \'"+date+"\'"+" and flag = 0", null );
        updateALLContact();
        res.moveToFirst();

        while(res.isAfterLast() == false){
            HashMap<String ,String> hm = new HashMap<>();
            hm.put("number",res.getString(res.getColumnIndex(CONTACTS_COLUMN_CONTACT)));
            hm.put("message",res.getString(res.getColumnIndex("message")));
            array_list.add(hm);
            updateParticular(date);

            res.moveToNext();
        }
        Log.d("Query result",array_list.toString());





        return array_list;
    }
    public boolean updateALLContact() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("flag",0);
        db.update("details", contentValues, "\"1\""+ "= ? ", new String[] { Integer.toString(1) } );
        return true;
    }

    public boolean updateParticular(String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("flag",1);
        db.update("details", contentValues, "date = ?", new String[] { date} );
        return true;
    }
}
