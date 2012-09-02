package com.android.memo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MemoList extends Activity {
	
	//TextView�I�u�W�F�N�g
	TextView view;
	
	//�G���[���b�Z�[�W
	public static String msg;
	
	public void onCreate(Bundle savedInstanceState){
		
		//Activity�N���X��OnCreate�����s
		super.onCreate(savedInstanceState);
		
		//���C�A�E�g�ݒ�t�@�C�����w��
		setContentView(R.layout.list_memo);
		
		//TextView�I�u�W�F�N�g�擾
		view = (TextView)findViewById(R.id.warning);
		
		//�t�@�C���`�F�b�N
		if(!fileChk()){
			view.setVisibility(View.VISIBLE);
			view.setText(msg);
			return ;	//�����I��
		}
		
		//ListView�I�u�W�F�N�g���擾
        ListView list = (ListView)findViewById(R.id.list);
        
        //SimpleAdapter�̃C���X�^���X����
        SimpleAdapter adapter = new SimpleAdapter(
        		this,
        		fileRead(),
        		android.R.layout.simple_list_item_2,
        		new String[]{"subject","date"},
        		new int[]{android.R.id.text1, android.R.id.text2});
        
        //���X�g�\��
        list.setAdapter(adapter);
	}
	
	//�t�@�C���ǂݍ���
	public ArrayList<HashMap<String,String>> fileRead(){
		
		try{
			
			//�t�@�C�����w��
			FileInputStream stream = openFileInput(MemoActivity.FILE_NAME);
			
			//�o�b�t�@�̈�m��
			BufferedReader in = new BufferedReader(new InputStreamReader(stream));
			
			//�t�@�C�����e�i�[
			String[] str = {};
			
			//������i�[
			String line = "";
			
			//���X�g�\���p
			ArrayList<HashMap<String,String>> listData = new ArrayList<HashMap<String,String>>();
			
			while((line = in.readLine()) != null){
				
				//�J���}��؂�ŕ\��
				str = line.split(",");
				
				listData.add(getMapData(new String[][]{{"date","�쐬�����F" + str[0]}, {"subject",str[1]}}));			
			}
			
			return listData;
			
		}catch(Exception e){
			
			Toast.makeText(this, "�t�@�C���ǂݍ��݂Ŏ��s���܂����I", Toast.LENGTH_SHORT).show();
			return null;
		}
		
	}
	
	private HashMap<String,String> getMapData(String[][] values){
    	
    	//HashMap�̃C���X�^���X�𐶐�
    	HashMap<String,String> map = new HashMap<String,String>();
    	
    	//�L�[�ƒl�����蓖��
    	for(int i = 0; i<values.length; i++){
    		map.put(values[i][0], values[i][1]);
    	}
    	
    	return map;
    }
	
	//�t�@�C���`�F�b�N���\�b�h
	public boolean fileChk(){
		
		//�t�@�C���擾
		File file = this.getFileStreamPath(MemoActivity.FILE_NAME);
		
		//�t�@�C���L���`�F�b�N
		if(!file.exists()){
			msg = "�t�@�C����������܂���I";
			return false;
		}
		
		//�t�@�C���T�C�Y�`�F�b�N
		if(file.length() == 0){
			msg = "�t�@�C���T�C�Y���O�o�C�g�ł��B";
			return false;
		}
		
		return true;
	}
}
