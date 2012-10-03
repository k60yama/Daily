package com.example.numericpuzzle;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;

public class NumericPuzzle extends Activity implements OnClickListener{

	//ImageButtonオブジェクトのIDを配列化
	private final int imageButtons[] = {
		R.id.image_button1, R.id.image_button2, R.id.image_button3, R.id.image_button4,
		R.id.image_button5, R.id.image_button6, R.id.image_button7, R.id.image_button8,
		R.id.image_button9, R.id.image_button10, R.id.image_button11, R.id.image_button12,
		R.id.image_button13, R.id.image_button14, R.id.image_button15, R.id.image_button16
	};
	
	//画像オブジェクトを配列化
	private final int numImages[] = {
		R.drawable.num1, R.drawable.num2, R.drawable.num3, R.drawable.num4,
		R.drawable.num5, R.drawable.num6, R.drawable.num7, R.drawable.num8,
		R.drawable.num9, R.drawable.num10, R.drawable.num11, R.drawable.num12,
		R.drawable.num13, R.drawable.num14, R.drawable.num15, R.drawable.blank,
	};
	
	//ゲーム開始フラグ初期化
	private boolean gameStarted = false;
	
	//内部クラス OrderControllerクラスの配列インスタンス生成
	OrderController orders[] = new OrderController[imageButtons.length];
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		
		//ActivityクラスのonCreateを実行
		super.onCreate(savedInstanceState);
		
		//レイアウト設定ファイルの指定
		this.setContentView(R.layout.numericpuzzle);
		
		//画像とImageButtonの関連付け
		this.createOrderController();
		
		//Buttonオブジェクトにクリックリスナー設定
		((Button)this.findViewById(R.id.start_button)).setOnClickListener(this);
	}
	
	//画像とImageButtonの関連付け
	private void createOrderController(){
		for(int i = 0; i < (this.imageButtons).length; i++){
			//ImageButtonオブジェクトのId
			ImageButton imgbtn = (ImageButton)this.findViewById(this.imageButtons[i]);
			
			//OrderControllerクラスのコンストラクタ実行
			this.orders[i] = new OrderController(imgbtn, i, this.numImages[i]);
		}
	}
	
	//ゲーム開始メソッド
	private void startGame(){
		//画像配列の長さを取得
		int size = (this.numImages).length;
		
		//空白を除く数字画像を並び変える
		for(int i = 0; i < size-2; i++){
			
			//入れ替える画像のインデックスをランダムで決める
			int swap = (int)(Math.random() * (size - (i + 1)));
			
			//画像の入れ替え
			orders[i].swapImage(orders[i + swap]);
		}
		
		//ゲーム開始フラグON
		this.gameStarted = true;
	}
	
	//ゲームクリアチェック
	private boolean isCompleted(){
		//ゲーム開始フラグチェック
		if(!(gameStarted)){
			return false;		//ゲームが開始されてなければ、falseを返す
		}
		
		//クリア条件チェック(配列:orders と配列：numImagesの一致チェック)
		for(int i = 0; i < numImages.length; i++){
			if(numImages[i] != orders[i].getImageRes()){
				return false;	//正しく合わせられていない場合は、falseを返す
			}
		}
		return true;			//正しく合わせられていればtrueを返す
	}
	
	//タイマー開始メソッド
	private void startChronometer(){
		//Chronometerオブジェクト取得
		Chronometer chrono = (Chronometer)this.findViewById(R.id.chronometer);
		
		//基準時刻の設定(よくわからないって！！)
		chrono.setBase(SystemClock.elapsedRealtime());
		
		//タイマー開始
		chrono.start();
	}
	
	private long stopChronometer(){
		//Chronometerオブジェクト取得
		Chronometer chrono = (Chronometer)this.findViewById(R.id.chronometer);
		
		//?
		chrono.stop();
		return SystemClock.elapsedRealtime() - chrono.getBase();
	}
	
	@Override
	//スタートボタン押下後処理
	public void onClick(View view){
		this.startGame();
		this.startChronometer();
	}
	
	//ゲームクリアメッセージ
	private void complete(){
		long msec = this.stopChronometer();
		AlertDialog.Builder alertDlgBld = new AlertDialog.Builder(this);
		alertDlgBld.setTitle(R.string.complete_title);			//ダイアログ件名
		alertDlgBld.setMessage(msec / 1000 + "秒");				//ダイアログ内メッセージ
		
		//おめでとうボタン
		alertDlgBld.setPositiveButton(
				R.string.complete_button,
				new DialogInterface.OnClickListener(){
					//おめでとう押下後処理
					public void onClick(DialogInterface dialog, int which){
						dialog.dismiss();
					}
				});
		
		//ダイアログ表示
		alertDlgBld.show();
	}
	
	//空白の検索
	private void searchDir(int idx){
		boolean searchRight = true;
		boolean searchLeft = true;
		boolean searchUp = true;
		boolean searchDown = true;
		
		//最上段行が押下された場合は、上側の検索をしない
		if(idx < 4){
			searchUp = false;
		}
		
		//最下段行が押下された場合、下側の検索をしない
		if(idx > 11){
			searchDown = false;
		}
		
		//最左列が押下された場合、左側の検索をしない
		if((idx % 4) == 0){
			searchLeft = false;
		}
		
		//最右列が押下された場合、右側の検索をしない
		if((idx % 4) == 3){
			searchRight = false;
		}
		
		//上側の検索処理へ
		if(searchUp){
			if(this.searchUp(idx)){
				return;
			}
		}
		
		//下側の検索処理へ
		if(searchDown){
			if(this.searchDown(idx)){
				return;
			}
		}
		
		//左側の検索処理へ
		if(searchLeft){
			if(this.searchLeft(idx)){
				return;
			}
		}
		
		//右側の検索処理へ
		if(searchRight){
			if(this.searchRight(idx)){
				return;
			}
		}
	}
	
	private boolean searchUp(int idx){
		int distance = 0;
		for(int i = idx -4; i > -1; i = i - 4){
			distance--;
			if(orders[i].getImageRes() == R.drawable.blank){
				swapUp(idx, distance);
				return true;
			}
		}
		return false;
	}
	
	private boolean searchDown(int idx){
		int distance = 0;
		for(int i = idx + 4; i < 16; i = i + 4){
			distance++;
			if(orders[i].getImageRes() == R.drawable.blank){
				swapDown(idx, distance);
				return true;
			}
		}
		return false;
	}
	
	private boolean searchLeft(int idx){
		int distance = 0;
		int min = 0;
		min = idx - (idx % 4);
		
		for(int i = idx - 1; i >= min; i--){
			distance--;
			if(orders[i].getImageRes() == R.drawable.blank){
				swapLeft(idx, distance);
				return true;
			}
		}
		return false;
	}
	
	private boolean searchRight(int idx){
		int distance = 0;
		int max = 15;
		max = (idx + 4) - (idx + 4) % 4;
		
		for(int i = idx + 1; i < max; i++ ){
			distance++;
			if(orders[i].getImageRes() == R.drawable.blank){
				swapRight(idx, distance);
				return true;
			}
		}
		return false;
	}
	
	private void swapUp(int idx, int distance){
		for(int i = idx + (distance * 4); i < idx; i = i + 4){
			orders[i].swapImage(orders[i + 4]);
		}
	}
	
	private void swapDown(int idx, int distance){
		for(int i = idx + (distance * 4); i > idx; i = i -4){
			orders[i].swapImage(orders[i - 4]);
		}
	}
	
	private void swapLeft(int idx, int distance){
		for(int i = idx + distance; i < idx; i++){
			orders[i].swapImage(orders[i + 1]);
		}
	}
	
	private void swapRight(int idx, int distance){
		for(int i = idx + distance; i > idx; i--){
			orders[i].swapImage(orders[i - 1]);
		}
	}
	
	//内部クラス
	class OrderController implements OnClickListener{

		public ImageButton imgBtn;
		public int idx = 0;
		public int curImageId = 0;
		
		//コンストラクタ(オブジェクトIDと画像のリソースIDを割り当てる)
		public OrderController(ImageButton ibtn, int i, int resid){
			this.imgBtn = ibtn;							//ImageButtonのId
			this.idx = i;								//配列のindex
			this.setImageRes(resid);					//ImageButtonのIdに画像を設定
			(this.imgBtn).setOnClickListener(this);		//ImageButtonにクリックリスナーを設定
		}
		
		public int setImageRes(int resid){
			int old = this.curImageId;					//現在の画像obj → old
			this.curImageId = resid;					//新しい画像obj → 現在の画像obj

			//ImageButtonのIdに画像を設定
			(this.imgBtn).setImageResource(resid);			
			return old;								
		}
		
		public int getImageRes(){
			//現在の画像を戻り値とする
			return this.curImageId;
		}
		
		@Override
		//画像が押下された場合
		public void onClick(View view) {	
			if(this.curImageId == R.drawable.blank){
				//押下された画像が空白の場合は終了
				return;
			}
			searchDir(idx);
			
			//ゲームクリア判定
			if(isCompleted()){
				//ゲームクリアメッセージメソッド
				complete();	
			}
		}
		
		public void swapImage(OrderController other){
			int previous = other.setImageRes(curImageId);
			this.setImageRes(previous);
		}
	}
}
