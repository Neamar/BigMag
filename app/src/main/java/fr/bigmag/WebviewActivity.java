package fr.bigmag;

import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class WebviewActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_webview);

        WebView webView = (WebView) findViewById(R.id.webView);
        webView.setBackgroundColor(Color.TRANSPARENT);
        webView.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
        final View loader = findViewById(R.id.loader);

        String url = getIntent().getStringExtra("url");
        url += "?device_id=" + getAndroidId();
        webView.loadUrl(url);

        webView.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                loader.setVisibility(View.GONE);
            }
        });
    }

    public String getAndroidId() {
        return Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }
}
