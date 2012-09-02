package jp.co.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class TestActivity extends Activity {

	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.list);
		
		Toast.makeText(this, "hoge", Toast.LENGTH_SHORT).show();
		
	}
	
}
