package com.giskeskaaren.easydialer;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Creator: Eivin Giske Skaaren
 * Email: eskaaren@yahoo.no
 * Date: 6/2/12
 * Time: 12:04 AM
 */
public class Download extends Activity {

    WebView web;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download);

        web = (WebView) findViewById(R.id.DownloadView);
        web.setWebViewClient(new WebClient());
        web.getSettings().setJavaScriptEnabled(true);
        //web.loadUrl("http://dt031g.programvaruteknik.nu/dialpad/sounds/");
        web.loadUrl("http://www.giskeskaaren.com/dialpad/sounds/");

    }

    public class WebClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            if (url.contains(".dps")) {
                Intent i = new Intent(Download.this, FileHandler.class);
                i.putExtra("url", url);
                i.putExtra("target", Environment.getExternalStorageDirectory().getPath() + "/dialpad/sounds/" + url.substring(url.lastIndexOf("/")+1, url.length()-4));
                startActivity(i);
                finish();
            }
            return true;

        }

    }
    // To handle "Back" key press event for WebView to go back to previous screen.
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && web.canGoBack()) {
            web.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

