package com.example.contentprovidersample1;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class ProductContentProvider extends ContentProvider {
	
	//CreateItemHelperインスタンス生成
	CreateItemHelper databaseHelper;

	//データベース作成処理
	@Override
	public boolean onCreate() {
		
		databaseHelper = new CreateItemHelper(this.getContext());
		return true;
	}

	//データベースへの登録処理
	@Override
	public Uri insert(Uri arg0, ContentValues values) {
		
		SQLiteDatabase db = databaseHelper.getWritableDatabase();
		db.insert("item", null, values);
		return null;
	}	

	//データベースからデータ取得処理
	@Override
	public Cursor query(Uri arg0, String[] projection, String selection, 
			String[] selectionArgs, String sortOrder) {
		
		SQLiteDatabase db = databaseHelper.getReadableDatabase();
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		qb.setTables("item");
		Cursor c = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);
		return c;
	}
	
	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public String getType(Uri arg0) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	
	@Override
	public int update(Uri arg0, ContentValues arg1, String arg2, String[] arg3) {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

}
