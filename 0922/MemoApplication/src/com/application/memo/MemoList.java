package com.application.memo;

import java.util.ArrayList;
import java.util.HashMap;
import android.app.Activity;
import android.app.LauncherActivity.ListItem;
import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TwoLineListItem;

public class MemoList extends ListActivity {
	
	//�A�N�e�B�r�e�BOnCreate���\�b�h
	public void onCreate(Bundle savedInstanceState){
		
		//Activity�N���X��OnCreate�����s
		super.onCreate(savedInstanceState);
		
		//���C�A�E�g�ݒ�t�@�C�����w��
		setContentView(R.layout.list_memo);
	        
        //SimpleAdapter�̃C���X�^���X����
        SimpleAdapter adapter = new SimpleAdapter(
        		this,
        		dataRead(),
        		android.R.layout.simple_list_item_2,
        		new String[]{"subject","date"},
        		new int[]{android.R.id.text1, android.R.id.text2});
        
        this.setListAdapter(adapter);
 	}
	
	//�f�[�^�ǂݍ���
	public ArrayList<HashMap<String,String>> dataRead(){
		
			//���X�g�\���p
			ArrayList<HashMap<String,String>> listData = new ArrayList<HashMap<String,String>>();
			
			//DB�I�u�W�F�N�g�擾
			MemoHelper helper = new MemoHelper(this);
			SQLiteDatabase db = helper.getReadableDatabase();
			
			//�񖼒�`
			String columns[] = {
					"id", "subject", "body", "createDate"
			};	
				
			//�f�[�^�擾
			Cursor cursor = db.query("memo", columns, null, null, null, null, "id");
			if(cursor.getCount() == 0){
				//TextView�I�u�W�F�N�g�擾
				TextView view = (TextView)findViewById(R.id.warning);
				view.setVisibility(View.VISIBLE);
				view.setText("�ۑ�����Ă��郁���͂���܂���I");
			}
			
			while(cursor.moveToNext()){
				listData.add(getMapData(new String[][]{
						{"date","�쐬�����F" + cursor.getString(3)}, 
						{"subject",cursor.getString(0) + " " +cursor.getString(1)}
				}));			
			}
			
			db.close();						//DB�I�u�W�F�N�g�N���[�Y
			return listData;	
	}
	
	private HashMap<String,String> getMapData(String[][] values){
    	
    	//HashMap�̃C���X�^���X�𐶐�
    	HashMap<String,String> map = new HashMap<String,String>();
    	
    	//�L�[�ƒl�����蓖��
    	for(int i = 0; i<values.length; i++){
    		map.put(values[i][0], values[i][1]);
    	}
    	
    	return map;
    }

	
	@Override
	public void onListItemClick(ListView list, View view, int position, long id){
		
		//ListActivity�N���X��onListItemClick�����s
		super.onListItemClick(list, view, position, id);
		
		//TwoLineListItem�^�Ƃ��āAView���擾
		TwoLineListItem textview = (TwoLineListItem)view;
		
		Toast.makeText(this, textview.getText1().getText().toString(), Toast.LENGTH_SHORT).show();
	}
	
}
