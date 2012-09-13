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
    	
    	//Activity�N���X��OnCreate�����s
        super.onCreate(savedInstanceState);
        
        //���C�A�E�g�ݒ�t�@�C���̎w��
        setContentView(R.layout.contextmenusample);
        
        //���X�g�r���[�ɕ\�����郊�X�g�쐬
        List<String> list = new ArrayList<String>();
        list.add("�I��1");
        list.add("�I��2");
        list.add("�I��3");
        
        //���X�g�A�_�v�^����
        ListAdapter adapter = new ArrayAdapter<String>(
        		this,
        		android.R.layout.simple_list_item_1,
        		list);
        
        //ListView�I�u�W�F�N�g�ɃA�_�v�^��ݒ�
        ListView listview = (ListView)findViewById(R.id.list);
        listview.setAdapter(adapter);
        
        //�R���e�L�X�g���j���[�Ƀ��X�g�r���[��o�^
        registerForContextMenu(listview);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_context_menu_sample, menu);
        return true;
    }

    
    //�R���e�L�X�g���j���[����
    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenuInfo menuInfo){
    	
    	//�R���e�L�X�g���j���[�ݒ�
    	menu.setHeaderTitle("�R���e�L�X�g���j���[");
    	menu.add("���j���[1");
    	menu.add("���j���[2");
    	menu.add("���j���[3");
    	
    	//Activity�N���X��onCreateContextMenu�����s
    	super.onCreateContextMenu(menu, view, menuInfo);
    }
    
    //�R���e�L�X�g���j���[�I��
    @Override
    public boolean onContextItemSelected(MenuItem item){
    	
    	//�I�����ꂽ���j���[��TextView�ɕ\��
    	TextView view = (TextView)findViewById(R.id.text);
    	view.setText("�R���e�L�X�g���j���[�őI���F" + item.getTitle());
    	
    	//Activity�N���X��onContextItemSelected�̖߂�l��Ԃ�
    	return super.onContextItemSelected(item);
    }
    
}
