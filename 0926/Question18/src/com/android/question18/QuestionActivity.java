package com.android.question18;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.TabActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;

public class QuestionActivity extends TabActivity implements OnClickListener, OnTabChangeListener{

	//TabHostオブジェクト
	private TabHost tabHost;
	
	//TableLayoutオブジェクト
	private TableLayout tablelayout;
	
	//ファイル名
	private final static String FILE_NAME="TabQuestion";
	
	//アクティビティのonCreateを実行
	@Override
	public void onCreate(Bundle savedInstanceState){
		
		//ActivityのonCreateを実行
		super.onCreate(savedInstanceState);
		
		//TabHostを取得
		tabHost = getTabHost();
		
		//レイアウトXMLデータ取得
		LayoutInflater.from(this).inflate(R.layout.question_layout, tabHost.getTabContentView(), true);
		
		//タブ設定
		setTab();
		tabHost.setCurrentTab(0);
		tabHost.setOnTabChangedListener(this);
		
		//Buttonオブジェクト取得
		Button button = (Button)findViewById(R.id.registry);
		button.setOnClickListener(this);
	}
	
	//タブ設定メソッド
	public void setTab(){
		
		//設定タブ
		TabSpec settingTab = tabHost.newTabSpec("settingTab");
		settingTab.setIndicator("設定", getResources().getDrawable(android.R.drawable.ic_menu_add));
		settingTab.setContent(R.id.order_registry);
		tabHost.addTab(settingTab);
		
		//表示タブ
		TabSpec viewTab = tabHost.newTabSpec("viewTab");
		viewTab.setIndicator("表示", getResources().getDrawable(android.R.drawable.ic_menu_info_details));
		viewTab.setContent(R.id.order_list);
		tabHost.addTab(viewTab);
	}
	
	//登録ボタン押下後
	@Override
	public void onClick(View view){
		
		//EditTextオブジェクト取得
		EditText itemName = (EditText)findViewById(R.id.itemName);
		EditText price = (EditText)findViewById(R.id.price);
		
		//ファイルに保存
		try{
			FileOutputStream stream = openFileOutput(FILE_NAME, MODE_APPEND);
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(stream));
			out.write(itemName.getText().toString() + "," + price.getText().toString());
			out.newLine();
			out.close();
		}catch(Exception e){
			Log.e("onClick_Error:","ファイル保存処理で異常が発生しました。");	
		}
	}

	//タブ切り替え
	@Override
	public void onTabChanged(String tabItem) {
		//表示タブ
		if(tabItem.equals("viewTab")){
			//TableLayoutを初期化
			tablelayout = (TableLayout)findViewById(R.id.order_list);
			tablelayout.removeAllViews();
			tablelayout.setStretchAllColumns(true);
			
			//ヘッダー作成
			TableRow headrow = new TableRow(this);
			TextView itemLabel = new TextView(this);
			itemLabel.setText("商品名");
			itemLabel.setGravity(Gravity.CENTER_HORIZONTAL);
			headrow.addView(itemLabel);
			
			TextView priceLabel = new TextView(this);
			priceLabel.setText("価格");
			priceLabel.setGravity(Gravity.CENTER_HORIZONTAL);
			headrow.addView(priceLabel);
			
			//ヘッダー追加
			tablelayout.addView(headrow);
			
			//ファイル読み込み
			readFile();
		}
	}
	
	//ファイル読み込み処理
	public void readFile(){
		try{
			FileInputStream stream = openFileInput(FILE_NAME);
			BufferedReader in = new BufferedReader(new InputStreamReader(stream));
			
			String line = in.readLine();
			while(line != null){
				itemList(line);
				line = in.readLine();
			}	
			in.close();
		}catch(Exception e){
			Log.e("readFile_Error:","ファイル読み込みで異常が発生しました。");
		}
	}
	
	//商品リストの作成処理
	public void itemList(String readInfo){
		
		//カンマで分割
		String[] itemInfo = readInfo.split(",");
		
		//商品リスト
		TableRow itemList = new TableRow(this);
		TextView itemName = new TextView(this);
		itemName.setText(itemInfo[0]);
		itemName.setGravity(Gravity.CENTER_HORIZONTAL);
		itemList.addView(itemName);
		
		TextView price = new TextView(this);
		price.setText(itemInfo[1]);
		price.setGravity(Gravity.CENTER_HORIZONTAL);
		itemList.addView(price);
		
		//リスト追加
		tablelayout.addView(itemList);
	}
}
