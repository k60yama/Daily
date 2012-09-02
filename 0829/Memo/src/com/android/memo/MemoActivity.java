package com.android.memo;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class MemoActivity extends Activity implements OnClickListener,OnCheckedChangeListener {

	//�G���[���b�Z�[�W
	private static String err_msg;
	
	//���������ݒ�
	public static final String err_moji = "��";
	
	//�󕶎��`�F�b�N
	public static final String blank_str = "";
	
	//�t�@�C����
	public static final String FILE_NAME = "MEMO.csv";
	
	//���s���[�h�I�u�W�F�N�g
	private RadioGroup exec;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        
    	//Activity�N���X��onCreate���Ăяo��
    	super.onCreate(savedInstanceState);
        
        //Memo���C�A�E�g�t�@�C�����w��
        setContentView(R.layout.layout_memo);
        
        //Button�I�u�W�F�N�g�擾���A�N���b�N���X�i�[��ݒ�
        Button save = (Button)findViewById(R.id.savebutton);
        save.setOnClickListener(this);
        
        //Button�I�u�W�F�N�g�擾���A�N���b�N���X�i�[��ݒ�
        Button close = (Button)findViewById(R.id.closebutton);
        close.setOnClickListener(this);
        
        //RadioGroup�I�u�W�F�N�g�擾
        exec = (RadioGroup)findViewById(R.id.exec_group);
        
        //RadioGroup�I�u�W�F�N�g�Ƀ`�F�b�N���X�i�[��ݒ�
        exec.setOnCheckedChangeListener(this);
    }

    //�N���b�N����
	@Override
	public void onClick(View view) {
		
		//�����{�^���m�F
		if(view.getId() == R.id.closebutton){
			
			//�A�N�e�B�r�e�B�I��
			finish();
			
			//�����I��
			return ;
		}
		
	    //�����I�u�W�F�N�g�擾
		EditText subject_obj = (EditText)findViewById(R.id.subject);
		String subject = subject_obj.getText().toString();
		
		//�{���I�u�W�F�N�g�擾
		EditText body_obj = (EditText)findViewById(R.id.body);
		String body = body_obj.getText().toString();
		
		//�K�{���̓`�F�b�N���\�b�h��
		if(!itemChk(subject,body,exec)){
			
			//�G���[���b�Z�[�W�\��������
			msgView(err_msg);
			
			//�����I��
			return ;		
		}
		
		//���W�I�{�^���擾
		RadioButton exec_button = (RadioButton)findViewById(exec.getCheckedRadioButtonId());
		
		//�I�����b�Z�[�W������
		String msg = "";
		
		//�I��ʏ���
		switch(exec_button.getId()){
		case R.id.save:
			
			//�ۑ�����
			saveMemo(subject,body);			
			
			//�I�����b�Z�[�W�\��
			msg = "�ۑ��������������܂����B";
			msgView(msg);
			finish();
			break;
			
		case R.id.email:
			
			//�ۑ�����
			saveMemo(subject,body);							
			
			//�I�����b�Z�[�W�\��
			msg = "�ۑ��������������܂����BE���[�����N�����܂��B";
			msgView(msg);
			
			//E-MAIL�N������
			emailMemo(subject,body);
			break;
		}
	}
	
	//�ۑ�����
	private void saveMemo(String subject, String body){
		
		//�����o������
		try{
			//�t�@�C�����w��
			FileOutputStream stream = openFileOutput(FILE_NAME,MODE_APPEND);
			
			//�o�b�t�@�̈�m��
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(stream));
			
			//�ۑ������ݒ�
			SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			//�f�[�^�i�[
			String[] items = {
					date.format(new Date()),
					subject,
					body
			};
			
			//�������ݏ���
			for(int i = 0; i < items.length; i++){
				
				//�J���}�����`�F�b�N
				if(items[i].indexOf(",") != -1){
					items[i] = items[i].replace(",",err_moji);
				}
				
				//�t�@�C����������
				out.write(items[i]);
				
				//���sor�J���}�}��
				if((i + 1) == items.length){
					out.newLine();
				}else{
					out.write(",");
				}
			}
				
			//�o�b�t�@�̈���
			out.close();
			
		}catch(Exception e){
			
			//�G���[���b�Z�[�W�\��������
			err_msg = "Error:�������ݏ����Ɏ��s���܂����B";
			msgView(err_msg);			
		}	
	}
	
	//E-MAIL�N������
	private void emailMemo(String subject, String body){
		
		//����I�u�W�F�N�g�擾
		EditText sendto = (EditText)findViewById(R.id.sendto);
		
		//URI�ݒ�(����)
		Uri uri = Uri.parse("mailto:" + sendto.getText().toString());
		
		//�C���e���g����
		Intent intent = new Intent("android.intent.action.SENDTO",uri);
		
		//�t�����ݒ�
		intent.putExtra(Intent.EXTRA_SUBJECT, subject);		//����
		intent.putExtra(Intent.EXTRA_TEXT, body);			//�{��
		
		//�A�N�e�B�r�e�B���s
		startActivity(intent);
	}
	
	//�K�{���̓`�F�b�N
	private boolean itemChk(String subject, String body, RadioGroup exec){
		
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

		//���s���[�h�`�F�b�N
		if(exec.getCheckedRadioButtonId() == -1){
			err_msg = "Error:���s���[�h�����I���ł��B";
			itemChk = false;
			return itemChk;
		}
		
		itemChk = true;
		return itemChk;
	}
	
	//���b�Z�[�W�\������
	private void msgView(String msg_txt){
		
		//���b�Z�[�W�\���ݒ�
		Toast msg = Toast.makeText(this, msg_txt, Toast.LENGTH_LONG);
		msg.setGravity(Gravity.CENTER, 0, 0);
		
		//���b�Z�[�W�\��
		msg.show();
	}

	@Override
	//����I�u�W�F�N�g�\���E��\������
	public void onCheckedChanged(RadioGroup rGrp, int rid) {
		
		//����I�u�W�F�N�g�擾
		View[] sendto_obj = {
			(TextView)findViewById(R.id.sendto_label),
			(EditText)findViewById(R.id.sendto)
		};
		
		//�\���E��\������
		for(int i = 0; i < sendto_obj.length; i++){
			if(rid == R.id.save){
				sendto_obj[i].setVisibility(View.GONE);
			}else if(rid == R.id.email){
				sendto_obj[i].setVisibility(View.VISIBLE);
			}
		}
	}
}
