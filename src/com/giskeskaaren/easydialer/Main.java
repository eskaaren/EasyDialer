package com.giskeskaaren.easydialer;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.view.*;
import android.widget.Toast;

/**
 * Creator: Eivin Giske Skaaren
 * Email: eskaaren@yahoo.no
 * Date: 5/13/12
 * Time: 1:15 AM
 */
public class Main extends Activity implements KeyEvent.Callback {
    private DialPadView d;

    private boolean dtmf = false;

    private SharedPreferences settings;


    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        d = new DialPadView(getApplicationContext());
        setContentView(d);

        settings = PreferenceManager.getDefaultSharedPreferences(this);
        dtmf = settings.getBoolean("dtmf", false);
        d.setDtmf(dtmf);
        d.loadSettings(settings);

        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);

        if (!(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))) {
            Toast t = Toast.makeText(getApplicationContext(), "Error no SD card mounted, exiting.", 3000);
            t.show();
            finish();
        }
    }

    public void onResume() {
        super.onResume();
        dtmf = settings.getBoolean("dtmf", false);
        d.setDtmf(dtmf);
        d.loadSettings(settings);
    }

    public void onDestroy() {
        super.onDestroy();
        if (dtmf) {
//            if (tone != null) {
//                tone.stopTone();
//                tone.release();
//            }
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.prefs) {
            Intent intent = new Intent(this, Preferences.class);
            startActivity(intent);
        }
        else if (item.getItemId() == R.id.download) {
            Intent intent = new Intent(this, Download.class);
            startActivity(intent);
        }
        else if (item.getItemId() == R.id.callhist) {
            Intent intent = new Intent(this, CallList.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }
        else if (event.getKeyCode() != KeyEvent.KEYCODE_MENU) {
            d.onKeyDown(keyCode,event);
            return true;
        }
        return false;
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (event.getKeyCode() != KeyEvent.KEYCODE_MENU) {
            d.onKeyUp(keyCode,event);
            return true;
        }
        return false;
    }
}