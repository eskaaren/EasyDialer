package com.giskeskaaren.easydialer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.ToneGenerator;
import android.net.Uri;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.io.File;

/**
 * Creator: Eivin Giske Skaaren
 * Email: eskaaren@yahoo.no
 * Date: 5/13/12
 * Time: 1:15 AM
 */
public class DialPadView extends LinearLayout implements View.OnTouchListener, KeyEvent.Callback, View.OnLongClickListener, MediaPlayer.OnCompletionListener {
    private ImageButton b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, bs, bp, arrow, phone;
    private String SOUNDPATH = Environment.getExternalStorageDirectory().getPath() + "/dialpad/sounds/";
    private String SOUNDTYPE = "";
    private String [] soundfiles;
    private EditText textbox;
    private ToneGenerator tone;
    private MediaPlayer mp;
    private boolean dtmf = false;
    private Context context;
    private CallAdder callAdder;
    private static final String DB = "CALLHISTORY_DB";


//    public DialPadView(Context context, AttributeSet attr) {
//        super(context, attr);
//        inflate(context, R.layout.dialpadview, this);
//        setButtons();
//        this.context = context;
//    }

    public DialPadView(Context context) {
        super(context);
        View.inflate(context, R.layout.dialpadview, this);
        setButtons();
        this.context = context;
        callAdder = new CallAdder(DB, context);
    }

    public void setButtons() {
        b0 = (ImageButton) findViewById(R.id.num0Button);
        b1 = (ImageButton) findViewById(R.id.num1Button);
        b2 = (ImageButton) findViewById(R.id.num2Button);
        b3 = (ImageButton) findViewById(R.id.num3Button);
        b4 = (ImageButton) findViewById(R.id.num4Button);
        b5 = (ImageButton) findViewById(R.id.num5Button);
        b6 = (ImageButton) findViewById(R.id.num6Button);
        b7 = (ImageButton) findViewById(R.id.num7Button);
        b8 = (ImageButton) findViewById(R.id.num8Button);
        b9 = (ImageButton) findViewById(R.id.num9Button);
        bs = (ImageButton) findViewById(R.id.numsButton);
        bp = (ImageButton) findViewById(R.id.numpButton);
        arrow = (ImageButton) findViewById(R.id.arrowbutton);
        phone = (ImageButton) findViewById(R.id.phonebutton);

        b0.setOnTouchListener(this);
        b1.setOnTouchListener(this);
        b2.setOnTouchListener(this);
        b3.setOnTouchListener(this);
        b4.setOnTouchListener(this);
        b5.setOnTouchListener(this);
        b6.setOnTouchListener(this);
        b7.setOnTouchListener(this);
        b8.setOnTouchListener(this);
        b9.setOnTouchListener(this);
        bs.setOnTouchListener(this);
        bp.setOnTouchListener(this);
        arrow.setOnTouchListener(this);
        phone.setOnTouchListener(this);

        arrow.setOnLongClickListener(this);

        textbox = (EditText) findViewById(R.id.textbox);
    }

    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        return true;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (view.getId()) {
            case R.id.arrowbutton:
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    delNum();
                }
                break;
            case R.id.phonebutton:
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    call();
                }
                break;
            case R.id.num0Button:
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    playSound(R.id.num0Button);
                    addNum("0");
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    stopSound();
                }
                break;
            case R.id.num1Button:
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    playSound(R.id.num1Button);
                    addNum("1");
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    stopSound();
                }
                break;
            case R.id.num2Button:
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    playSound(R.id.num2Button);
                    addNum("2");
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    stopSound();
                }
                break;
            case R.id.num3Button:
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    playSound(R.id.num3Button);
                    addNum("3");
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    stopSound();
                }
                break;
            case R.id.num4Button:
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    playSound(R.id.num4Button);
                    addNum("4");
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    stopSound();
                }
                break;
            case R.id.num5Button:
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    playSound(R.id.num5Button);
                    addNum("5");
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    stopSound();
                }
                break;
            case R.id.num6Button:
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    playSound(R.id.num6Button);
                    addNum("6");
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    stopSound();
                }
                break;
            case R.id.num7Button:
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    playSound(R.id.num7Button);
                    addNum("7");
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    stopSound();
                }
                break;
            case R.id.num8Button:
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    playSound(R.id.num8Button);
                    addNum("8");
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    stopSound();
                }
                break;
            case R.id.num9Button:
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    playSound(R.id.num9Button);
                    addNum("9");
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    stopSound();
                }
                break;
            case R.id.numsButton:
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    playSound(R.id.numsButton);
                    addNum("*");
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    stopSound();
                }
                break;
            case R.id.numpButton:
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    playSound(R.id.numpButton);
                    addNum("#");
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    stopSound();
                }
                break;
        }
        return false;
    }

    private void call() {
        callAdder.addCall(textbox.getText().toString(), DB);
        Intent i = new Intent(Intent.ACTION_CALL);
        i.setData(Uri.parse("tel:" + textbox.getText().toString()));
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        super.getContext().startActivity(i);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getRepeatCount() == 0) {
            switch (event.getKeyCode()) {
                case KeyEvent.KEYCODE_0:
                    b0.setPressed(true);
                    playSound(R.id.num0Button);
                    addNum("0");
                    break;
                case KeyEvent.KEYCODE_1:
                    b1.setPressed(true);
                    playSound(R.id.num1Button);
                    addNum("1");
                    break;
                case KeyEvent.KEYCODE_2:
                    b2.setPressed(true);
                    playSound(R.id.num2Button);
                    addNum("2");
                    break;
                case KeyEvent.KEYCODE_3:
                    b3.setPressed(true);
                    playSound(R.id.num3Button);
                    addNum("3");
                    break;
                case KeyEvent.KEYCODE_4:
                    b4.setPressed(true);
                    playSound(R.id.num4Button);
                    addNum("4");
                    break;
                case KeyEvent.KEYCODE_5:
                    b5.setPressed(true);
                    playSound(R.id.num5Button);
                    addNum("5");
                    break;
                case KeyEvent.KEYCODE_6:
                    b6.setPressed(true);
                    playSound(R.id.num6Button);
                    addNum("6");
                    break;
                case KeyEvent.KEYCODE_7:
                    b7.setPressed(true);
                    playSound(R.id.num7Button);
                    addNum("7");
                    break;
                case KeyEvent.KEYCODE_8:
                    b8.setPressed(true);
                    playSound(R.id.num8Button);
                    addNum("8");
                    break;
                case KeyEvent.KEYCODE_9:
                    b9.setPressed(true);
                    playSound(R.id.num9Button);
                    addNum("9");
                    break;
                case KeyEvent.KEYCODE_STAR:
                    bs.setPressed(true);
                    playSound(R.id.numsButton);
                    addNum("*");
                    break;
                case KeyEvent.KEYCODE_POUND:
                    bp.setPressed(true);
                    playSound(R.id.numpButton);
                    addNum("#");
                    break;
            }
        }
        return false;
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.KEYCODE_0:
                b0.setPressed(false);
                break;
            case KeyEvent.KEYCODE_1:
                b1.setPressed(false);
                break;
            case KeyEvent.KEYCODE_2:
                b2.setPressed(false);
                break;
            case KeyEvent.KEYCODE_3:
                b3.setPressed(false);
                break;
            case KeyEvent.KEYCODE_4:
                b4.setPressed(false);
                break;
            case KeyEvent.KEYCODE_5:
                b5.setPressed(false);
                break;
            case KeyEvent.KEYCODE_6:
                b6.setPressed(false);
                break;
            case KeyEvent.KEYCODE_7:
                b7.setPressed(false);
                break;
            case KeyEvent.KEYCODE_8:
                b8.setPressed(false);
                break;
            case KeyEvent.KEYCODE_9:
                b9.setPressed(false);
                break;
            case KeyEvent.KEYCODE_STAR:
                bs.setPressed(false);
                break;
            case KeyEvent.KEYCODE_POUND:
                bp.setPressed(false);
                break;
        }
        stopSound();
        return false;
    }

    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        return false;
    }


    private void stopSound() {
        if (dtmf) {
            if (tone != null) {
                tone.stopTone();
            }
        }
//        if (mp != null) {
//            while (mp.isPlaying()) {
//                //Do nothing
//            }
//            mp.stop();
//            mp.reset();
//            mp.release();
//            mp = null;
//        }
    }

    private void playSound(int numButton) {
        if (dtmf) {
            tone = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
            switch (numButton) {
                case R.id.num0Button:
                    tone.startTone(ToneGenerator.TONE_DTMF_0);
                    break;
                case R.id.num1Button:
                    tone.startTone(ToneGenerator.TONE_DTMF_1);
                    break;
                case R.id.num2Button:
                    tone.startTone(ToneGenerator.TONE_DTMF_2);
                    break;
                case R.id.num3Button:
                    tone.startTone(ToneGenerator.TONE_DTMF_3);
                    break;
                case R.id.num4Button:
                    tone.startTone(ToneGenerator.TONE_DTMF_4);
                    break;
                case R.id.num5Button:
                    tone.startTone(ToneGenerator.TONE_DTMF_5);
                    break;
                case R.id.num6Button:
                    tone.startTone(ToneGenerator.TONE_DTMF_6);
                    break;
                case R.id.num7Button:
                    tone.startTone(ToneGenerator.TONE_DTMF_7);
                    break;
                case R.id.num8Button:
                    tone.startTone(ToneGenerator.TONE_DTMF_8);
                    break;
                case R.id.num9Button:
                    tone.startTone(ToneGenerator.TONE_DTMF_9);
                    break;
                case R.id.numsButton:
                    tone.startTone(ToneGenerator.TONE_DTMF_S);
                    break;
                case R.id.numpButton:
                    tone.startTone(ToneGenerator.TONE_DTMF_P);
                    break;
            }
        }

        else {
            if (mp != null) {
                mp.stop();
                mp.reset();
                mp.release();
                mp = null;
            }
            switch (numButton) {
                case R.id.num0Button:
                    mp = MediaPlayer.create(getContext().getApplicationContext(), Uri.fromFile(new File(SOUNDPATH + SOUNDTYPE + "zero.mp3")));
                    break;
                case R.id.num1Button:
                    mp = MediaPlayer.create(getContext().getApplicationContext(), Uri.fromFile(new File(SOUNDPATH + SOUNDTYPE + "one.mp3")));
                    break;
                case R.id.num2Button:
                    mp = MediaPlayer.create(getContext().getApplicationContext(), Uri.fromFile(new File(SOUNDPATH + SOUNDTYPE + "two.mp3")));
                    break;
                case R.id.num3Button:
                    mp = MediaPlayer.create(getContext().getApplicationContext(), Uri.fromFile(new File(SOUNDPATH + SOUNDTYPE + "three.mp3")));
                    break;
                case R.id.num4Button:
                    mp = MediaPlayer.create(getContext().getApplicationContext(), Uri.fromFile(new File(SOUNDPATH + SOUNDTYPE + "four.mp3")));
                    break;
                case R.id.num5Button:
                    mp = MediaPlayer.create(getContext().getApplicationContext(), Uri.fromFile(new File(SOUNDPATH + SOUNDTYPE + "five.mp3")));
                    break;
                case R.id.num6Button:
                    mp = MediaPlayer.create(getContext().getApplicationContext(), Uri.fromFile(new File(SOUNDPATH + SOUNDTYPE + "six.mp3")));
                    break;
                case R.id.num7Button:
                    mp = MediaPlayer.create(getContext().getApplicationContext(), Uri.fromFile(new File(SOUNDPATH + SOUNDTYPE + "seven.mp3")));
                    break;
                case R.id.num8Button:
                    mp = MediaPlayer.create(getContext().getApplicationContext(), Uri.fromFile(new File(SOUNDPATH + SOUNDTYPE + "eight.mp3")));
                    break;
                case R.id.num9Button:
                    mp = MediaPlayer.create(getContext().getApplicationContext(), Uri.fromFile(new File(SOUNDPATH + SOUNDTYPE + "nine.mp3")));
                    break;
                case R.id.numsButton:
                    mp = MediaPlayer.create(getContext().getApplicationContext(), Uri.fromFile(new File(SOUNDPATH + SOUNDTYPE + "star.mp3")));
                    break;
                case R.id.numpButton:
                    mp = MediaPlayer.create(getContext().getApplicationContext(), Uri.fromFile(new File(SOUNDPATH + SOUNDTYPE + "pound.mp3")));
                    break;
            }
            if (mp != null) {
                mp.start();
            }
        }
    }

    public void addNum(String s) {
        textbox.setText(textbox.getText().toString() + s);
    }

    public void delNum() {
        String s = textbox.getText().toString();
        if (s.length() > 0) {
            s = s.substring(0, s.length() - 1);
            textbox.setText(s);
        }
    }

    public boolean onLongClick(View view) {
        if (view.getId() == R.id.arrowbutton) {
            textbox.setText("");
        }
        return false;
    }

    public void setDtmf(boolean b) {
        if (b) {
            dtmf = true;
        }
        else dtmf = false;
    }

//    public void loadSounds(SharedPreferences settings) {
//        //Preferences p = findViewById(R.id.prefs);
//        Resources r = getResources();
//        String[] s = r.getStringArray(R.array.soundfiles);
//        SharedPreferences.Editor e = settings.edit();
//           e.putString("soundfileee", s[0]);
//        e.commit();
//    }

    public void loadSettings(SharedPreferences settings) {
        SOUNDPATH = settings.getString("soundfiles", "") + "/";
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        mp.release();
        mp = null;
    }


}
