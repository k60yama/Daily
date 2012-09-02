package com.android.memo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MemoList extends Activity {
	
	//TextViewオブジェクト
	TextView view;
	
	//エラーメッセージ
	public static String msg;
	
	public void onCreate(Bundle savedInstanceState){
		
		//ActivityクラスのOnCreateを実行
		super.onCreate(savedInstanceState);
		
		//レイアウト設定ファイルを指定
		setContentView(R.layout.list_memo);
		
		//TextViewオブジェクト取得
		view = (TextView)findViewById(R.id.warning);
		
		//ファイルチェック
		if(!fileChk()){
			view.setVisibility(View.VISIBLE);
			view.setText(msg);
			return ;	//強制終了
		}
		
		//ListViewオブジェクトを取得
        ListView list = (ListView)findViewById(R.id.list);
        
        //SimpleAdapterのインスタンス生成
        SimpleAdapter adapter = new SimpleAdapter(
        		this,
        		fileRead(),
        		android.R.layout.simple_list_item_2,
        		new String[]{"subject","date"},
        		new int[]{android.R.id.text1, android.R.id.text2});
        
        //リスト表示
        list.setAdapter(adapter);
	}
	
	//ファイル読み込み
	public ArrayList<HashMap<String,String>> fileRead(){
		
		try{
			
			//ファイル名指定
			FileInputStream stream = openFileInput(MemoActivity.FILE_NAME);
			
			//バッファ領域確保
			BufferedReader in = new BufferedReader(new InputStreamReader(stream));
			
			//ファイル内容格納
			String[] str = {};
			
			//文字列格納
			String line = "";
			
			//リスト表示用
			ArrayList<HashMap<String,String>> listData = new ArrayList<HashMap<String,String>>();
			
			while((line = in.readLine()) != null){
				
				//カンマ区切りで表示
				str = line.split(",");
				
				listData.add(getMapData(new String[][]{{"date","作成日時：" + str[0]}, {"subject",str[1]}}));			
			}
			
			return listData;
			
		}catch(Exception e){
			
			Toast.makeText(this, "ファイル読み込みで失敗しました！", Toast.LENGTH_SHORT).show();
			return null;
		}
		
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
	
	//ファイルチェックメソッド
	public boolean fileChk(){
		
		//ファイル取得
		File file = this.getFileStreamPath(MemoActivity.FILE_NAME);
		
		//ファイル有無チェック
		if(!file.exists()){
			msg = "ファイルが見つかりません！";
			return false;
		}
		
		//ファイルサイズチェック
		if(file.length() == 0){
			msg = "ファイルサイズが０バイトです。";
			return false;
		}
		
		return true;
	}
}
