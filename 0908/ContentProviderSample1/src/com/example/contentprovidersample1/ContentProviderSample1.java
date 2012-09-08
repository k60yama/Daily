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

	//インスタンス変数宣言
	public CreateItemHelper helper;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	//ActivityクラスのonCreateを実行
        super.onCreate(savedInstanceState);
        
        //レイアウト設定ファイルの指定
        setContentView(R.layout.contentprovidersample1);
        
        //各ボタンオブジェクト取得
        Button[] button = {
        		(Button)findViewById(R.id.registry),
        		(Button)findViewById(R.id.update),
        		(Button)findViewById(R.id.delete),
        		(Button)findViewById(R.id.view)
        };
        
        //各ボタンオブジェクトにクリックリスナーを設定
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
		
		//DB定義用インスタンス生成
		helper = new CreateItemHelper(this); 
		SQLiteDatabase db = helper.getWritableDatabase();
		
		//インスタンス生成
		SQLmethod dbinfo = new SQLmethod(db);
		
		//EditTextオブジェクト取得
		EditText[] values = {
				(EditText)findViewById(R.id.item_id),
				(EditText)findViewById(R.id.item_name),
				(EditText)findViewById(R.id.price)
		};
		
		//TableLayoutオブジェクト取得
		TableLayout tablelayout = (TableLayout)findViewById(R.id.item_list);
		tablelayout.removeAllViews();		//初期化
		
		//処理結果メッセージ初期化
		String result_msg = "";
		
		switch(view.getId()){
		
		//登録処理
		case R.id.registry:
			result_msg = dbinfo.dataRegistry(values);
			break;
			
		//更新処理
		case R.id.update:
			result_msg = dbinfo.dataUpdate(values);
			break;
		
		//削除処理
		case R.id.delete:
			result_msg = dbinfo.dataDelete(values);
			break;
			
		//表示処理	
		case R.id.view:
			result_msg = this.dataView(values,tablelayout);
			break;
		}
		
		//DBクローズ
		db.close();
		
		//処理結果メッセージ設定
		TextView result = (TextView)findViewById(R.id.result_msg);
		result.setText(result_msg);
	}
	
	//表示処理メソッド
	public String dataView(EditText[] value, TableLayout tablelayout){
		try{
			//DBオブジェクト取得
			SQLiteDatabase db = helper.getReadableDatabase();
			
			//列名定義
			String columns[] = {"productid","name","price"};
			
			//データ取得
			Cursor cursor = db.query("item", columns, null, null, null, null, "productid");
			
			//TableLayoutの表示範囲を設定
			tablelayout.setStretchAllColumns(true);
			
			//テーブル一覧のヘッダ設定
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
			
			return "データを取得しました。";
		}catch(Exception e){
			return "データ取得に失敗しました。";
		}
	}
	
	
}
