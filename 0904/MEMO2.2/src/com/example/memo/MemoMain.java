package com.example.memo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MemoMain extends Activity implements OnItemClickListener{

	@Override
	public void onCreate(Bundle savedInstanceState){
		
		//ActivityクラスのonCreateを実行
		super.onCreate(savedInstanceState);
		
		//レイアウト設定ファイルの指定
		setContentView(R.layout.main_memo);
		
		//ListViewオブジェクト取得
		ListView listview = (ListView)findViewById(R.id.mainlist);
		
		//ListViewオブジェクトにクリックリスナーを設定
		listview.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
		//クリック時のListViewオブジェクト取得
		ListView listview = (ListView)parent;
		
		//選択された値取得
		String item = (String)listview.getItemAtPosition(position);
		
		//押下別インテント起動
		if(item.equals("新規メモ作成")){
			newMemo();
		}else if(item.equals("作成メモ一覧")){
			memosView();
		}
	}
	
	public void newMemo(){
		
		//インテント生成
		Intent intent = new Intent(this,MemoActivity.class);
		
		//アクティビティ起動
		startActivity(intent);
	}
	
	public void memosView(){
		
		//インテント生成
		Intent intent = new Intent(this,MemoList.class);
		
		//アクティビティ起動
		startActivity(intent);		
	}
}
