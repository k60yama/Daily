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

	//エラーメッセージ
	private static String err_msg;
	
	//無効文字設定
	public static final String err_moji = "■";
	
	//空文字チェック
	public static final String blank_str = "";
	
	//ファイル名
	public static final String FILE_NAME = "MEMO.csv";
	
	//実行モードオブジェクト
	private RadioGroup exec;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        
    	//ActivityクラスのonCreateを呼び出し
    	super.onCreate(savedInstanceState);
        
        //Memoレイアウトファイルを指定
        setContentView(R.layout.layout_memo);
        
        //Buttonオブジェクト取得し、クリックリスナーを設定
        Button save = (Button)findViewById(R.id.savebutton);
        save.setOnClickListener(this);
        
        //Buttonオブジェクト取得し、クリックリスナーを設定
        Button close = (Button)findViewById(R.id.closebutton);
        close.setOnClickListener(this);
        
        //RadioGroupオブジェクト取得
        exec = (RadioGroup)findViewById(R.id.exec_group);
        
        //RadioGroupオブジェクトにチェックリスナーを設定
        exec.setOnCheckedChangeListener(this);
    }

    //クリック処理
	@Override
	public void onClick(View view) {
		
		//押下ボタン確認
		if(view.getId() == R.id.closebutton){
			
			//アクティビティ終了
			finish();
			
			//強制終了
			return ;
		}
		
	    //件名オブジェクト取得
		EditText subject_obj = (EditText)findViewById(R.id.subject);
		String subject = subject_obj.getText().toString();
		
		//本文オブジェクト取得
		EditText body_obj = (EditText)findViewById(R.id.body);
		String body = body_obj.getText().toString();
		
		//必須入力チェックメソッドへ
		if(!itemChk(subject,body,exec)){
			
			//エラーメッセージ表示処理へ
			msgView(err_msg);
			
			//強制終了
			return ;		
		}
		
		//ラジオボタン取得
		RadioButton exec_button = (RadioButton)findViewById(exec.getCheckedRadioButtonId());
		
		//終了メッセージ初期化
		String msg = "";
		
		//選択別処理
		switch(exec_button.getId()){
		case R.id.save:
			
			//保存処理
			saveMemo(subject,body);			
			
			//終了メッセージ表示
			msg = "保存処理が成功しました。";
			msgView(msg);
			finish();
			break;
			
		case R.id.email:
			
			//保存処理
			saveMemo(subject,body);							
			
			//終了メッセージ表示
			msg = "保存処理が成功しました。Eメールを起動します。";
			msgView(msg);
			
			//E-MAIL起動処理
			emailMemo(subject,body);
			break;
		}
	}
	
	//保存処理
	private void saveMemo(String subject, String body){
		
		//書き出し処理
		try{
			//ファイル名指定
			FileOutputStream stream = openFileOutput(FILE_NAME,MODE_APPEND);
			
			//バッファ領域確保
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(stream));
			
			//保存日時設定
			SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			//データ格納
			String[] items = {
					date.format(new Date()),
					subject,
					body
			};
			
			//書き込み処理
			for(int i = 0; i < items.length; i++){
				
				//カンマ混入チェック
				if(items[i].indexOf(",") != -1){
					items[i] = items[i].replace(",",err_moji);
				}
				
				//ファイル書き込み
				out.write(items[i]);
				
				//改行orカンマ挿入
				if((i + 1) == items.length){
					out.newLine();
				}else{
					out.write(",");
				}
			}
				
			//バッファ領域解放
			out.close();
			
		}catch(Exception e){
			
			//エラーメッセージ表示処理へ
			err_msg = "Error:書き込み処理に失敗しました。";
			msgView(err_msg);			
		}	
	}
	
	//E-MAIL起動処理
	private void emailMemo(String subject, String body){
		
		//宛先オブジェクト取得
		EditText sendto = (EditText)findViewById(R.id.sendto);
		
		//URI設定(宛先)
		Uri uri = Uri.parse("mailto:" + sendto.getText().toString());
		
		//インテント生成
		Intent intent = new Intent("android.intent.action.SENDTO",uri);
		
		//付属情報設定
		intent.putExtra(Intent.EXTRA_SUBJECT, subject);		//件名
		intent.putExtra(Intent.EXTRA_TEXT, body);			//本文
		
		//アクティビティ実行
		startActivity(intent);
	}
	
	//必須入力チェック
	private boolean itemChk(String subject, String body, RadioGroup exec){
		
		//フラグ変数
		boolean itemChk;
		
		//件名、本文オブジェクトチェック
		if(blank_str.equals(subject.trim())){
			err_msg = "Error:件名が未入力です。";
			itemChk = false;
			return itemChk;
		}else if(subject.indexOf(err_moji) != -1){
			err_msg = "Error:本文に無効文字：" + err_moji + "が入力されています。";
			itemChk = false;
			return itemChk;
		}
		
		//本文オブジェクトチェック
		if(body.trim().equals(blank_str)){
			err_msg = "Error:本文が未入力です。";
			itemChk = false;
			return itemChk;
		}else if(body.indexOf(err_moji) != -1){
			err_msg = "Error:本文に無効文字：" + err_moji + "が入力されています。";
			itemChk = false;
			return itemChk;
		}

		//実行モードチェック
		if(exec.getCheckedRadioButtonId() == -1){
			err_msg = "Error:実行モードが未選択です。";
			itemChk = false;
			return itemChk;
		}
		
		itemChk = true;
		return itemChk;
	}
	
	//メッセージ表示処理
	private void msgView(String msg_txt){
		
		//メッセージ表示設定
		Toast msg = Toast.makeText(this, msg_txt, Toast.LENGTH_LONG);
		msg.setGravity(Gravity.CENTER, 0, 0);
		
		//メッセージ表示
		msg.show();
	}

	@Override
	//宛先オブジェクト表示・非表示制御
	public void onCheckedChanged(RadioGroup rGrp, int rid) {
		
		//宛先オブジェクト取得
		View[] sendto_obj = {
			(TextView)findViewById(R.id.sendto_label),
			(EditText)findViewById(R.id.sendto)
		};
		
		//表示・非表示制御
		for(int i = 0; i < sendto_obj.length; i++){
			if(rid == R.id.save){
				sendto_obj[i].setVisibility(View.GONE);
			}else if(rid == R.id.email){
				sendto_obj[i].setVisibility(View.VISIBLE);
			}
		}
	}
}
