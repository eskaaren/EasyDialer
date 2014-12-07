package com.giskeskaaren.easydialer;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;

/**
 * Creator: Eivin Giske Skaaren
 * Email: eskaaren@yahoo.no
 * Date: 6/3/12
 * Time: 10:47 PM
 */
public class DeleteSound extends ListActivity {
    private String[] entryArr = new String[0];
    private String[] entryValArr = new String[0];
    private File[] files = new File(Environment.getExternalStorageDirectory().getPath() + "/dialpad/sounds").listFiles();

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        for ( File aFile : files )
            if ( aFile.isDirectory() ) {
                String[] tmp = entryArr;
                String[] tmp2 = entryValArr;
                entryArr = new String[entryArr.length+1];
                entryValArr = new String[entryValArr.length+1];
                for (int i = 0; i<tmp.length; ++i) {
                    entryArr[i] = tmp [i];
                    entryValArr[i] = tmp2[i];
                }
                entryArr[entryArr.length-1] = aFile.toString().substring(aFile.toString().lastIndexOf("/")+1, aFile.toString().length());
                entryValArr[entryValArr.length-1] = aFile.toString();
            }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, entryArr);
        setListAdapter(adapter);
    }

    public void onListItemClick(ListView l, View v, int position, long id) {
        Toast t = Toast.makeText(getApplicationContext(), "Deleting: " + entryArr[position], 3000);
        t.show();

        deleteDirectory(files[position]);
        startActivity(getIntent()); finish();
    }

    static public boolean deleteDirectory(File path) {
        if( path.exists() ) {
            File[] files = path.listFiles();
            for(int i=0; i<files.length; i++) {
                if(files[i].isDirectory()) {
                    deleteDirectory(files[i]);
                }
                else {
                    files[i].delete();
                }
            }
        }
        return( path.delete() );
    }

    public void onBackPressed() {
        Intent i = new Intent(this, Preferences.class);
        startActivity(i);
        finish();
    }
}
