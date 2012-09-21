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
    	
    	//Activity�N���X��onCreate�����s����
        super.onCreate(savedInstanceState);
        
        //TabHost�擾
        TabHost tabHost = getTabHost();
        
        //���C�A�E�gXML�f�[�^�擾
        LayoutInflater.from(this).inflate(R.layout.tabsample, tabHost.getTabContentView(), true);
        
        //TextView�ɐF��ݒ�
        TextView txt1 = (TextView)findViewById(R.id.textview1);
        TextView txt2 = (TextView)findViewById(R.id.textview2);
        
        txt1.setBackgroundColor(Color.GREEN);
        txt2.setBackgroundColor(Color.BLUE);
        
        //�^�u�P�̐ݒ�
        TabSpec tab1 = tabHost.newTabSpec("tab1");
        tab1.setIndicator("�^�u�P");
        tab1.setContent(R.id.linearlayout1);
        
        //�^�u�Q�̐ݒ�
        TabSpec tab2 = tabHost.newTabSpec("tab2");
        tab2.setIndicator("�^�u�Q", getResources().getDrawable(android.R.drawable.ic_menu_edit));
        tab2.setContent(R.id.textview1);
        
        //�^�u�R�̐ݒ�
        TabSpec tab3 = tabHost.newTabSpec("tab3");
        tab3.setIndicator("�^�u�R", getResources().getDrawable(android.R.drawable.ic_menu_search));
        tab3.setContent(R.id.textview2);
        
        //�e�^�u��TabHost�ɐݒ�
        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
        tabHost.addTab(tab3);
        
        //�����\���̃^�u��ݒ�
        tabHost.setCurrentTab(0);
    }
}
