package com.example.contentprovideraccesssample1;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class ContentProviderAccessSample1 extends Activity implements OnClickListener{

	public Uri uri;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	//Activity�N���X��onCreate�����s
        super.onCreate(savedInstanceState);
        
        //���C�A�E�g�ݒ�t�@�C���̎w��
        setContentView(R.layout.accesssample1);
        
        //�e�{�^���̃I�u�W�F�N�g�擾
        Button[] button = {
        		(Button)findViewById(R.id.registry),
        		(Button)findViewById(R.id.view)
        };
        
        //�N���b�N���X�i�[��ݒ�
        for(int i=0; i<button.length; i++){
        	button[i].setOnClickListener(this);
        }
    }

    @Override
	public void onClick(View view) {
		
    	//�������ʃ��b�Z�[�W������
    	String msg = "";
    	
    	//���͏��擾
    	EditText[] values = {
    			(EditText)findViewById(R.id.item_id),
    			(EditText)findViewById(R.id.item_name),
    			(EditText)findViewById(R.id.price),
    	};
		
    	//Uri�擾
    	uri = Uri.parse("content://com.example.contentprovidersample1");
    	
    	//TableLayout�I�u�W�F�N�g�擾
    	TableLayout tablelayout = (TableLayout)findViewById(R.id.item_list);
    	tablelayout.removeAllViews();		//������
    	
    	//�����ʏ���
    	switch(view.getId()){
    	case R.id.registry:
    		msg = this.dataRegistry(values);
    		break;
    	case R.id.view:
    		msg = this.dataView(values, tablelayout);
    		break;
    	}
    	
    	//�������ʃ��b�Z�[�W�ݒ�
    	TextView label = (TextView)findViewById(R.id.result_msg);
    	label.setText(msg);
	}
    
    //�f�[�^�o�^����
    public String dataRegistry(EditText[] value){
    	try{
    		//�o�^�f�[�^�ݒ�
    		ContentValues val = new ContentValues();
    		val.put("productid", value[0].getText().toString());
    		val.put("name", "***" + value[1].getText().toString() + "***");
    		val.put("price", value[2].getText().toString());
    		
    		//�f�[�^�o�^
    		this.getContentResolver().insert(uri, val);
    		
    		return "�f�[�^��o�^���܂����I";
    	}catch(Exception e){
    		return "�f�[�^�o�^�Ɏ��s���܂����I";
    	}
    }
    
    //�f�[�^�\������
    public String dataView(EditText[] value, TableLayout tablelayout){
    	try{
    		//�f�[�^�擾
    		Cursor cursor = managedQuery(uri, null, null, null, "productid");
    		
    		//�e�[�u�����C�A�E�g�̕\���͈͐ݒ�
    		tablelayout.setStretchAllColumns(true);
    		
    		//�e�[�u���ꗗ�̃w�b�_���ݒ�
    		TableRow headrow = new TableRow(this);
    		
    		TextView headtxt1 = new TextView(this);
    		headtxt1.setText("���iID");
    		headtxt1.setGravity(Gravity.CENTER_HORIZONTAL);
    		headtxt1.setWidth(60);
    		headrow.addView(headtxt1);
    		
    		TextView headtxt2 = new TextView(this);
    		headtxt2.setText("���i��");
    		headtxt2.setGravity(Gravity.CENTER_HORIZONTAL);
    		headtxt2.setWidth(100);
    		headrow.addView(headtxt2);
    		
    		TextView headtxt3 = new TextView(this);
    		headtxt3.setText("���i");
    		headtxt3.setGravity(Gravity.CENTER_HORIZONTAL);
    		headtxt3.setWidth(60);
    		headrow.addView(headtxt3);
    		
    		tablelayout.addView(headrow);
    		
    		//�擾�����f�[�^�𖾍ו��ɐݒ�
    		while(cursor.moveToNext()){
    			TableRow row = new TableRow(this);
    			
    			TextView id = new TextView(this);
    			id.setText(cursor.getString(1));
    			id.setGravity(Gravity.CENTER_HORIZONTAL);
    			row.addView(id);
    			
    			TextView name = new TextView(this);
    			name.setText(cursor.getString(2));
    			name.setGravity(Gravity.CENTER_HORIZONTAL);
    			row.addView(name);
    			
    			TextView price = new TextView(this);
    			price.setText(cursor.getString(3));
    			price.setGravity(Gravity.CENTER_HORIZONTAL);
    			row.addView(price);
    			
    			tablelayout.addView(row);
    		}
    		
    		return "�f�[�^���擾���܂����I";
    	}catch(Exception e){
    		return "�f�[�^�擾�Ɏ��s���܂����I";
    	}
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_content_provider_access_sample1, menu);
        return true;
    }

	

    
}
