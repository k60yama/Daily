package layoutbook.example.photoviewer;

import android.app.Activity;
import android.os.Bundle;

public class PhotoViewer extends Activity{

	public void onCreate(Bundle savedInstanceState){
		
		//ActivityクラスのOnCreate実行
		super.onCreate(savedInstanceState);
		
		//レイアウト設定ファイルの指定
		this.setContentView(R.layout.main);
	}
}
