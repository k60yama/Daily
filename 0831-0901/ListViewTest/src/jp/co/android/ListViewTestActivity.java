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
        
    	//Activity�N���X��onCreate�����s
    	super.onCreate(savedInstanceState);
        
    	//���C�A�E�g�ݒ�t�@�C���̎w��
        setContentView(R.layout.main);
        
        //ListView�I�u�W�F�N�g���擾
        ListView list = (ListView)findViewById(R.id.list);
        
        //SimpleAdapter�̃C���X�^���X����
        SimpleAdapter adapter = new SimpleAdapter(
        		this,
        		getListData(),
        		android.R.layout.simple_list_item_2,
        		new String[]{"no","name"},
        		new int[]{android.R.id.text1, android.R.id.text2});
        
        list.setAdapter(adapter);
        
        /*
        //Button�I�u�W�F�N�g�̎擾
        Button button = (Button)findViewById(R.id.button);
        
        //Button�I�u�W�F�N�g�ɃN���b�N���X�i�[��ݒ�
        button.setOnClickListener(this);
    	*/
    }

    private ArrayList<HashMap<String,String>> getListData(){
    	
    	//ArrayList�̃C���X�^���X�𐶐�(�f�[�^�^��HashMap�^)
    	ArrayList<HashMap<String,String>> listData = new ArrayList<HashMap<String,String>>();
    	
    	listData.add(getMapData(new String[][]{{"no","01"}, {"name","����������"}}));
    	listData.add(getMapData(new String[][]{{"no","02"}, {"name","����������"}}));
    	listData.add(getMapData(new String[][]{{"no","03"}, {"name","����������"}}));
    	
    	return listData;
    }
    
    
    private HashMap<String,String> getMapData(String[][] values){
    	
    	//HashMap�̃C���X�^���X�𐶐�
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
				"����",
				"�啪",
				"�F�{",
				"����",
				"����",
				"�{��",
				"������",
				"����"
		};
		
		String[] data2 = new String[]{
				"�t�N�I�J",
				"�I�I�C�^",
				"�N�}���g",
				"�T�K",
				"�i�K�T�L",
				"�~���U�L",
				"�J�S�V�}",
				"�I�L�i��"
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
				Toast.makeText(this, (i+1) + "�v�f�ځF�J���}����", Toast.LENGTH_SHORT).show();
				test[i] = test[i].replace(",", "��");
				Toast.makeText(this, (i+1) + "�v�f�ځF�u����" + test[i], Toast.LENGTH_SHORT).show();
			}else{
				Toast.makeText(this, (i+1) + "�v�f�ځF�J���}�������ĂȂ��B", Toast.LENGTH_SHORT).show();
			}
		}
		*/
		
	}
	
	
	
	
}