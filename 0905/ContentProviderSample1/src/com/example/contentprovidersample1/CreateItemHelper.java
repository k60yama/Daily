package com.example.contentprovidersample1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CreateItemHelper extends SQLiteOpenHelper {

	//�f�[�^�x�[�X��`
	public CreateItemHelper(Context con) {
		super(con, "itemdb", null, 1);
	}

	@Override
	//�e�[�u����`
	public void onCreate(SQLiteDatabase db) {
		String sql 
			= "create table item(_id integer primary key autoincrement," +
			  "productid text not null," +
			  "name text not null," +
			  "price integer default 0)";

		//SQL���s
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u

	}

}
