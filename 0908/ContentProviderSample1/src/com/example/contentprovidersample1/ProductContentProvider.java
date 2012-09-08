package com.example.contentprovidersample1;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class ProductContentProvider extends ContentProvider {
	
	//CreateItemHelper�C���X�^���X����
	CreateItemHelper databaseHelper;

	//�f�[�^�x�[�X�쐬����
	@Override
	public boolean onCreate() {
		
		databaseHelper = new CreateItemHelper(this.getContext());
		return true;
	}

	//�f�[�^�x�[�X�ւ̓o�^����
	@Override
	public Uri insert(Uri arg0, ContentValues values) {
		
		SQLiteDatabase db = databaseHelper.getWritableDatabase();
		db.insert("item", null, values);
		return null;
	}	

	//�f�[�^�x�[�X����f�[�^�擾����
	@Override
	public Cursor query(Uri arg0, String[] projection, String selection, 
			String[] selectionArgs, String sortOrder) {
		
		SQLiteDatabase db = databaseHelper.getReadableDatabase();
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		qb.setTables("item");
		Cursor c = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);
		return c;
	}
	
	//�f�[�^�X�V����
	@Override
	public int update(Uri arg0, ContentValues values, String condition, String[] arg3) {
		
		SQLiteDatabase db = databaseHelper.getWritableDatabase();
		return db.update("item", values, condition, null);
	}
	
	
	//�f�[�^�폜����
	@Override
	public int delete(Uri arg0, String condition, String[] arg2) {
		
		SQLiteDatabase db = databaseHelper.getWritableDatabase();
		return db.delete("item", condition, null);
	}

	@Override
	public String getType(Uri arg0) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}
	
	

}
