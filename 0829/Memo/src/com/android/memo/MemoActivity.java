package com.android.memo;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MemoActivity extends Activity implements OnClickListener {

	//�G���[���b�Z�[�W
	public static String err_msg;
	
	//���������ݒ�
	public static final String err_moji = "��";
	
	//�󕶎��`�F�b�N
	public static final String blank_str = "";
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        
    	//Activity�N���X��onCreate���Ăяo��
    	super.onCreate(savedInstanceState);
        
        //Memo���C�A�E�g�t�@�C�����w��
        setContentView(R.layout.layout_memo);
        
        //Button�I�u�W�F�N�g�擾
        Button button = (Button)findViewById(R.id.savebutton);
        
        //Button�I�u�W�F�N�g�ɃN���b�N���X�i�[��ݒ�
        button.setOnClickListener(this);
    }

    //�N���b�N����
	@Override
	public void onClick(View view) {
		
		//�����I�u�W�F�N�g�擾
		EditText subject_obj = (EditText)findViewById(R.id.subject);
		String subject = subject_obj.getText().toString();
		
		//�{���I�u�W�F�N�g�擾
		EditText body_obj = (EditText)findViewById(R.id.body);
		String body = body_obj.getText().toString();
		
		//���s�`�ԃI�u�W�F�N�g�擾
		RadioGroup exec = (RadioGroup)findViewById(R.id.exec_group);
		
		//�K�{���̓`�F�b�N���\�b�h��
		if(!itemChk(subject,body,exec)){
			msgView(err_msg);	//�G���[���b�Z�[�W�\��������
			return ;			//�����I��
		}
		
		//���W�I�{�^���擾
		RadioButton exec_button = (RadioButton)findViewById(exec.getCheckedRadioButtonId());
		
		
		//�I�����b�Z�[�W������
		String msg;
		
		//�I��ʏ���
		switch(exec_button.getId()){
		case R.id.save:
			
			saveMemo(subject,body);			//�ۑ�����
			
			msg = "�ۑ��������������܂����B";		//�I������
			msgView(msg);										
			break;
			
		case R.id.sms:
			
			saveMemo(subject,body);							//�ۑ�����
			
			msg = "�ۑ��������������܂����BE���[�����N�����܂��B";		//�I������
			msgView(msg);										
			emailMemo(subject,body);						//E-MAIL�N������
			break;
		}
	}
	
	//�ۑ�����
	public void saveMemo(String subject, String body){
		
		//�����o������
		try{
			//�t�@�C�����w��
			FileOutputStream stream = openFileOutput("MEMO.csv",MODE_APPEND);
			
			//�o�b�t�@�̈�m��
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(stream));
			
			//�i�[
			String[] items = {
					subject,
					body
			};
			
			//�������ݏ���
			for(int i = 0; i < items.length; i++){
				
				//�J���}�����`�F�b�N
				if(items[i].indexOf(",") != -1){
					items[i].replace(",","��");
				}
				out.write(items[i]);			//��������
				if((i + 1) == items.length){
					out.newLine();
				}else{
					out.write(",");
				}
			}
				
			//�o�b�t�@�̈���
			out.close();
			
		}catch(Exception e){
			err_msg = "Error:�������ݏ����Ɏ��s���܂����B";
			msgView(err_msg);			//�G���[���b�Z�[�W�\��������
		}	
	}
	
	//SMS�N������
	public void emailMemo(String subject, String body){
		
		//�C���e���g����
		Intent intent = new Intent("android.intent.action.SENDTO",Uri.parse("mailto:"));
		
		//�t�����ݒ�
		intent.putExtra(Intent.EXTRA_SUBJECT, subject);		//����
		intent.putExtra(Intent.EXTRA_TEXT, body);			//�{��
		
		//�A�N�e�B�r�e�B���s
		startActivity(intent);
	}
	
	//�K�{���̓`�F�b�N
	public boolean itemChk(String subject, String body, RadioGroup exec){
		
		//�t���O�ϐ�
		boolean itemChk;
		
		//�����A�{���I�u�W�F�N�g�`�F�b�N
		if(blank_str.equals(subject.trim())){
			err_msg = "Error:�����������͂ł��B";
			itemChk = false;
			return itemChk;
		}else if(subject.indexOf(err_moji) != -1){
			err_msg = "Error:�{���ɖ��������F" + err_moji + "�����͂���Ă��܂��B";
			itemChk = false;
			return itemChk;
		}
		
		//�{���I�u�W�F�N�g�`�F�b�N
		if(body.trim().equals(blank_str)){
			err_msg = "Error:�{���������͂ł��B";
			itemChk = false;
			return itemChk;
		}else if(body.indexOf(err_moji) != -1){
			err_msg = "Error:�{���ɖ��������F" + err_moji + "�����͂���Ă��܂��B";
			itemChk = false;
			return itemChk;
		}

		//���s�`�ԃ{�^���`�F�b�N
		if(exec.getCheckedRadioButtonId() == -1){
			err_msg = "Error:���s���[�h�����I���ł��B";
			itemChk = false;
			return itemChk;
		}
		
		itemChk = true;
		return itemChk;
	}
	
	//���b�Z�[�W�\������
	public void msgView(String msg_txt){
		
		//���b�Z�[�W�\���ݒ�
		Toast msg = Toast.makeText(this, msg_txt, Toast.LENGTH_LONG);
		msg.setGravity(Gravity.CENTER, 0, 0);
		
		//���b�Z�[�W�\��
		msg.show();
	}
}
