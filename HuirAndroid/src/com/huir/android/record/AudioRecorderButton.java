package com.huir.android.record;

import java.io.File;

import com.example.androidfrist.R;
import com.huir.android.record.AudioRecoderUtils.AudioStateListener;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

/** 
 * 自定义按钮 实现录音等功能 
 * 
 */  
  
@SuppressLint("AppCompatCustomView")  
public class AudioRecorderButton extends Button implements AudioStateListener {
	private static final String TAG = "AudioRecorderButton";
	
	private static final int STATE_NORMAL = 1;
	private static final int STATE_RECORDING = 2;
	private static final int STATE_WANT_CANCLE = 3;
	private static final int Y_WANT_CANCLE=300;
	private static final int MSG_AUDIO_PREPARED=0X110; 
	private static final int MSG_VOICE_CHANGE=0X111;
	private static final int MSG_DIALOG_DISMISS=0X112;
	private float time=0;
	private int STATE_CHANGE = STATE_NORMAL;
	private boolean isRecording;
	private boolean ready; //是否触发longClick
	private String filename;
	
	private DialogManager dialogManager;
	private AudioRecoderUtils audioManager;
	
	
	public AudioRecorderButton(Context context) {
		this(context,null);
		// TODO Auto-generated constructor stub
	}

	public AudioRecorderButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		dialogManager = new DialogManager(context);
		fileName();
		filename = "/sdcard/DownLoad/com.huir.download/Record";
		audioManager = AudioRecoderUtils.getInstance(filename);
		audioManager.setOnAudioStateListener(this);
		setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				Log.e(TAG, "进入长按");
				ready = true;
				audioManager.prepareAudio();
				return false;
			}
		});
	}
	
	   @Override
	   public void wellPrepared() {
		Log.e(TAG, "wellPrepared");
		mHandlr.sendEmptyMessage(MSG_AUDIO_PREPARED);
	}
	   
	/**
	 * 录音完成后的回调   
	 * @author huir316
	 *
	 */
	public interface OnAudioListener{
		void onFinish(float second,String filePath);
	}
	
	private  OnAudioListener audioListener;
	public void setOnAudioListener(OnAudioListener audioListener) {
		this.audioListener = audioListener;
	}
	/**
	 * 获取音量大小的线程   
	 */
	private Runnable getLevelRunnable = new Runnable() {
		@Override
		public void run() {
			while (isRecording) {
				try {
					Thread.sleep(100);
					time += 0.1f;//进入线程 计时
					mHandlr.sendEmptyMessage(MSG_VOICE_CHANGE);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}
	};
	private Handler mHandlr = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MSG_AUDIO_PREPARED:
					isRecording = true; //开始录音
					dialogManager.showDialog();
					new Thread(getLevelRunnable).start();
					break;
			case MSG_VOICE_CHANGE:
					Log.e(TAG, "进入MSG_VOICE_CHANGE");
					int le = dialogManager.updateLevel(audioManager.getLevel(5));
					break;
			case MSG_DIALOG_DISMISS:
					dialogManager.dismissDialog();
					break;
				}
			};
		};
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();
		int x = (int)event.getX();
		int y = (int)event.getY();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			changState(STATE_RECORDING); //按下 状态为录制
			break;
		case MotionEvent.ACTION_MOVE:
			if(isRecording) {
				if(wantCancle(x,y)) {
					changState(STATE_WANT_CANCLE);
				}else {
					changState(STATE_RECORDING);
				}
				Log.e(TAG, "ACTION_MOVE");
			}
			break;
		case MotionEvent.ACTION_UP:
				if(!ready) { //没有进入长按事件
					reset();
					return super.onTouchEvent(event);
				}
				if(!isRecording || time <0.9f) { //没有准备好
					dialogManager.tooShort();
					audioManager.cancel();
					mHandlr.sendEmptyMessageDelayed(MSG_DIALOG_DISMISS, 1300);
					
				}else if(STATE_CHANGE == STATE_RECORDING) {
					dialogManager.dismissDialog();
					audioManager.release();
					if(audioListener != null) {
						audioListener.onFinish(time, audioManager.getCurrentFilePath());
					}
				}else if(STATE_CHANGE == STATE_WANT_CANCLE) {
					dialogManager.dismissDialog();
					audioManager.release();
					audioManager.cancel();
				}
			reset();
			break;
		}
		return super.onTouchEvent(event);
	}

	/**
	 * 释放资源重置
	 */
	private void reset() {
		isRecording=false;
		changState(STATE_NORMAL);
		time = 0;
	}

	private boolean wantCancle(int x, int y) {
		if(x<0 || x>getWidth()) {
			return true;
		}
		if(y<-Y_WANT_CANCLE || y>getHeight()+Y_WANT_CANCLE) {
			return true;
		}
		return false;
	}

	/**
	 * 根据状态改变 相应的dialog样式
	 * @param state
	 */
	private void changState(int state) {
		if(STATE_CHANGE !=state) {
			STATE_CHANGE = state;
			switch (STATE_CHANGE) {
			case STATE_NORMAL:
				setBackgroundResource(R.drawable.shape_pressed_color);
				setText(R.string.str_recorder_normal);
				break;
			case STATE_RECORDING:
				setBackgroundResource(R.drawable.shape_pressed_color);
				setText(R.string.str_recorder_recoding);
				if(isRecording) {
					dialogManager.recording();
				}
				break;
			case STATE_WANT_CANCLE:
				setBackgroundResource(R.drawable.shape_pressed_color);
				setText(R.string.str_recorder_want_cancle);
				dialogManager.wantCancle();
				break;
			}
		}
	}
  
	/**
	 * 判断文件夹是否存在 不存在就新建
	 */
   public void fileName() {
	   File file = new File("/sdcard/DownLoad/com.huir.download/Record");  
	   if(!file.exists()) {
		   file.mkdir();
	   }
   }
   
   
}  
