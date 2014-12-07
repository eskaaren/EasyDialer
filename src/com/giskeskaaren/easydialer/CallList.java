package com.giskeskaaren.easydialer;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.Arrays;
import java.util.Collections;

/**
 * Creator: Eivin Giske Skaaren
 * Email: eskaaren@yahoo.no
 * Date: 6/19/12
 * Time: 11:45 PM
 */
public class CallList extends ListActivity {
    private CallAdder c;
    private static final String DB = "CALLHISTORY_DB";

    public void onCreate(Bundle savedInstanceState) {
        getWindow().setTitle("Call History");
        super.onCreate(savedInstanceState);
        c = new CallAdder(DB, this);
        String[] values = c.getCalls(DB);
        Collections.reverse(Arrays.asList(values)); // Newest entries first

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
    }
}