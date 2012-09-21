package example.android.tabsample;

import android.os.Bundle;
import android.app.TabActivity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class TabSample extends TabActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	//ActivityクラスのonCreateを実行する
        super.onCreate(savedInstanceState);
        
        //TabHost取得
        TabHost tabHost = getTabHost();
        
        //レイアウトXMLデータ取得
        LayoutInflater.from(this).inflate(R.layout.tabsample, tabHost.getTabContentView(), true);
        
        //TextViewに色を設定
        TextView txt1 = (TextView)findViewById(R.id.textview1);
        TextView txt2 = (TextView)findViewById(R.id.textview2);
        
        txt1.setBackgroundColor(Color.GREEN);
        txt2.setBackgroundColor(Color.BLUE);
        
        //タブ１の設定
        TabSpec tab1 = tabHost.newTabSpec("tab1");
        tab1.setIndicator("タブ１");
        tab1.setContent(R.id.linearlayout1);
        
        //タブ２の設定
        TabSpec tab2 = tabHost.newTabSpec("tab2");
        tab2.setIndicator("タブ２", getResources().getDrawable(android.R.drawable.ic_menu_edit));
        tab2.setContent(R.id.textview1);
        
        //タブ３の設定
        TabSpec tab3 = tabHost.newTabSpec("tab3");
        tab3.setIndicator("タブ３", getResources().getDrawable(android.R.drawable.ic_menu_search));
        tab3.setContent(R.id.textview2);
        
        //各タブをTabHostに設定
        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
        tabHost.addTab(tab3);
        
        //初期表示のタブを設定
        tabHost.setCurrentTab(0);
    }
}
