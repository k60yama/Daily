package com.application.memo;

import java.text.SimpleDateFormat;
import java.util.Date;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class MemoSQLMethod {

	private MemoHelper helper;
	private SQLiteDatabase db;
	
	//初期化(コンストラクタ)
	public MemoSQLMethod(MemoHelper helper){
		this.helper = helper;
	}
	
	//データ登録処理
	public void insertSQLMethod(String subject, String body){		
		this.db = this.helper.getWritableDatabase();	//DBオブジェクト取得
		this.db.beginTransaction();						//トランザクション制御開始
		
		//データ登録処理
		ContentValues val = new ContentValues();
		val.put("subject", subject);					//件名
		val.put("body", body);							//本文
		
		//作成日時設定
		String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		val.put("createDate", date);
		
		this.db.insert("memo", null, val);				//データ登録
		this.db.setTransactionSuccessful();				//コミット
		this.db.endTransaction();						//トランザクション制御終了
		
		this.db.close();								//DBオブジェクトクローズ
	}
}
