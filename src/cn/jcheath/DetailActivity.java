package cn.jcheath;

import cn.jcheath.ui.MainActivity.JsInterface;
import cn.jcheath.ui.MainActivity.MyWebChromClient;
import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class DetailActivity extends Activity {

	private String TAG="DetailActivity";
	private WebView myWebView;
	private long exitTime = 0;
	private String loadUrl;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		initData();
		initView();
		initWebSetting();
	}
	
	private void initData(){
		loadUrl = getIntent().getStringExtra("loadUrl");
	}
	
	public void initView(){
		myWebView = (WebView) findViewById(R.id.wb_content);
	}
	
	@SuppressLint({ "JavascriptInterface", "SetJavaScriptEnabled" })
	public void initWebSetting(){
		
		myWebView.getSettings().setJavaScriptEnabled(true);
		myWebView.setWebChromeClient(new MyWebChromClient());
		myWebView.loadUrl("file:///android_asset/"+loadUrl);
	}
	
public class MyWebChromClient extends WebChromeClient{
    	
    	
    	@Override
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            String message = consoleMessage.message();
            int lineNumber = consoleMessage.lineNumber();
            String sourceID = consoleMessage.sourceId();
            String messageLevel = consoleMessage.message();

            Log.i("[WebView]", String.format("[%s] sourceID: %s lineNumber: %n message: %s",
                    messageLevel, sourceID, lineNumber, message));

            return super.onConsoleMessage(consoleMessage);
        }

        @Override
        public void onConsoleMessage(String message, int lineNumber, String sourceID) {
            Log.i("[WebView]", String.format("sourceID: %s lineNumber: %n message: %s", sourceID,
                    lineNumber, message));
            super.onConsoleMessage(message, lineNumber, sourceID);
        }
    }

	
}
