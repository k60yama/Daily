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
        
        //RadioGroup �I�u�W�F�N�g�擾
        RadioGroup r_group = (RadioGroup)findViewById(R.id.radio_group);
        r_group.setOnCheckedChangeListener(this);
        
        //Button �I�u�W�F�N�g�擾
        Button button1 = (Button)findViewById(R.id.button1);        
        button1.setOnClickListener(this);
    }

	@Override
	//Button�������̃C���X�^���X���\�b�h
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
	//RadioButton�I�����̃C���X�^���X���\�b�h
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
	
		//�I�u�W�F�N�g�擾
		View[] items = {
				(TextView)findViewById(R.id.sendto_label),
				(EditText)findViewById(R.id.sendto)
		};
		
		//�\���E��\������
		for(int i = 0; i < items.length; i++){
			if(checkedId == R.id.select2){
				items[i].setVisibility(View.VISIBLE);
			}else if(checkedId == R.id.select1){
				items[i].setVisibility(View.GONE);
			}
		}
		
	}
}