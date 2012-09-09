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

	//DialogSampleActivityオブジェクト
	public DialogSampleActivity active;
	
	//TextViewオブジェクト
	public TextView msg;
	
	//コンストラクタ(各オブジェクト初期化)
	public DialogShow(DialogSampleActivity active, TextView msg){
		this.active = active;
		this.msg = msg;
	}
	
	//通常ダイアログ表示
	public void showDialog(){
		AlertDialog.Builder dialog = new AlertDialog.Builder(active);
		dialog.setTitle("通常ダイアログ");
		dialog.setMessage("選択してください。");
		
		//無名クラス
		dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				msg.setText("通常ダイアログ:OKが選択されました。");
			}
		});
		
		//無名クラス
		dialog.setNegativeButton("NG", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				msg.setText("通常ダイアログ:NGが選択されました。");
			}
		});
		
		//ダイアログ表示
		dialog.show();
	}
	
	//テキストダイアログ表示
	public void showTextDialog(){
		final EditText edittext = new EditText(active);
		AlertDialog.Builder dialog = new AlertDialog.Builder(active);
		dialog.setTitle("テキストダイアログ");
		dialog.setMessage("テキストを入力してください。");
		dialog.setView(edittext);
		
		//無名クラス
		dialog.setPositiveButton("OK", new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which){
				msg.setText("テキストダイアログ：" + edittext.getText().toString() + "が入力されました。");
			}
		});
		
		//ダイアログ表示
		dialog.show();
	}
	
	//単一選択ダイアログの表示
	//選択肢
	final String[] items = new String[]{"もも","うめ","さくら"};
	Integer which = 0;
	public void showSingleDialog(){
		AlertDialog.Builder dialog = new AlertDialog.Builder(active);
		dialog.setTitle("単一選択ダイアログ");
		
		//無名クラス
		dialog.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int whichButton) {
				which = whichButton;
			}
		});
		
		//無名クラス
		dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int whichButton) {
				String selected = items[which];
				msg.setText("単一選択ダイアログ：" + selected + "が入力されました。");
			}
		});
		
		//ダイアログ表示
		dialog.show();
	}
	
	//日付選択ダイアログの表示
	public void showDatePickerDialog(){
		Calendar cal = Calendar.getInstance();
		
		//無名クラス
		DatePickerDialog dialog = new DatePickerDialog(active, new DatePickerDialog.OnDateSetListener(){
			@Override
			public void onDateSet(DatePicker picker, int year, int month, int day){
				msg.setText("日付選択ダイアログ：" + year + "年" + (month+1) + "月" + day + "日");
			}
		}
		,cal.get(Calendar.YEAR)
		,cal.get(Calendar.MONTH)
		,cal.get(Calendar.DAY_OF_MONTH)
				
		);
		
		//ダイアログ表示
		dialog.show();
	}
	
	//時間選択ダイアログの表示
	public void showTimePickerDialog(){
		TimePickerDialog dialog = new TimePickerDialog(active, new TimePickerDialog.OnTimeSetListener(){
			@Override
			public void onTimeSet(TimePicker picker, int hour, int min){
				msg.setText("時間選択ダイアログ：" + hour + "時" + min + "分");
			}
		},0,0,true);
		
		//ダイアログ表示
		dialog.show();
	}
	
	//プログレスバーダイアログの表示
	ProgressDialog dialog;
	public void showProgressDialog(){
		dialog = new ProgressDialog(active);
		dialog.setTitle("プログレスバーダイアログ");
		dialog.setMessage("しばらくお待ちください・・・");
		dialog.setIndeterminate(false);
		dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		//dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		dialog.setMax(100);
		dialog.setCancelable(false);
		
		//無名クラス
		dialog.setOnCancelListener(new DialogInterface.OnCancelListener(){
			@Override
			public void onCancel(DialogInterface dialog){		
			}
		});
		
		//ダイアログ表示
		dialog.show();
		
		//スレッド(無名クラス)
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
