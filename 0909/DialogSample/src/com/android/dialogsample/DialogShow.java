package com.android.dialogsample;

import java.util.Calendar;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

public class DialogShow {

	//DialogSampleActivity�I�u�W�F�N�g
	public DialogSampleActivity active;
	
	//TextView�I�u�W�F�N�g
	public TextView msg;
	
	//�R���X�g���N�^(�e�I�u�W�F�N�g������)
	public DialogShow(DialogSampleActivity active, TextView msg){
		this.active = active;
		this.msg = msg;
	}
	
	//�ʏ�_�C�A���O�\��
	public void showDialog(){
		AlertDialog.Builder dialog = new AlertDialog.Builder(active);
		dialog.setTitle("�ʏ�_�C�A���O");
		dialog.setMessage("�I�����Ă��������B");
		
		//�����N���X
		dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				msg.setText("�ʏ�_�C�A���O:OK���I������܂����B");
			}
		});
		
		//�����N���X
		dialog.setNegativeButton("NG", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				msg.setText("�ʏ�_�C�A���O:NG���I������܂����B");
			}
		});
		
		//�_�C�A���O�\��
		dialog.show();
	}
	
	//�e�L�X�g�_�C�A���O�\��
	public void showTextDialog(){
		final EditText edittext = new EditText(active);
		AlertDialog.Builder dialog = new AlertDialog.Builder(active);
		dialog.setTitle("�e�L�X�g�_�C�A���O");
		dialog.setMessage("�e�L�X�g����͂��Ă��������B");
		dialog.setView(edittext);
		
		//�����N���X
		dialog.setPositiveButton("OK", new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which){
				msg.setText("�e�L�X�g�_�C�A���O�F" + edittext.getText().toString() + "�����͂���܂����B");
			}
		});
		
		//�_�C�A���O�\��
		dialog.show();
	}
	
	//�P��I���_�C�A���O�̕\��
	//�I����
	final String[] items = new String[]{"����","����","������"};
	Integer which = 0;
	public void showSingleDialog(){
		AlertDialog.Builder dialog = new AlertDialog.Builder(active);
		dialog.setTitle("�P��I���_�C�A���O");
		
		//�����N���X
		dialog.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int whichButton) {
				which = whichButton;
			}
		});
		
		//�����N���X
		dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int whichButton) {
				String selected = items[which];
				msg.setText("�P��I���_�C�A���O�F" + selected + "�����͂���܂����B");
			}
		});
		
		//�_�C�A���O�\��
		dialog.show();
	}
	
	//���t�I���_�C�A���O�̕\��
	public void showDatePickerDialog(){
		Calendar cal = Calendar.getInstance();
		
		//�����N���X
		DatePickerDialog dialog = new DatePickerDialog(active, new DatePickerDialog.OnDateSetListener(){
			@Override
			public void onDateSet(DatePicker picker, int year, int month, int day){
				msg.setText("���t�I���_�C�A���O�F" + year + "�N" + (month+1) + "��" + day + "��");
			}
		}
		,cal.get(Calendar.YEAR)
		,cal.get(Calendar.MONTH)
		,cal.get(Calendar.DAY_OF_MONTH)
				
		);
		
		//�_�C�A���O�\��
		dialog.show();
	}
	
	//���ԑI���_�C�A���O�̕\��
	public void showTimePickerDialog(){
		TimePickerDialog dialog = new TimePickerDialog(active, new TimePickerDialog.OnTimeSetListener(){
			@Override
			public void onTimeSet(TimePicker picker, int hour, int min){
				msg.setText("���ԑI���_�C�A���O�F" + hour + "��" + min + "��");
			}
		},0,0,true);
		
		//�_�C�A���O�\��
		dialog.show();
	}
	
	//�v���O���X�o�[�_�C�A���O�̕\��
	ProgressDialog dialog;
	public void showProgressDialog(){
		dialog = new ProgressDialog(active);
		dialog.setTitle("�v���O���X�o�[�_�C�A���O");
		dialog.setMessage("���΂炭���҂����������E�E�E");
		dialog.setIndeterminate(false);
		dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		//dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		dialog.setMax(100);
		dialog.setCancelable(false);
		
		//�����N���X
		dialog.setOnCancelListener(new DialogInterface.OnCancelListener(){
			@Override
			public void onCancel(DialogInterface dialog){		
			}
		});
		
		//�_�C�A���O�\��
		dialog.show();
		
		//�X���b�h(�����N���X)
		new Thread(new Runnable(){
			public void run(){
				try{
					for(int i=0; i<dialog.getMax(); i++){
						dialog.setProgress(i);
						Thread.sleep(100);
					}
				}catch(Exception e){
					
				}
				dialog.dismiss();
			}
		}).start();
	}
}
