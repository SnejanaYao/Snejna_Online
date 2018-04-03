package com.example.androidfrist;



import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class WelcomeActity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		new Handler(new Handler.Callback() {
          //处理接收到的消息的方法
         @Override
         public boolean handleMessage(Message arg0) {
           //实现页面跳转
           startActivity(new Intent(getApplicationContext(),MainActivity.class));
            return false;
         }
     }).sendEmptyMessageDelayed(0, 3000); //表示延时三秒进行任务的执行
	
	}
	

	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		System.out.println("onDestroy");
	}
	  
}


