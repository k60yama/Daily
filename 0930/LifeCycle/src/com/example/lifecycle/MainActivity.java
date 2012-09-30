package com.example.lifecycle;

import android.os.Bundle;
import android.app.Activity;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this, "onCreate is Called", Toast.LENGTH_SHORT).show();
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onStart(){
    	super.onStart();
    	Toast.makeText(this, "onStart is Called", Toast.LENGTH_SHORT).show();
    }
    
    @Override
    public void onRestart(){
    	super.onRestart();
    	Toast.makeText(this, "onRestart is Called", Toast.LENGTH_SHORT).show();
    }
    
    @Override
    public void onResume(){
    	super.onResume();
    	Toast.makeText(this, "onResume is Called", Toast.LENGTH_SHORT).show();
    }
    
    @Override
    public void onPause(){
    	super.onPause();
    	Toast.makeText(this, "onPause is Called", Toast.LENGTH_SHORT).show();
    }
    
    @Override
    public void onStop(){
    	super.onStop();
    	Toast.makeText(this, "onStop is Called", Toast.LENGTH_SHORT).show();
    }
    
    @Override
    public void onDestroy(){
    	super.onDestroy();
    	Toast.makeText(this, "onDestroy is Called", Toast.LENGTH_SHORT).show();
    }
    
}
