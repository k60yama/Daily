package com.android.provider;

import android.os.Bundle;
import android.provider.CallLog;
import android.app.Activity;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ProviderActivity extends Activity {
	
	TableLayout table = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	//Activity�N���X��OnCreate�����s
        super.onCreate(savedInstanceState);
        
        //���C�A�E�g�ݒ�t�@�C���̎w��
        setContentView(R.layout.providerquestion);
        
        //TableLayout�I�u�W�F�N�g�擾
    	table = (TableLayout)findViewById(R.id.tablelayout);
    	
        try{
        	//�f�[�^�擾
        	Cursor cur = this.managedQuery(CallLog.Calls.CONTENT_URI,
        			null,
        			null,
        			null,
        			null);
        	
        	//�f�[�^�擾�`�F�b�N
        	if(cur.getCount() != 0){
        		//�擾�����f�[�^�\��
        		while(cur.moveToNext()){
        			String phoneNum = cur.getString(
        					cur.getColumnIndex(CallLog.Calls.NUMBER));
        			this.setPhoneNum(phoneNum);
        		}
        	}else{
        		//���b�Z�[�W�ݒ�
        		TextView view = new TextView(this);
        		view.setText("�f�[�^���擾�ł��܂���ł����B");
        		
        		//LinearLayout�I�u�W�F�N�g
        		LinearLayout linearlayout = (LinearLayout)findViewById(R.id.linearlayout);
        		linearlayout.addView(view);
        	}        	
        }catch(Exception e){
        	Log.e("ERROR",e.getMessage());
        }       
    }

    //�ʘb�����̐ݒ�
    public void setPhoneNum(String item){	
    	TableRow row = new TableRow(this);
    	TextView view = new TextView(this);
    	view.setText(item);
    	row.addView(view);
    	
    	//�e�[�u�����C�A�E�g�̐ݒ�
    	table.addView(row);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_provider, menu);
        return true;
    }
}
