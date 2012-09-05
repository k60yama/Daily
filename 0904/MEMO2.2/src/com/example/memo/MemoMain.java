package com.example.memo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MemoMain extends Activity implements OnItemClickListener{

	@Override
	public void onCreate(Bundle savedInstanceState){
		
		//Activity�N���X��onCreate�����s
		super.onCreate(savedInstanceState);
		
		//���C�A�E�g�ݒ�t�@�C���̎w��
		setContentView(R.layout.main_memo);
		
		//ListView�I�u�W�F�N�g�擾
		ListView listview = (ListView)findViewById(R.id.mainlist);
		
		//ListView�I�u�W�F�N�g�ɃN���b�N���X�i�[��ݒ�
		listview.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
		//�N���b�N����ListView�I�u�W�F�N�g�擾
		ListView listview = (ListView)parent;
		
		//�I�����ꂽ�l�擾
		String item = (String)listview.getItemAtPosition(position);
		
		//�����ʃC���e���g�N��
		if(item.equals("�V�K�����쐬")){
			newMemo();
		}else if(item.equals("�쐬�����ꗗ")){
			memosView();
		}
	}
	
	public void newMemo(){
		
		//�C���e���g����
		Intent intent = new Intent(this,MemoActivity.class);
		
		//�A�N�e�B�r�e�B�N��
		startActivity(intent);
	}
	
	public void memosView(){
		
		//�C���e���g����
		Intent intent = new Intent(this,MemoList.class);
		
		//�A�N�e�B�r�e�B�N��
		startActivity(intent);		
	}
}
