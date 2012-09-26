package com.android.question17;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;

public class QuestionActivity extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	//ActivityクラスのonCreate
        super.onCreate(savedInstanceState);
        
        //レイアウト設定ファイルの指定
        setContentView(R.layout.question_layout);
    }

    //オプションメニュー生成
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	
    	//メニュー設定ファイルの指定
        getMenuInflater().inflate(R.menu.question_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //メニュー選択処理
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
    	switch(item.getItemId()){
    	case R.id.menu_setting:
    		this.dateDialogView();
    		break;
    	case R.id.menu_view:
    		this.normalDialog(this.readPreferences());
    		break;
    	}
    	return super.onOptionsItemSelected(item);
    }
    
    //日付ダイアログ表示
    public void dateDialogView(){
    	
    	//Calendar クラスのインスタンス取得
    	Calendar cal = Calendar.getInstance();
    	
    	//日付ダイアログの設定
    	DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener(){
    		//設定ボタン押下処理
    		@Override
    		public void onDateSet(DatePicker picker, int year, int month, int day){
    			
    			//プリファレンス生成
    	    	SharedPreferences pref = getSharedPreferences("DialogDate", MODE_PRIVATE);
    	    	
    	    	//編集用のインスタンス取得
    	    	SharedPreferences.Editor editor = pref.edit();
    	    	
    	    	//プリファレンスに保存
    	    	editor.putString("DATE", year + "年" + (month+1) + "月" + day + "日");
    	    	editor.commit();
    		}
    	},
    	cal.get(Calendar.YEAR),
    	cal.get(Calendar.MONTH),
    	cal.get(Calendar.DAY_OF_MONTH)
    	);
    	
    	//日付ダイアログの表示
    	dialog.show();
    }
    

    //プリファレンス読み込み
    public String readPreferences(){
    	
    	//プリファレンス取得
    	SharedPreferences pref = getSharedPreferences("DialogDate", MODE_PRIVATE);
    	return pref.getString("DATE", "日付は保存されていません。");
    }
    
    //通常ダイアログ表示
    public void normalDialog(String date){
    	AlertDialog.Builder dialog = new AlertDialog.Builder(this);
    	dialog.setTitle("保存結果");
    	dialog.setMessage(date);
    	dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			//OK押下後、処理
    		@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		});
    	
    	//ダイアログ表示
    	dialog.show();
    }
}
