package example.android.contentprovidersample2;

import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.app.Activity;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ContentProviderSample2Activity extends Activity {

	TableLayout tablelayout = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	//ActivityクラスのonCreateを実行
        super.onCreate(savedInstanceState);
        
        //レイアウト設定ファイルの指定
        setContentView(R.layout.contentprovidersample2);
        
        //テーブルレイアウト取得
        tablelayout = (TableLayout)findViewById(R.id.tablelayout);
        
        try{
        	//データ取得
        	Cursor cur = this.managedQuery(Contacts.CONTENT_URI,
        			null,
        			null,
        			null,
        			null);
        	
        	//データが取得できた場合
        	if(cur.getCount() != 0){
        		//取得したデータ表示
        		while(cur.moveToNext()){
        			String name = cur.getString(
        					cur.getColumnIndex(Contacts.DISPLAY_NAME));
        			setItems(name);
        		}
        	}else{
        		TextView message = new TextView(this);
        		message.setText("データが取得できませんでした。");
        		LinearLayout linearlayout = (LinearLayout)this.findViewById(R.id.linearlayout);
        		linearlayout.addView(message);
        	}
        }catch(Exception e){
        	Log.e("ERROR",e.getMessage());
        }
        
    }

    //setItemsメソッド(テーブル表示処理)
    private void setItems(String name) {
		//名前を設定
    	TableRow row = new TableRow(this);
    	TextView displayName = new TextView(this);
    	displayName.setText(name);
    	row.addView(displayName);
		
    	//テーブルレイアウトに設定
    	tablelayout.addView(row);
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.contentprovidersample2, menu);
        return true;
    }

    
}
