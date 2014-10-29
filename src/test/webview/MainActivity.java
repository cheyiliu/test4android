
package test.webview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import com.test4android.R;

public class MainActivity extends Activity {
    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    private EditText field;
    private WebView browser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_webview_activity_main);
        field = (EditText) findViewById(R.id.urlField);
        browser = (WebView) findViewById(R.id.webView1);
        browser.setWebViewClient(new MyBrowser());

        // load default baidu.com for convinent test
        open(null);

    }

    public void open(View view) {
        String url = field.getText().toString();
        String httpHeader = "http://www.";
        if (!url.startsWith(httpHeader)) {
            url = httpHeader + url;
        }
        browser.getSettings().setLoadsImagesAutomatically(true);
        browser.getSettings().setJavaScriptEnabled(true);
        browser.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        // for test to load local HTML
        url = "file:///android_asset/stun_docs/index.html";

        // for test 2
        // url =
        // "http://221.181.100.148/content/chinamobile/RDportal07141455156583338/747/37/23499172.page?referFolderId=385";
        browser.loadUrl(url);

    }

    @Override
    public void onBackPressed()
    {
        if (browser.canGoBack()) {
            Log.i("test", "browser.canGoBack()");
            browser.goBack();
        }
        else {
            Log.i("test", "super.onBackPressed()");
            super.onBackPressed();
        }
    }

}
