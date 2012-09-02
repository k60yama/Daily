package jp.co.android;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class ListViewTestActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        
    	//ActivityクラスのonCreateを実行
    	super.onCreate(savedInstanceState);
        
    	//レイアウト設定ファイルの指定
        setContentView(R.layout.main);
        
        //ListViewオブジェクトを取得
        ListView list = (ListView)findViewById(R.id.list);
        
        //SimpleAdapterのインスタンス生成
        SimpleAdapter adapter = new SimpleAdapter(
        		this,
        		getListData(),
        		android.R.layout.simple_list_item_2,
        		new String[]{"no","name"},
        		new int[]{android.R.id.text1, android.R.id.text2});
        
        list.setAdapter(adapter);
        
        /*
        //Buttonオブジェクトの取得
        Button button = (Button)findViewById(R.id.button);
        
        //Buttonオブジェクトにクリックリスナーを設定
        button.setOnClickListener(this);
    	*/
    }

    private ArrayList<HashMap<String,String>> getListData(){
    	
    	//ArrayListのインスタンスを生成(データ型はHashMap型)
    	ArrayList<HashMap<String,String>> listData = new ArrayList<HashMap<String,String>>();
    	
    	listData.add(getMapData(new String[][]{{"no","01"}, {"name","あいうえお"}}));
    	listData.add(getMapData(new String[][]{{"no","02"}, {"name","かきくけこ"}}));
    	listData.add(getMapData(new String[][]{{"no","03"}, {"name","さしすせそ"}}));
    	
    	return listData;
    }
    
    
    private HashMap<String,String> getMapData(String[][] values){
    	
    	//HashMapのインスタンスを生成
    	HashMap<String,String> map = new HashMap<String,String>();
    	
    	for(int i = 0; i<values.length; i++){
    		map.put(values[i][0], values[i][1]);
    	}
    	
    	return map;
    }
    
    
	@Override
	public void onClick(View view) {
		
		/*
		String[] data = new String[]{
				"福岡",
				"大分",
				"熊本",
				"佐賀",
				"長崎",
				"宮崎",
				"鹿児島",
				"沖縄"
		};
		
		String[] data2 = new String[]{
				"フクオカ",
				"オオイタ",
				"クマモト",
				"サガ",
				"ナガサキ",
				"ミヤザキ",
				"カゴシマ",
				"オキナワ"
		};
		*/
		
		/*
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
		String date_str = date.format(new Date());
	
		String[] data = new String[]{
				date_str,
				date_str,
		};
		
		ArrayAdapter<String> adapter;
		
		adapter = new ArrayAdapter<String>(
				this,android.R.layout.simple_list_item_1,data);
		
		ListView list = (ListView)findViewById(R.id.list);
		list.setAdapter(adapter);
		*/
		
		/*
		String[] test = {
			"te,s,t",
			"google",
			"ya,ho,o"
		};
		
		
		
		for(int i = 0; i < test.length; i++){
			
			if(test[i].indexOf(",") != -1){
				Toast.makeText(this, (i+1) + "要素目：カンマ混入", Toast.LENGTH_SHORT).show();
				test[i] = test[i].replace(",", "■");
				Toast.makeText(this, (i+1) + "要素目：置換後" + test[i], Toast.LENGTH_SHORT).show();
			}else{
				Toast.makeText(this, (i+1) + "要素目：カンマ混入してない。", Toast.LENGTH_SHORT).show();
			}
		}
		*/
		
	}
	
	
	
	
}