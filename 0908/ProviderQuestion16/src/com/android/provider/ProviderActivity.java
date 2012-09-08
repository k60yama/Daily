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
    	
    	//ActivityクラスのOnCreateを実行
        super.onCreate(savedInstanceState);
        
        //レイアウト設定ファイルの指定
        setContentView(R.layout.providerquestion);
        
        //TableLayoutオブジェクト取得
    	table = (TableLayout)findViewById(R.id.tablelayout);
    	
        try{
        	//データ取得
        	Cursor cur = this.managedQuery(CallLog.Calls.CONTENT_URI,
        			null,
        			null,
        			null,
        			null);
        	
        	//データ取得チェック
        	if(cur.getCount() != 0){
        		//取得したデータ表示
        		while(cur.moveToNext()){
        			String phoneNum = cur.getString(
        					cur.getColumnIndex(CallLog.Calls.NUMBER));
        			this.setPhoneNum(phoneNum);
        		}
        	}else{
        		//メッセージ設定
        		TextView view = new TextView(this);
        		view.setText("データが取得できませんでした。");
        		
        		//LinearLayoutオブジェクト
        		LinearLayout linearlayout = (LinearLayout)findViewById(R.id.linearlayout);
        		linearlayout.addView(view);
        	}        	
        }catch(Exception e){
        	Log.e("ERROR",e.getMessage());
        }       
    }

    //通話履歴の設定
    public void setPhoneNum(String item){	
    	TableRow row = new TableRow(this);
    	TextView view = new TextView(this);
    	view.setText(item);
    	row.addView(view);
    	
    	//テーブルレイアウトの設定
    	table.addView(row);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_provider, menu);
        return true;
    }
}
