package com.android.dialogsample;

import android.os.Bundle;
import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.view.View;

public class DialogSampleActivity extends Activity implements OnClickListener {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	//Activity�N���X��onCreate�����s
        super.onCreate(savedInstanceState);
        
        //���C�A�E�g�ݒ�t�@�C�����w��
        setContentView(R.layout.dialoglayout);
        
        //Button�I�u�W�F�N�g���擾
        Button[] button = {
        		(Button)findViewById(R.id.button1),
        		(Button)findViewById(R.id.button2),
        		(Button)findViewById(R.id.button3),
        		(Button)findViewById(R.id.button4),
        		(Button)findViewById(R.id.button5),
        		(Button)findViewById(R.id.button6),
        };
        
        //Button�I�u�W�F�N�g�N���b�N���X�i�[�ݒ�
        for(int i=0; i<button.length; i++){
        	button[i].setOnClickListener(this);
        }
    }

    @Override
    //�{�^���������̃��\�b�h
    public void onClick(View view){
    	
    	//TextView�I�u�W�F�N�g�擾
    	TextView msg = (TextView)findViewById(R.id.msg);
    	
    	//DialogShow�N���X�̃C���X�^���X����
    	DialogShow dialog = new DialogShow(this,msg);
    	
    	//�����ʏ���
    	switch(view.getId()){
    	case R.id.button1:
    		//�ʏ�_�C�A���O�\��
    		dialog.showDialog();
    		break;
    	case R.id.button2:
    		//�e�L�X�g�_�C�A���O�\��
    		dialog.showTextDialog();
    		break;
    	case R.id.button3:
    		//�P��I�����O�\��
    		dialog.showSingleDialog();
    		break;
    	case R.id.button4:
    		//���t�I���_�C�A���O�\��
    		dialog.showDatePickerDialog();
    		break;
    	case R.id.button5:
    		//���ԑI���_�C�A���O�\��
    		dialog.showTimePickerDialog();
    		break;
    	case R.id.button6:
    		//�v���O���X�o�[�_�C�A���O�\��
    		dialog.showProgressDialog();
    		break;
    	}
    }
}
