package com.example.contentprovidersample1;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;

public class SQLmethod {

	//インスタンス変数宣言
	public SQLiteDatabase db;
	
	//コンストラクタ
	public SQLmethod(SQLiteDatabase db ){
		this.db = db;
	}
	
	//データ登録処理メソッド
	public String dataRegistry(EditText[] value){
		try{
			//トランザクション制御開始
			db.beginTransaction();
			
			//登録データ設定
			ContentValues val = new ContentValues();
			val.put("productid", value[0].getText().toString());
			val.put("name", value[1].getText().toString());
			val.put("price", value[2].getText().toString());
			
			//データ登録
			db.insert("item", null, val);
			
			//コミット
			db.setTransactionSuccessful();
			
			//トランザクション制御終了
			db.endTransaction();
			
			return "データを登録しました。";
		}catch(Exception e){
			return "データ登録に失敗しました。";
		}
	}
	
	//データ更新処理メソッド
	public String dataUpdate(EditText[] value){
		try{
			//トランザクション制御開始
			db.beginTransaction();
			
			//更新データ設定
			ContentValues val = new ContentValues();
			val.put("name", value[1].getText().toString());
			val.put("price",value[2].getText().toString());
			
			//データ更新
			db.update("item", val, setCondition(value[0]), null);
			
			//コミット処理
			db.setTransactionSuccessful();
			
			//トランザクション制御終了
			db.endTransaction();
			
			return "データを更新しました。";
		}catch(Exception e){
			return "データ更新に失敗しました。";
		}
	}
	
	//データ削除処理
	public String dataDelete(EditText[] value){
		try{
			//トランザクション制御開始
			db.beginTransaction();
			
			//データ削除
			db.delete("item", setCondition(value[0]), null);
			
			//コミット処理
			db.setTransactionSuccessful();
			
			//トランザクション制御終了
			db.endTransaction();
			
			return "データを削除しました。";
		}catch(Exception e){
			return "データ削除に失敗しました。";
		}
	}
	
	//検索条件設定メソッド
	private String setCondition(EditText value){
		
		//検索条件
		String condition = null;		//初期化
		
		if(value != null && !(value.equals(""))){
			condition = "productid ='" + value.getText().toString() + "'";
		}
		
		return condition;
	}
	
}
