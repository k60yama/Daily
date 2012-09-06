package com.example.contentprovidersample1;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ContentProviderSample1 extends Activity implements OnClickListener{

	//�C���X�^���X�ϐ��錾
	public CreateItemHelper helper;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	//Activity�N���X��onCreate�����s
        super.onCreate(savedInstanceState);
        
        //���C�A�E�g�ݒ�t�@�C���̎w��
        setContentView(R.layout.contentprovidersample1);
        
        //�e�{�^���I�u�W�F�N�g�擾
        Button[] button = {
        		(Button)findViewById(R.id.registry),
        		(Button)findViewById(R.id.update),
        		(Button)findViewById(R.id.delete),
        		(Button)findViewById(R.id.view)
        };
        
        //�e�{�^���I�u�W�F�N�g�ɃN���b�N���X�i�[��ݒ�
        for(int i=0; i<button.length; i++){
        	button[i].setOnClickListener(this);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.contentprovidersample1, menu);
        return true;
    }

	@Override
	public void onClick(View view) {
		
		//DB��`�p�C���X�^���X����
		helper = new CreateItemHelper(this); 
		SQLiteDatabase db = helper.getWritableDatabase();
		
		//�C���X�^���X����
		SQLmethod dbinfo = new SQLmethod(db);
		
		//EditText�I�u�W�F�N�g�擾
		EditText[] values = {
				(EditText)findViewById(R.id.item_id),
				(EditText)findViewById(R.id.item_name),
				(EditText)findViewById(R.id.price)
		};
		
		//TableLayout�I�u�W�F�N�g�擾
		TableLayout tablelayout = (TableLayout)findViewById(R.id.item_list);
		tablelayout.removeAllViews();		//������
		
		//�������ʃ��b�Z�[�W������
		String result_msg = "";
		
		switch(view.getId()){
		
		//�o�^����
		case R.id.registry:
			result_msg = dbinfo.dataRegistry(values);
			break;
			
		//�X�V����
		case R.id.update:
			result_msg = dbinfo.dataUpdate(values);
			break;
		
		//�폜����
		case R.id.delete:
			result_msg = dbinfo.dataDelete(values);
			break;
			
		//�\������	
		case R.id.view:
			result_msg = this.dataView(values,tablelayout);
			break;
		}
		
		//DB�N���[�Y
		db.close();
		
		//�������ʃ��b�Z�[�W�ݒ�
		TextView result = (TextView)findViewById(R.id.result_msg);
		result.setText(result_msg);
	}
	
	//�\���������\�b�h
	public String dataView(EditText[] value, TableLayout tablelayout){
		try{
			//DB�I�u�W�F�N�g�擾
			SQLiteDatabase db = helper.getReadableDatabase();
			
			//�񖼒�`
			String columns[] = {"productid","name","price"};
			
			//�f�[�^�擾
			Cursor cursor = db.query("item", columns, null, null, null, null, "productid");
			
			//TableLayout�̕\���͈͂�ݒ�
			tablelayout.setStretchAllColumns(true);
			
			//�e�[�u���ꗗ�̃w�b�_�ݒ�
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
				TextView productid = new TextView(this);
				productid.setGravity(Gravity.CENTER_HORIZONTAL);
				productid.setText(cursor.getString(0));
				row.addView(productid);
				
				TextView name = new TextView(this);
				name.setGravity(Gravity.CENTER_HORIZONTAL);
				name.setText(cursor.getString(1));
				row.addView(name);
				
				TextView price = new TextView(this);
				price.setGravity(Gravity.CENTER_HORIZONTAL);
				price.setText(cursor.getString(2));
				row.addView(price);
				
				tablelayout.addView(row);
			}
			
			return "�f�[�^���擾���܂����B";
		}catch(Exception e){
			return "�f�[�^�擾�Ɏ��s���܂����B";
		}
	}
	
	
}
