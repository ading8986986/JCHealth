package cn.jcheath.tools;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class ToastUtil {
	public static void showLong(Context context,int content) {
		Toast t = Toast.makeText(context, content, Toast.LENGTH_LONG);
		t.setGravity(Gravity.CENTER, 0, 0);
		t.show();
	}
	
	public static void showLong(Context context,String content) {
		Toast t = Toast.makeText(context, content, Toast.LENGTH_LONG);
		t.setGravity(Gravity.CENTER, 0, 0);
		t.show();
	}
	
	
	public static void showShort(Context context,int content) {
		Toast t = Toast.makeText(context, content, Toast.LENGTH_SHORT);
		t.setGravity(Gravity.CENTER, 0, 0);
		t.show();
	}

	public static void showShort(Context context, String content) {
		// TODO Auto-generated method stub
		Toast t = Toast.makeText(context, content, Toast.LENGTH_SHORT);
		t.setGravity(Gravity.CENTER, 0, 0);
		t.show();
	}
}
