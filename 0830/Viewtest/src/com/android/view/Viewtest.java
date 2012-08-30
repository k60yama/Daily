package com.android.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Viewtest extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button button1 = (Button)findViewById(R.id.button1);
        
        button1.setOnClickListener(this);
    }

	@Override
	public void onClick(View view) {
		
		/*
		Button button2 = (Button)findViewById(R.id.button2);
		
		if(button2.getVisibility() != View.VISIBLE){
			button2.setVisibility(View.VISIBLE);
		}else{
			button2.setVisibility(View.GONE);
		}
		*/
		
		View[] items = {
				(TextView)findViewById(R.id.sendto_label),
				(EditText)findViewById(R.id.sendto)
		};
		
		for(int i = 0; i < items.length; i++){
			if(items[i].getVisibility() == View.VISIBLE){
				items[i].setVisibility(View.GONE);
			}else{
				items[i].setVisibility(View.VISIBLE);
			}
		}
		
		
	}
}