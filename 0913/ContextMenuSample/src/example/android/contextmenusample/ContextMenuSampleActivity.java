package example.android.contextmenusample;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class ContextMenuSampleActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	//ActivityクラスのOnCreateを実行
        super.onCreate(savedInstanceState);
        
        //レイアウト設定ファイルの指定
        setContentView(R.layout.contextmenusample);
        
        //リストビューに表示するリスト作成
        List<String> list = new ArrayList<String>();
        list.add("選択1");
        list.add("選択2");
        list.add("選択3");
        
        //リストアダプタ生成
        ListAdapter adapter = new ArrayAdapter<String>(
        		this,
        		android.R.layout.simple_list_item_1,
        		list);
        
        //ListViewオブジェクトにアダプタを設定
        ListView listview = (ListView)findViewById(R.id.list);
        listview.setAdapter(adapter);
        
        //コンテキストメニューにリストビューを登録
        registerForContextMenu(listview);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_context_menu_sample, menu);
        return true;
    }

    
    //コンテキストメニュー生成
    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenuInfo menuInfo){
    	
    	//コンテキストメニュー設定
    	menu.setHeaderTitle("コンテキストメニュー");
    	menu.add("メニュー1");
    	menu.add("メニュー2");
    	menu.add("メニュー3");
    	
    	//ActivityクラスのonCreateContextMenuを実行
    	super.onCreateContextMenu(menu, view, menuInfo);
    }
    
    //コンテキストメニュー選択
    @Override
    public boolean onContextItemSelected(MenuItem item){
    	
    	//選択されたメニューをTextViewに表示
    	TextView view = (TextView)findViewById(R.id.text);
    	view.setText("コンテキストメニューで選択：" + item.getTitle());
    	
    	//ActivityクラスのonContextItemSelectedの戻り値を返す
    	return super.onContextItemSelected(item);
    }
    
}
