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
    	
    	//ActivityクラスのonCreateを実行
        super.onCreate(savedInstanceState);
        
        //レイアウト設定ファイルの指定
        setContentView(R.layout.accesssample1);
        
        //各ボタンのオブジェクト取得
        Button[] button = {
        		(Button)findViewById(R.id.registry),
        		(Button)findViewById(R.id.view)
        };
        
        //クリックリスナーを設定
        for(int i=0; i<button.length; i++){
        	button[i].setOnClickListener(this);
        }
    }

    @Override
	public void onClick(View view) {
		
    	//処理結果メッセージ初期化
    	String msg = "";
    	
    	//入力情報取得
    	EditText[] values = {
    			(EditText)findViewById(R.id.item_id),
    			(EditText)findViewById(R.id.item_name),
    			(EditText)findViewById(R.id.price),
    	};
		
    	//Uri取得
    	uri = Uri.parse("content://com.example.contentprovidersample1");
    	
    	//TableLayoutオブジェクト取得
    	TableLayout tablelayout = (TableLayout)findViewById(R.id.item_list);
    	tablelayout.removeAllViews();		//初期化
    	
    	//押下別処理
    	switch(view.getId()){
    	case R.id.registry:
    		msg = this.dataRegistry(values);
    		break;
    	case R.id.view:
    		msg = this.dataView(values, tablelayout);
    		break;
    	}
    	
    	//処理結果メッセージ設定
    	TextView label = (TextView)findViewById(R.id.result_msg);
    	label.setText(msg);
	}
    
    //データ登録処理
    public String dataRegistry(EditText[] value){
    	try{
    		//登録データ設定
    		ContentValues val = new ContentValues();
    		val.put("productid", value[0].getText().toString());
    		val.put("name", "***" + value[1].getText().toString() + "***");
    		val.put("price", value[2].getText().toString());
    		
    		//データ登録
    		this.getContentResolver().insert(uri, val);
    		
    		return "データを登録しました！";
    	}catch(Exception e){
    		return "データ登録に失敗しました！";
    	}
    }
    
    //データ表示処理
    public String dataView(EditText[] value, TableLayout tablelayout){
    	try{
    		//データ取得
    		Cursor cursor = managedQuery(uri, null, null, null, "productid");
    		
    		//テーブルレイアウトの表示範囲設定
    		tablelayout.setStretchAllColumns(true);
    		
    		//テーブル一覧のヘッダ部設定
    		TableRow headrow = new TableRow(this);
    		
    		TextView headtxt1 = new TextView(this);
    		headtxt1.setText("商品ID");
    		headtxt1.setGravity(Gravity.CENTER_HORIZONTAL);
    		headtxt1.setWidth(60);
    		headrow.addView(headtxt1);
    		
    		TextView headtxt2 = new TextView(this);
    		headtxt2.setText("商品名");
    		headtxt2.setGravity(Gravity.CENTER_HORIZONTAL);
    		headtxt2.setWidth(100);
    		headrow.addView(headtxt2);
    		
    		TextView headtxt3 = new TextView(this);
    		headtxt3.setText("価格");
    		headtxt3.setGravity(Gravity.CENTER_HORIZONTAL);
    		headtxt3.setWidth(60);
    		headrow.addView(headtxt3);
    		
    		tablelayout.addView(headrow);
    		
    		//取得したデータを明細部に設定
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
    		
    		return "データを取得しました！";
    	}catch(Exception e){
    		return "データ取得に失敗しました！";
    	}
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_content_provider_access_sample1, menu);
        return true;
    }

	

    
}
