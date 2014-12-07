package com.giskeskaaren.easydialer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;

import java.io.File;

/**
 * Creator: Eivin Giske Skaaren
 * Email: eskaaren@yahoo.no
 * Date: 6/13/12
 * Time: 3:39 AM
 */
public class Preferences extends PreferenceActivity {
    private File path;
    private String[] entryArr;
    private String[] entryValArr;
    private PreferenceScreen root;
    private ListPreference listPref;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        root = getPreferenceScreen();
        setValues();

        Preference myPref = findPreference("delete");
        myPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                Intent i = new Intent(Preferences.this, DeleteSound.class);
                startActivity(i);
                root.removePreference(listPref);
                finish();
                return true;
            }
        });
    }


    private void setValues() {
        listPref = new ListPreference(this);
        entryArr = new String[0];
        entryValArr = new String[0];

        path = new File(Environment.getExternalStorageDirectory().getPath() + "/dialpad/sounds");
        if (!path.exists()) {
            path.mkdirs();
        }

        File[] files = new File(path.toString()).listFiles();
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


        listPref.setKey("soundfiles"); //Refer to get the pref value
        listPref.setEntries(entryArr);
        listPref.setEntryValues(entryValArr);
        listPref.setDialogTitle("Soundfiles");
        listPref.setTitle("Soundfiles");
        listPref.setSummary("Choose Sound");
        root.addPreference(listPref);// Adding under the category
    }
}
