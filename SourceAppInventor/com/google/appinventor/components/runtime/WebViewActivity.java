package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public final class WebViewActivity extends Activity {

    /* renamed from: com.google.appinventor.components.runtime.WebViewActivity.1 */
    class C01301 extends WebViewClient {
        C01301() {
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.i("WebView", "Handling url " + url);
            Uri uri = Uri.parse(url);
            if (uri.getScheme().equals(Form.APPINVENTOR_URL_SCHEME)) {
                Intent resultIntent = new Intent();
                resultIntent.setData(uri);
                WebViewActivity.this.setResult(-1, resultIntent);
                WebViewActivity.this.finish();
            } else {
                view.loadUrl(url);
            }
            return true;
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebView webview = new WebView(this);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new C01301());
        setContentView(webview);
        Intent uriIntent = getIntent();
        if (uriIntent != null && uriIntent.getData() != null) {
            Uri uri = uriIntent.getData();
            String scheme = uri.getScheme();
            Log.i("WebView", "Got intent with URI: " + uri + ", scheme=" + scheme + ", host=" + uri.getHost());
            webview.loadUrl(uri.toString());
        }
    }
}