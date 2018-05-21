package com.huir.android.tab;

import java.util.ArrayList;
import java.util.List;

import com.example.androidfrist.R;
import com.huir.android.entity.Msg;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;

public class ShowUsersAdapater extends BaseAdapter {
	private ViewHolder viewHolder;
	private List<Msg> datas = new ArrayList<Msg>();
	private Context context;
	private int pos = -1;// 标记当前录音索引，默认没有播放任何一个 -1
	@Override
	public int getCount() {
		return 0;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		RelativeLayout layout = (RelativeLayout) convertView.findViewById(R.layout.item_activity_show_users);
		return null;
	}
	
	class ViewHolder {
		
	}

}
