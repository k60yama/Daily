package com.android.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class Viewtest extends Activity implements OnClickListener, OnCheckedChangeListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //RadioGroup オブジェクト取得
        RadioGroup r_group = (RadioGroup)findViewById(R.id.radio_group);
        r_group.setOnCheckedChangeListener(this);
        
        //Button オブジェクト取得
        Button button1 = (Button)findViewById(R.id.button1);        
        button1.setOnClickListener(this);
    }

	@Override
	//Button押下時のインスタンスメソッド
	public void onClick(View view) {
		
		/*
		Button button2 = (Button)findViewById(R.id.button2);
		
		if(button2.getVisibility() != View.VISIBLE){
			button2.setVisibility(View.VISIBLE);
		}else{
			button2.setVisibility(View.GONE);
		}
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
		*/
		
	}

	@Override
	//RadioButton選択時のインスタンスメソッド
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO 自動生成されたメソッド・スタブ
	
		//オブジェクト取得
		View[] items = {
				(TextView)findViewById(R.id.sendto_label),
				(EditText)findViewById(R.id.sendto)
		};
		
		//表示・非表示制御
		for(int i = 0; i < items.length; i++){
			if(checkedId == R.id.select2){
				items[i].setVisibility(View.VISIBLE);
			}else if(checkedId == R.id.select1){
				items[i].setVisibility(View.GONE);
			}
		}
		
	}
}