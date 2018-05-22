package com.huir.android.chat;

import java.util.ArrayList;
import java.util.List;

import com.example.androidfrist.R;
import com.huir.android.entity.Msg;
import com.huir.android.record.MediaManager;
import com.huir.android.tool.CommonsUtils;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class ChatViewAdapter extends BaseAdapter {
	private ViewHolder viewHolder;
	private List<Msg> datas = new ArrayList<Msg>();
	private Context context;
	private int pos = -1;// 标记当前录音索引，默认没有播放任何一个 -1
	private AnimationDrawable animation; //动画
	public List<AnimationDrawable> animationDrawables;
	
	public ChatViewAdapter() {
	}
	
	public ChatViewAdapter(List<Msg> datas, Context context) {
		super();
		this.datas = datas;
		this.context = context;
	}

	//给adapter添加数据
	public void addDataToAdapter(Msg e) {
	        datas.add(e);
	}
	
	@Override
	public int getCount() {
		return datas.size();
	}

	@Override
	public Object getItem(int position) {
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		if(convertView ==null) {
			 convertView=LayoutInflater.from(context).inflate(R.layout.item_activity_chat,null);
	         viewHolder = new ViewHolder(convertView);  
	         convertView.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		final Msg entity = datas.get(position);
		int type= entity.getType();
		String msg =  entity.getMsg();
		if(type==1){
			l_talk(msg);
		}
		else  if(type==2) {
			r_talk(msg);
		}else if(type==3) {
			int second = entity.getSecond();
			boolean isPlayed= entity.isPlayed();
			l_voice(second,isPlayed);
			RelativeLayout.LayoutParams ps = (RelativeLayout.LayoutParams)viewHolder.chat_left_voiceLine.getLayoutParams();
			ps.width = CommonsUtils.getVoiceLineWight(context, second);
			viewHolder.chat_left_voiceLine.setLayoutParams(ps);
			viewHolder.chat_left_voiceLine.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	viewHolder.chat_left_red.setVisibility(View.GONE);  //被点击则红点消失
	            	notifyDataSetChanged();
	            	entity.setPlayed(true);
	            	if(pos == position) {
	            		if(entity.isPlaying()) {
	            			entity.setPlaying(false);
	            			MediaManager.release();
	                        animation.stop();
	                        animation.selectDrawable(0);//reset
	                        return;
	            		}else {
	            			entity.setPlaying(true);
	            		}
	            		entity.setPlaying(false);
	            	}
	            	animation = (AnimationDrawable)viewHolder.sign.getBackground(); //播放动画
	            	//resetAnim(animation);//重置动画再播放
	            	animation.start();
	            	pos = position;
	                entity.setPlaying(true);

	                 //播放前重置。
	                MediaManager.release();
	            	MediaManager.playSound(entity.getPath(),new MediaPlayer.OnCompletionListener() {
						@Override
						public void onCompletion(MediaPlayer mp) {
							animation.selectDrawable(0);//显示动画第一帧
							animation.stop();

                            //播放完毕，当前播放索引置为-1。
                            pos = -1;
						}
					});
	            }
	        });
		}
		return convertView;
	}
	
	public void l_talk(String msg) {
       viewHolder.left.setVisibility(View.VISIBLE);
       viewHolder.right.setVisibility(View.INVISIBLE);
       viewHolder.voiceLeft.setVisibility(View.INVISIBLE);
       
       viewHolder.text_left.setText(msg);//发送消息
	}
	
	public void r_talk(String msg) {
	   viewHolder.right.setVisibility(View.VISIBLE);
       viewHolder.left.setVisibility(View.INVISIBLE);
       viewHolder.voiceLeft.setVisibility(View.INVISIBLE);
       
       viewHolder.text_right.setText(msg); //发送消息
	}
	
	public void l_voice(int second,boolean isPlayed) {
		viewHolder.left.setVisibility(View.INVISIBLE);
	    viewHolder.right.setVisibility(View.INVISIBLE);
	    viewHolder.voiceLeft.setVisibility(View.VISIBLE);
	    viewHolder.vc_left.setText(second<=0 ? 1 +" ''" : second+" '' ");
	   if(isPlayed) { //未读与已读的红点状态
		   viewHolder.chat_left_red.setVisibility(View.GONE);
	   }else {
		   viewHolder.chat_left_red.setVisibility(View.VISIBLE);
	   }
	}
	
 class ViewHolder {
       public View rootView;
       public TextView text_left;
       public LinearLayout left; //左侧文字发送
       
       public TextView text_right;
       public LinearLayout right; //右侧文字发送
      
       public TextView vc_left;  //语音秒数
       public ImageView chat_left_red; //小红点
       public ImageView chat_left_voiceLine; //语音条
       public LinearLayout voiceLeft; //左侧语音发送
       public LinearLayout sign; //语音标志
       
       public ViewHolder(View rootView) {
       	    this.rootView = rootView;
            this.text_left = (TextView) rootView.findViewById(R.id.text_left);
            this.left = (LinearLayout) rootView.findViewById(R.id.chat_left);
            this.text_right = (TextView) rootView.findViewById(R.id.text_right);
            this.right = (LinearLayout) rootView.findViewById(R.id.chat_right);
            
            this.voiceLeft = (LinearLayout)rootView.findViewById(R.id.chat_left_vc);
            this.vc_left = (TextView)rootView.findViewById(R.id.chat_left_voicetime);
            this.chat_left_red=(ImageView)rootView.findViewById(R.id.chat_left_red);
            this.chat_left_voiceLine = (ImageView)rootView.findViewById(R.id.chat_left_voiceLine);
            this.sign = (LinearLayout)rootView.findViewById(R.id.chat_left_singer);
       }
 	}
 	
 	private void resetAnim(AnimationDrawable animationDrawable) {
     if (!this.animationDrawables.contains(animationDrawable)) {
    	 this.animationDrawables.add(animationDrawable);
     }
     for (AnimationDrawable ad : this.animationDrawables) {
         ad.selectDrawable(0);
         ad.stop();
     }
 }
}


