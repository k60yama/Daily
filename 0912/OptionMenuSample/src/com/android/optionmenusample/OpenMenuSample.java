package com.android.optionmenusample;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
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
        
        //���j���[�A�C�e���S�̒ǉ�
        MenuItem item4 = menu.add(0,3,0,"item4");
        item4.setIcon(android.R.drawable.ic_menu_call);
        
        //���j���[�A�C�e���T�̒ǉ�
        MenuItem item5 = menu.add(0,4,0,"item5");
        item5.setIcon(android.R.drawable.ic_menu_camera);
        
        //���j���[�A�C�e���U�̒ǉ�
        SubMenu item6 = menu.addSubMenu(0,5,0,"���̑�");
        item6.setIcon(android.R.drawable.ic_menu_more);
        item6.add(0,10,0,"subitem1");
        item6.add(0,20,0,"subitem2");
        
        return true; 
    }

    
    //���j���[�A�C�e���I������
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
    	
    	//�_�C�A���O�ɕ\�����郁�b�Z�[�W������
    	String msg = "";
    	
    	switch(item.getItemId()){
    	case 0:
    		showMsg("���j���[�A�C�e��1��I�����܂����B");
    		break;
    	case 1:
    		showMsg("���j���[�A�C�e��2��I�����܂����B");
    		break;
    	case 2:
    		showMsg("���j���[�A�C�e��3��I�����܂����B");
    		break;
    	case 3:
    		showMsg("���j���[�A�C�e��4��I�����܂����B");
    		break;
    	case 4:
    		showMsg("���j���[�A�C�e��5��I�����܂����B");
    		break;
    	case 10:
    		showMsg("�T�u���j���[�A�C�e��1��I�����܂����B");
    		break;
    	case 20:
    		showMsg("�T�u���j���[�A�C�e��2��I�����܂����B");
    		break;
    	}
    	
    	return true;
    }    
    
    //�_�C�A���O�\��(show()�̏ꍇ)
    public void showMsg(String msg){
    	
    	//�_�C�A���O�̃C���X�^���X����
    	AlertDialog.Builder dialog = new AlertDialog.Builder(OpenMenuSample.this);
    	
    	//�_�C�A���O�ݒ�
    	dialog.setTitle("���j���[�A�C�e���I������");
    	dialog.setMessage(msg);
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
