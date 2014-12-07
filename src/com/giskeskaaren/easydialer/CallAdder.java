package com.giskeskaaren.easydialer;

import android.content.Context;

import java.text.DateFormat;
import java.util.Date;

/**
 * Creator: Eivin Giske Skaaren
 * Email: eskaaren@yahoo.no
 * Date: 5/16/12
 * Time: 12:09 AM
 */
public class CallAdder {
    private SQLiteAdapter mySQLiteAdapter;
    private String[] values = new String[] {};
    private Context context;
    private double lastscore;
    private String name;

    public CallAdder(String name, Context c) {
        this.context = c;
        this.name = name;
    }

    public void addCall(String number, String dbname) {

//        //Uncomment to clear DB
//        mySQLiteAdapter = new SQLiteAdapter(context, dbname);
//        mySQLiteAdapter.openToWrite();
//        mySQLiteAdapter.deleteAll();
//        mySQLiteAdapter.close();

        //Add to DB
        mySQLiteAdapter = new SQLiteAdapter(context, dbname);
        mySQLiteAdapter.openToWrite();
        mySQLiteAdapter.insert(number, DateFormat.getDateTimeInstance().format(new Date()));
        mySQLiteAdapter.close();
    }

    public String[] getCalls(String dbname) {
        mySQLiteAdapter = new SQLiteAdapter(context, dbname);

        mySQLiteAdapter.openToRead();
        String contentRead = mySQLiteAdapter.queueAll();
        values = contentRead.split("\t");
        mySQLiteAdapter.close();
        return values;
    }

}
