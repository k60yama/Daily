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
    	
    	//ActivityクラスのonCreateを実行
        super.onCreate(savedInstanceState);
        
        //レイアウト設定ファイルを指定
        setContentView(R.layout.dialoglayout);
        
        //Buttonオブジェクトを取得
        Button[] button = {
        		(Button)findViewById(R.id.button1),
        		(Button)findViewById(R.id.button2),
        		(Button)findViewById(R.id.button3),
        		(Button)findViewById(R.id.button4),
        		(Button)findViewById(R.id.button5),
        		(Button)findViewById(R.id.button6),
        };
        
        //Buttonオブジェクトクリックリスナー設定
        for(int i=0; i<button.length; i++){
        	button[i].setOnClickListener(this);
        }
    }

    @Override
    //ボタン押下時のメソッド
    public void onClick(View view){
    	
    	//TextViewオブジェクト取得
    	TextView msg = (TextView)findViewById(R.id.msg);
    	
    	//DialogShowクラスのインスタンス生成
    	DialogShow dialog = new DialogShow(this,msg);
    	
    	//押下別処理
    	switch(view.getId()){
    	case R.id.button1:
    		//通常ダイアログ表示
    		dialog.showDialog();
    		break;
    	case R.id.button2:
    		//テキストダイアログ表示
    		dialog.showTextDialog();
    		break;
    	case R.id.button3:
    		//単一選択ログ表示
    		dialog.showSingleDialog();
    		break;
    	case R.id.button4:
    		//日付選択ダイアログ表示
    		dialog.showDatePickerDialog();
    		break;
    	case R.id.button5:
    		//時間選択ダイアログ表示
    		dialog.showTimePickerDialog();
    		break;
    	case R.id.button6:
    		//プログレスバーダイアログ表示
    		dialog.showProgressDialog();
    		break;
    	}
    }
}
