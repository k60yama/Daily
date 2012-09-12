package com.android.optionmenusample;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;

public class OpenMenuSample extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	//ActivityクラスのonCreateを実行
        super.onCreate(savedInstanceState);
        
        //レイアウト設定ファイルの指定
        setContentView(R.layout.optionmenusample);
    }

    //オプションメニュー生成
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	
    	//ActivityクラスのonCreateOptionMenuを実行
    	super.onCreateOptionsMenu(menu);
    	
    	//メニューアイテム１の追加
    	MenuItem item1 = menu.add(0,0,0,"item1");
    	
    	//メニューアイテム２の追加
    	MenuItem item2 = menu.add(0,1,0,"item2");
    	item2.setIcon(android.R.drawable.ic_menu_search);
    	
    	//メニューアイテム３の追加
    	MenuItem item3 = menu.add(0,2,0,"item3");
    	item3.setIcon(android.R.drawable.ic_menu_save);
        return true;
    }

    
    //メニューアイテム選択処理
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
    	showMsg(item.getItemId() + 1);
    	return true;
    }    
    
    //ダイアログ表示(show()の場合)
    public void showMsg(Integer position){
    	
    	//ダイアログのインスタンス生成
    	AlertDialog.Builder dialog = new AlertDialog.Builder(OpenMenuSample.this);
    	
    	//ダイアログ設定
    	dialog.setTitle("メニューアイテム選択結果");
    	dialog.setMessage("メニューアイテム" + position + "を選択しました。");
    	dialog.setPositiveButton("OK", new DialogInterface.OnClickListener(){
    		@Override
    		public void onClick(DialogInterface dialog, int whichButton){
    			OpenMenuSample.this.setResult(Activity.RESULT_OK);
    		}
    	});
    	
    	//ダイアログ作成
    	dialog.create();
    	
    	//ダイアログ表示
    	dialog.show();
    }
   
}
