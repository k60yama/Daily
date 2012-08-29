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

	//エラーメッセージ
	public static String err_msg;
	
	//無効文字設定
	public static final String err_moji = "■";
	
	//空文字チェック
	public static final String blank_str = "";
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        
    	//ActivityクラスのonCreateを呼び出し
    	super.onCreate(savedInstanceState);
        
        //Memoレイアウトファイルを指定
        setContentView(R.layout.layout_memo);
        
        //Buttonオブジェクト取得
        Button button = (Button)findViewById(R.id.savebutton);
        
        //Buttonオブジェクトにクリックリスナーを設定
        button.setOnClickListener(this);
    }

    //クリック処理
	@Override
	public void onClick(View view) {
		
		//件名オブジェクト取得
		EditText subject_obj = (EditText)findViewById(R.id.subject);
		String subject = subject_obj.getText().toString();
		
		//本文オブジェクト取得
		EditText body_obj = (EditText)findViewById(R.id.body);
		String body = body_obj.getText().toString();
		
		//実行形態オブジェクト取得
		RadioGroup exec = (RadioGroup)findViewById(R.id.exec_group);
		
		//必須入力チェックメソッドへ
		if(!itemChk(subject,body,exec)){
			msgView(err_msg);	//エラーメッセージ表示処理へ
			return ;			//強制終了
		}
		
		//ラジオボタン取得
		RadioButton exec_button = (RadioButton)findViewById(exec.getCheckedRadioButtonId());
		
		
		//終了メッセージ初期化
		String msg;
		
		//選択別処理
		switch(exec_button.getId()){
		case R.id.save:
			
			saveMemo(subject,body);			//保存処理
			
			msg = "保存処理が成功しました。";		//終了処理
			msgView(msg);										
			break;
			
		case R.id.sms:
			
			saveMemo(subject,body);							//保存処理
			
			msg = "保存処理が成功しました。Eメールを起動します。";		//終了処理
			msgView(msg);										
			emailMemo(subject,body);						//E-MAIL起動処理
			break;
		}
	}
	
	//保存処理
	public void saveMemo(String subject, String body){
		
		//書き出し処理
		try{
			//ファイル名指定
			FileOutputStream stream = openFileOutput("MEMO.csv",MODE_APPEND);
			
			//バッファ領域確保
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(stream));
			
			//格納
			String[] items = {
					subject,
					body
			};
			
			//書き込み処理
			for(int i = 0; i < items.length; i++){
				
				//カンマ混入チェック
				if(items[i].indexOf(",") != -1){
					items[i].replace(",","■");
				}
				out.write(items[i]);			//書き込み
				if((i + 1) == items.length){
					out.newLine();
				}else{
					out.write(",");
				}
			}
				
			//バッファ領域解放
			out.close();
			
		}catch(Exception e){
			err_msg = "Error:書き込み処理に失敗しました。";
			msgView(err_msg);			//エラーメッセージ表示処理へ
		}	
	}
	
	//SMS起動処理
	public void emailMemo(String subject, String body){
		
		//インテント生成
		Intent intent = new Intent("android.intent.action.SENDTO",Uri.parse("mailto:"));
		
		//付属情報設定
		intent.putExtra(Intent.EXTRA_SUBJECT, subject);		//件名
		intent.putExtra(Intent.EXTRA_TEXT, body);			//本文
		
		//アクティビティ実行
		startActivity(intent);
	}
	
	//必須入力チェック
	public boolean itemChk(String subject, String body, RadioGroup exec){
		
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

		//実行形態ボタンチェック
		if(exec.getCheckedRadioButtonId() == -1){
			err_msg = "Error:実行モードが未選択です。";
			itemChk = false;
			return itemChk;
		}
		
		itemChk = true;
		return itemChk;
	}
	
	//メッセージ表示処理
	public void msgView(String msg_txt){
		
		//メッセージ表示設定
		Toast msg = Toast.makeText(this, msg_txt, Toast.LENGTH_LONG);
		msg.setGravity(Gravity.CENTER, 0, 0);
		
		//メッセージ表示
		msg.show();
	}
}
