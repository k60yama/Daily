package com.android.optionmenusample;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
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
        
        //メニューアイテム４の追加
        MenuItem item4 = menu.add(0,3,0,"item4");
        item4.setIcon(android.R.drawable.ic_menu_call);
        
        //メニューアイテム５の追加
        MenuItem item5 = menu.add(0,4,0,"item5");
        item5.setIcon(android.R.drawable.ic_menu_camera);
        
        //メニューアイテム６の追加
        SubMenu item6 = menu.addSubMenu(0,5,0,"その他");
        item6.setIcon(android.R.drawable.ic_menu_more);
        item6.add(0,10,0,"subitem1");
        item6.add(0,20,0,"subitem2");
        
        return true; 
    }

    
    //メニューアイテム選択処理
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
    	
    	//ダイアログに表示するメッセージ初期化
    	String msg = "";
    	
    	switch(item.getItemId()){
    	case 0:
    		showMsg("メニューアイテム1を選択しました。");
    		break;
    	case 1:
    		showMsg("メニューアイテム2を選択しました。");
    		break;
    	case 2:
    		showMsg("メニューアイテム3を選択しました。");
    		break;
    	case 3:
    		showMsg("メニューアイテム4を選択しました。");
    		break;
    	case 4:
    		showMsg("メニューアイテム5を選択しました。");
    		break;
    	case 10:
    		showMsg("サブメニューアイテム1を選択しました。");
    		break;
    	case 20:
    		showMsg("サブメニューアイテム2を選択しました。");
    		break;
    	}
    	
    	return true;
    }    
    
    //ダイアログ表示(show()の場合)
    public void showMsg(String msg){
    	
    	//ダイアログのインスタンス生成
    	AlertDialog.Builder dialog = new AlertDialog.Builder(OpenMenuSample.this);
    	
    	//ダイアログ設定
    	dialog.setTitle("メニューアイテム選択結果");
    	dialog.setMessage(msg);
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
