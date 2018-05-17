package com.huir.android.chat.keyboard;

import com.huir.android.chat.ChatViewAdapter;
import com.huir.android.entity.Msg;
import com.huir.android.tool.Tool;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;

@SuppressLint("NewApi")
public class ChatEditKeyboard implements OnKeyListener {
	private ChatViewAdapter chatViewAdapter;
	private ListView clist;
	private EditText et_meg;
	
	

	public ChatEditKeyboard(ChatViewAdapter chatViewAdapter, ListView clist, EditText et_meg) {
		super();
		this.chatViewAdapter = chatViewAdapter;
		this.clist = clist;
		this.et_meg = et_meg;
	}



	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {
		String msg = et_meg.getText().toString();
		if(keyCode == KeyEvent.KEYCODE_ENTER) {
			if(!msg.isEmpty()) {
				chatViewAdapter.addDataToAdapter(new Msg(msg, 1));
				chatViewAdapter.notifyDataSetChanged();
			    clist.smoothScrollToPosition(clist.getCount() - 1);
			    et_meg.setText("");
			    }
			}
		return false;
	}

}
