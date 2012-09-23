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
	
	//アクティビティOnCreateメソッド
	public void onCreate(Bundle savedInstanceState){
		
		//ActivityクラスのOnCreateを実行
		super.onCreate(savedInstanceState);
		
		//レイアウト設定ファイルを指定
		setContentView(R.layout.list_memo);
	        
        //SimpleAdapterのインスタンス生成
        SimpleAdapter adapter = new SimpleAdapter(
        		this,
        		dataRead(),
        		android.R.layout.simple_list_item_2,
        		new String[]{"subject","date"},
        		new int[]{android.R.id.text1, android.R.id.text2});
        
        this.setListAdapter(adapter);
 	}
	
	//データ読み込み
	public ArrayList<HashMap<String,String>> dataRead(){
		
			//リスト表示用
			ArrayList<HashMap<String,String>> listData = new ArrayList<HashMap<String,String>>();
			
			//DBオブジェクト取得
			MemoHelper helper = new MemoHelper(this);
			SQLiteDatabase db = helper.getReadableDatabase();
			
			//列名定義
			String columns[] = {
					"id", "subject", "body", "createDate"
			};	
				
			//データ取得
			Cursor cursor = db.query("memo", columns, null, null, null, null, "id");
			if(cursor.getCount() == 0){
				//TextViewオブジェクト取得
				TextView view = (TextView)findViewById(R.id.warning);
				view.setVisibility(View.VISIBLE);
				view.setText("保存されているメモはありません！");
			}
			
			while(cursor.moveToNext()){
				listData.add(getMapData(new String[][]{
						{"date","作成日時：" + cursor.getString(3)}, 
						{"subject",cursor.getString(0) + " " +cursor.getString(1)}
				}));			
			}
			
			db.close();						//DBオブジェクトクローズ
			return listData;	
	}
	
	private HashMap<String,String> getMapData(String[][] values){
    	
    	//HashMapのインスタンスを生成
    	HashMap<String,String> map = new HashMap<String,String>();
    	
    	//キーと値を割り当て
    	for(int i = 0; i<values.length; i++){
    		map.put(values[i][0], values[i][1]);
    	}
    	
    	return map;
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
