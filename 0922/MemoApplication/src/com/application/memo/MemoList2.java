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
		//ListActivityクラスのonCreateを実行
		super.onCreate(savedInstanceState);
		
		//レイアウト設定ファイル指定
		setContentView(R.layout.list_memo);
		
		//ListView表示用
		List<Map<String,String>> dataList = new ArrayList<Map<String,String>>();
		
		//DBオブジェクト取得
		MemoHelper helper = new MemoHelper(this);
		SQLiteDatabase db = helper.getReadableDatabase();
		
		//列名定義
		String columns[] = {"id", "subject", "body", "createDate"};	
			
		//データ取得
		Cursor cursor = db.query("memo", columns, null, null, null, null, "id");
		if(cursor.getCount() == 0){
			//TextViewオブジェクト取得
			TextView view = (TextView)findViewById(R.id.warning);
			view.setVisibility(View.VISIBLE);
			view.setText("保存されているメモはありません！");
			return ;		//強制終了
		}
		
		//保存されたメモ情報を表示
		while(cursor.moveToNext()){
			Map<String,String> data = new HashMap<String,String>();
			data.put("subject", cursor.getString(0) + " " +cursor.getString(1));
			data.put("date", cursor.getString(3));
			dataList.add(data);
		}
		
		//SimpleAdapterのインスタンス生成
        SimpleAdapter adapter = new SimpleAdapter(
        		this,
        		dataList,
        		android.R.layout.simple_list_item_2,
        		new String[]{"subject","date"},
        		new int[]{android.R.id.text1, android.R.id.text2});
        
        //リスト表示
		setListAdapter(adapter);
	}
	
	@Override
	public void onListItemClick(ListView list, View view, int position, long id){
		
		//ListActivityクラスのonListItemClickを実行
		super.onListItemClick(list, view, position, id);
		
		//TwoLineListItem型として、Viewを取得
		TwoLineListItem textview = (TwoLineListItem)view;
		
		Toast.makeText(this, textview.getText1().getText().toString(), Toast.LENGTH_SHORT).show();
	}
}
