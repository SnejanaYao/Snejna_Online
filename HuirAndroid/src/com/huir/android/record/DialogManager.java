package com.huir.android.record;

import com.example.androidfrist.R;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 录音对话框界面显示
 */
public class DialogManager {
	private Dialog dialog;
	private Context context;
	private RelativeLayout layout_one;
	private TextView notice_cancle_one;// 正在录音时
	
	private RelativeLayout layout_two;
	private TextView notice_cancle_two; //关闭录音时
	
	private RelativeLayout layout_three;
	private TextView notice_cancle_three; //录音时间太短时
	
	public DialogManager(Context context) {
		this.context = context;
	}
	
	public void showDialog() {
		dialog = new Dialog(context,R.style.Theme_audioDialog);
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.dialog_manager, null);
		dialog.setContentView(view);
		layout_one = (RelativeLayout)dialog.findViewById(R.id.dm_rl_bg);
		notice_cancle_one=(TextView)dialog.findViewById(R.id.dm_tv_txt);
		
		layout_two=(RelativeLayout)dialog.findViewById(R.id.dm_rl_bg2);
		notice_cancle_two=(TextView)dialog.findViewById(R.id.dm_tv_txt2);
		
		layout_three=(RelativeLayout)dialog.findViewById(R.id.dm_rl_bg3);
		notice_cancle_three=(TextView)dialog.findViewById(R.id.dm_tv_txt3);
		
		dialog.setCancelable(false);
		dialog.show();
	}
	
	public void recording() {
		if(dialog !=null && dialog.isShowing()) {
			layoutOne();
			layoutTwoGone();
			layoutThreeGone();
			layout_one.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.yuyin_voice_1));
			notice_cancle_one.setText(R.string.str_recorder_notice_cancle);
		}
	}
	
	public void wantCancle() {
		if(dialog !=null && dialog.isShowing()) {
			layoutTwo();
			layoutOneGone();
			layoutThreeGone();
			layout_two.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.yuyin_cancel));
			notice_cancle_two.setText(R.string.str_recorder_notice_cantsend);
		}
	}
	
	public void tooShort() {
		if(dialog !=null && dialog.isShowing()) {
			layoutThree();
			layoutOneGone();
			layoutTwoGone();
			layout_one.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.yuyin_gantanhao));
			notice_cancle_one.setText(R.string.str_recorder_too_short);
		}
	}
	
	public void dismissDialog() {
		if(dialog !=null && dialog.isShowing()) {
			dialog.dismiss();
			dialog = null;
		}
	}
	
	/**
	 * 改变音量
	 * @param lev 1-5
	 */
	public int updateLevel(int lev) {
		if (lev > 0 && lev < 6) {

        } else {
        	lev = 5;
        }
		if(dialog !=null && dialog.isShowing()) {
			int resid = context.getResources().getIdentifier("yuyin_voice_"+lev, "drawable", context.getPackageName());
			layout_one.setBackgroundResource(resid);
			notice_cancle_one.setText(R.string.str_recorder_notice_cancle);
		}
		return lev;
	}
	
	
	public void layoutOne() {
		layout_one.setVisibility(View.VISIBLE);
		notice_cancle_one.setVisibility(View.VISIBLE);
	}
	
	public void layoutOneGone() {
		layout_one.setVisibility(View.GONE);
		notice_cancle_one.setVisibility(View.GONE);
	}
	
	public void layoutTwo() {
		layout_two.setVisibility(View.VISIBLE);
		notice_cancle_two.setVisibility(View.VISIBLE);
	}
	
	public void layoutTwoGone() {
		layout_two.setVisibility(View.GONE);
		notice_cancle_two.setVisibility(View.GONE);
	}
	
	public void layoutThree() {
		layout_three.setVisibility(View.VISIBLE);
		notice_cancle_three.setVisibility(View.VISIBLE);
	}
	
	public void layoutThreeGone() {
		layout_three.setVisibility(View.GONE);
		notice_cancle_three.setVisibility(View.GONE);
	}
}
