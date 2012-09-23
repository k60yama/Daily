package com.application.memo;

import java.text.SimpleDateFormat;
import java.util.Date;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class MemoSQLMethod {

	private MemoHelper helper;
	private SQLiteDatabase db;
	
	//������(�R���X�g���N�^)
	public MemoSQLMethod(MemoHelper helper){
		this.helper = helper;
	}
	
	//�f�[�^�o�^����
	public void insertSQLMethod(String subject, String body){		
		this.db = this.helper.getWritableDatabase();	//DB�I�u�W�F�N�g�擾
		this.db.beginTransaction();						//�g�����U�N�V��������J�n
		
		//�f�[�^�o�^����
		ContentValues val = new ContentValues();
		val.put("subject", subject);					//����
		val.put("body", body);							//�{��
		
		//�쐬�����ݒ�
		String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		val.put("createDate", date);
		
		this.db.insert("memo", null, val);				//�f�[�^�o�^
		this.db.setTransactionSuccessful();				//�R�~�b�g
		this.db.endTransaction();						//�g�����U�N�V��������I��
		
		this.db.close();								//DB�I�u�W�F�N�g�N���[�Y
	}
}
