package com.application.memo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TwoLineListItem;

public class MemoList2 extends ListActivity {

	@Override
	public void onCreate(Bundle savedInstanceState){	
		//ListActivity�N���X��onCreate�����s
		super.onCreate(savedInstanceState);
		
		//���C�A�E�g�ݒ�t�@�C���w��
		setContentView(R.layout.list_memo);
		
		//ListView�\���p
		List<Map<String,String>> dataList = new ArrayList<Map<String,String>>();
		
		//DB�I�u�W�F�N�g�擾
		MemoHelper helper = new MemoHelper(this);
		SQLiteDatabase db = helper.getReadableDatabase();
		
		//�񖼒�`
		String columns[] = {"id", "subject", "body", "createDate"};	
			
		//�f�[�^�擾
		Cursor cursor = db.query("memo", columns, null, null, null, null, "id");
		if(cursor.getCount() == 0){
			//TextView�I�u�W�F�N�g�擾
			TextView view = (TextView)findViewById(R.id.warning);
			view.setVisibility(View.VISIBLE);
			view.setText("�ۑ�����Ă��郁���͂���܂���I");
			return ;		//�����I��
		}
		
		//�ۑ����ꂽ��������\��
		while(cursor.moveToNext()){
			Map<String,String> data = new HashMap<String,String>();
			data.put("subject", cursor.getString(0) + " " +cursor.getString(1));
			data.put("date", cursor.getString(3));
			dataList.add(data);
		}
		
		//SimpleAdapter�̃C���X�^���X����
        SimpleAdapter adapter = new SimpleAdapter(
        		this,
        		dataList,
        		android.R.layout.simple_list_item_2,
        		new String[]{"subject","date"},
        		new int[]{android.R.id.text1, android.R.id.text2});
        
        //���X�g�\��
		setListAdapter(adapter);
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
