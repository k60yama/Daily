package com.example.contentprovidersample1;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;

public class SQLmethod {

	//�C���X�^���X�ϐ��錾
	public SQLiteDatabase db;
	
	//�R���X�g���N�^
	public SQLmethod(SQLiteDatabase db ){
		this.db = db;
	}
	
	//�f�[�^�o�^�������\�b�h
	public String dataRegistry(EditText[] value){
		try{
			//�g�����U�N�V��������J�n
			db.beginTransaction();
			
			//�o�^�f�[�^�ݒ�
			ContentValues val = new ContentValues();
			val.put("productid", value[0].getText().toString());
			val.put("name", value[1].getText().toString());
			val.put("price", value[2].getText().toString());
			
			//�f�[�^�o�^
			db.insert("item", null, val);
			
			//�R�~�b�g
			db.setTransactionSuccessful();
			
			//�g�����U�N�V��������I��
			db.endTransaction();
			
			return "�f�[�^��o�^���܂����B";
		}catch(Exception e){
			return "�f�[�^�o�^�Ɏ��s���܂����B";
		}
	}
	
	//�f�[�^�X�V�������\�b�h
	public String dataUpdate(EditText[] value){
		try{
			//�g�����U�N�V��������J�n
			db.beginTransaction();
			
			//�X�V�f�[�^�ݒ�
			ContentValues val = new ContentValues();
			val.put("name", value[1].getText().toString());
			val.put("price",value[2].getText().toString());
			
			//�f�[�^�X�V
			db.update("item", val, setCondition(value[0]), null);
			
			//�R�~�b�g����
			db.setTransactionSuccessful();
			
			//�g�����U�N�V��������I��
			db.endTransaction();
			
			return "�f�[�^���X�V���܂����B";
		}catch(Exception e){
			return "�f�[�^�X�V�Ɏ��s���܂����B";
		}
	}
	
	//�f�[�^�폜����
	public String dataDelete(EditText[] value){
		try{
			//�g�����U�N�V��������J�n
			db.beginTransaction();
			
			//�f�[�^�폜
			db.delete("item", setCondition(value[0]), null);
			
			//�R�~�b�g����
			db.setTransactionSuccessful();
			
			//�g�����U�N�V��������I��
			db.endTransaction();
			
			return "�f�[�^���폜���܂����B";
		}catch(Exception e){
			return "�f�[�^�폜�Ɏ��s���܂����B";
		}
	}
	
	//���������ݒ胁�\�b�h
	private String setCondition(EditText value){
		
		//��������
		String condition = null;		//������
		
		if(value != null && !(value.equals(""))){
			condition = "productid ='" + value.getText().toString() + "'";
		}
		
		return condition;
	}
	
}
