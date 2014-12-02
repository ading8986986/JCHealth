package cn.jcheath.ui;

import cn.jcheath.DetailActivity;
import cn.jcheath.R;
import cn.jcheath.R.id;
import cn.jcheath.R.layout;
import cn.jcheath.R.menu;
import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.ConsoleMessage;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private String TAG="MainActivity";
	private WebView myWebView;
	private long exitTime = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		initWebSetting();
	}

	public void initView(){
		myWebView = (WebView) findViewById(R.id.wb_content);
		
	}
	
	@SuppressLint({ "JavascriptInterface", "SetJavaScriptEnabled" })
	public void initWebSetting(){
		
		myWebView.getSettings().setJavaScriptEnabled(true);
		myWebView.addJavascriptInterface(new JsInterface(), "activityController");
		myWebView.setWebChromeClient(new MyWebChromClient());
		myWebView.loadUrl("file:///android_asset/my_health.html");
	}
	
	
	/**
     * 按键响应，在WebView中查看网页时，按返回键的时候按浏览历史退回,如果不做此项处理则整个WebView返回退出
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        // Check if the key event was the Back button and if there's history
        /*if ((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack())
        {
            // 返回键退回
            myWebView.goBack();
            return true;
        }*/
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
        	if((System.currentTimeMillis()-exitTime) > 2000){  
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();                                
                exitTime = System.currentTimeMillis();   
            } else {
                finish();
                System.exit(0);
            }
        	return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up
        // to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }
    
    public class JsInterface{
    	@JavascriptInterface
		public void startActivityWithUrl(String url){
			Log.i(TAG,url);
			Intent intent = new Intent(MainActivity.this,DetailActivity.class);
			intent.putExtra("loadUrl", url);
			startActivity(intent);
		}
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
