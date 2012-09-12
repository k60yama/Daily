package com.android.optionmenusample;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;

public class OpenMenuSample extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	//Activity�N���X��onCreate�����s
        super.onCreate(savedInstanceState);
        
        //���C�A�E�g�ݒ�t�@�C���̎w��
        setContentView(R.layout.optionmenusample);
    }

    //�I�v�V�������j���[����
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	
    	//Activity�N���X��onCreateOptionMenu�����s
    	super.onCreateOptionsMenu(menu);
    	
    	//���j���[�A�C�e���P�̒ǉ�
    	MenuItem item1 = menu.add(0,0,0,"item1");
    	
    	//���j���[�A�C�e���Q�̒ǉ�
    	MenuItem item2 = menu.add(0,1,0,"item2");
    	item2.setIcon(android.R.drawable.ic_menu_search);
    	
    	//���j���[�A�C�e���R�̒ǉ�
    	MenuItem item3 = menu.add(0,2,0,"item3");
    	item3.setIcon(android.R.drawable.ic_menu_save);
        return true;
    }

    
    //���j���[�A�C�e���I������
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
    	showMsg(item.getItemId() + 1);
    	return true;
    }    
    
    //�_�C�A���O�\��(show()�̏ꍇ)
    public void showMsg(Integer position){
    	
    	//�_�C�A���O�̃C���X�^���X����
    	AlertDialog.Builder dialog = new AlertDialog.Builder(OpenMenuSample.this);
    	
    	//�_�C�A���O�ݒ�
    	dialog.setTitle("���j���[�A�C�e���I������");
    	dialog.setMessage("���j���[�A�C�e��" + position + "��I�����܂����B");
    	dialog.setPositiveButton("OK", new DialogInterface.OnClickListener(){
    		@Override
    		public void onClick(DialogInterface dialog, int whichButton){
    			OpenMenuSample.this.setResult(Activity.RESULT_OK);
    		}
    	});
    	
    	//�_�C�A���O�쐬
    	dialog.create();
    	
    	//�_�C�A���O�\��
    	dialog.show();
    }
   
}
