package com.example.androidfrist;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import com.example.androidfrist.entity.Msg;

import android.R.color;
import android.app.Activity;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ChatActivity extends Activity {
	private String picUrl ="http://img01.mifile.cn/images/accs/xmjsb_11.jpg";  
	private List<Msg> datas = new ArrayList<Msg>();
	private EditText et_meg;
	private Button left,right,download;
	private GradientDrawable gd;
	private ListView clist;
    private ChatViewAdapter  chatViewAdapter;
    private ProgressDialog dialog;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//设置窗口没有标题栏  
		setContentView(R.layout.activity_chat);
		et_meg = (EditText)findViewById(R.id.et_meg);
		et_meg.addTextChangedListener(new changeBtn());
		
		left=(Button)findViewById(R.id.btn_left);
		left.setOnClickListener(new onClick());
		
		right=(Button)findViewById(R.id.btn_right);
		right.setOnClickListener(new onClick());
		
		download=(Button)findViewById(R.id.btn_download);
		download.setOnClickListener(new onClick());
		
		clist = (ListView)findViewById(R.id.chatList);
		chatViewAdapter = new  ChatViewAdapter();
		clist.setAdapter(chatViewAdapter);	
		 
		
	}
	
	class onClick implements OnClickListener{

		@Override
		public void onClick(View v) {
			String msg = et_meg.getText().toString().trim();
			//ChatViewAdapter chatViewAdapter = new ChatViewAdapter();
			switch (v.getId()) {
			case R.id.btn_left:
				chatViewAdapter.addDataToAdapter(new Msg(msg, 1));
				chatViewAdapter.notifyDataSetChanged();
				
				int strokeColor = Color.parseColor("#B0C4DE");//边框颜色
			    int fillColor = Color.parseColor("#B0C4DE");//内部填充颜色

			    gd = new GradientDrawable();//创建drawable
			    gd.setColor(fillColor);
			    gd.setCornerRadius(50);
			    gd.setStroke(2, strokeColor);
			    left.setBackgroundDrawable(gd);
			    clist.smoothScrollToPosition(clist.getCount() - 1);
			    et_meg.setText("");
				break;
			case R.id.btn_right:
			    chatViewAdapter.addDataToAdapter(new Msg(msg, 2));
				chatViewAdapter.notifyDataSetChanged();
				
				int right_strokeColor = Color.parseColor("#B0C4DE");//边框颜色
			    int right_fillColor = Color.parseColor("#B0C4DE");//内部填充颜色

			    GradientDrawable right_gd = new GradientDrawable();//创建drawable
			    right_gd.setColor(right_fillColor);
			    right_gd.setCornerRadius(50);
			    right_gd.setStroke(2, right_strokeColor);
			    right.setBackgroundDrawable(right_gd);
			    clist.smoothScrollToPosition(clist.getCount() - 1);
			    et_meg.setText("");
				break;
			case R.id.btn_download:
				/*//文件下载  (downLoadManager)
				String url= "http://img.zcool.cn/community/018d4e554967920000019ae9df1533.jpg@900w_1l_2o_100sh.jpg";//下载地址
				DownloadManager.Request request= new DownloadManager.Request(Uri.parse(url)); //获取DownloadManager的下载
				request.setTitle("Andorid下载管理");
				// 如果为了让它运行，你必须用android3.2编译你的应用程序
				if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.HONEYCOMB) {
				    request.allowScanningByMediaScanner();//表示允许MediaScanner扫描到这个文件，默认不允许。
				    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
				}
				request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS+"/com.android.first/", "test.jpg");
				// 获得下载服务和队列文件
				DownloadManager manager= (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
				Long id = manager.enqueue(request);
                listener(id);*/
				
				dialog = new ProgressDialog(ChatActivity.this);
				dialog.setMessage("正在下载...");
				dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
				DownloadFile downloadFile = new DownloadFile();
				downloadFile.execute("http://www.wandoujia.com/apps/com.xingin.xhs/binding");
				break;
			}
		}
		
	}
	

	/**
	 * downloadManager 监听事件
	 * @param Id
	 */
    private void listener(final long Id) {  
        // 注册广播监听系统的下载完成事件。  
        IntentFilter intentFilter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);  
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {  
            @Override  
            public void onReceive(Context context, Intent intent) {  
                long ID = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);  
                if (ID == Id) {  
                	Toast toast=Toast.makeText(ChatActivity.this,"下载文件目录在: 手机存储/"+Environment.DIRECTORY_DOWNLOADS+"/com.android.first",Toast.LENGTH_SHORT);
                	toast.show();
                }  
            }  
        };  
        registerReceiver(broadcastReceiver, intentFilter);  
    } 
	
	
	
	/**
	 * 当输入文字时 改变按钮颜色
	 * @author huir316
	 *
	 */
	class changeBtn implements TextWatcher{
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				left=(Button)findViewById(R.id.btn_left);
				right=(Button)findViewById(R.id.btn_right);
				left.setBackgroundColor((Color.parseColor("#6495ED")));
			    int strokeColor = Color.parseColor("#6495ED");//边框颜色
			    int fillColor = Color.parseColor("#6495ED");//内部填充颜色
			    

			    gd = new GradientDrawable();//创建drawable
			    gd.setColor(fillColor);
			    gd.setCornerRadius(50);
			    gd.setStroke(2, strokeColor);
			    left.setBackgroundDrawable(gd);
			    right.setBackgroundDrawable(gd);

			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
			
		}
	}
	
	
	public class ChatViewAdapter extends BaseAdapter {
		 private ViewHolder viewHolder;
		 public ChatViewAdapter() {
			// TODO Auto-generated constructor stub
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
		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView ==null) {
				 convertView=LayoutInflater.from(ChatActivity.this).inflate(R.layout.activity_chat_list,null);
		         viewHolder = new ViewHolder(convertView);  
		         convertView.setTag(viewHolder);
			}else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
			int type= datas.get(position).getType();
			String msg = datas.get(position).getMsg();
			if(type==1){
				viewHolder.text_left.setText(msg);
		        viewHolder.left.setVisibility(View.VISIBLE);
		        viewHolder.right.setVisibility(View.INVISIBLE);
			}
			else  if(type==2) {
				viewHolder.text_right.setText(msg);
				viewHolder.right.setVisibility(View.VISIBLE);
		        viewHolder.left.setVisibility(View.INVISIBLE);
			}
			return convertView;
		}
		
	  class ViewHolder {
	        public View rootView;
	        public TextView text_left;
	        public LinearLayout left;
	        public TextView text_right;
	        public LinearLayout right;

	        public ViewHolder(View rootView) {
	        	 this.rootView = rootView;
	             this.text_left = (TextView) rootView.findViewById(R.id.text_left);
	             this.left = (LinearLayout) rootView.findViewById(R.id.chat_left);
	             this.text_right = (TextView) rootView.findViewById(R.id.text_right);
	             this.right = (LinearLayout) rootView.findViewById(R.id.chat_right);
	        }
	  	}
	}
	
	
	private class DownloadFile extends AsyncTask<String, Integer, String> {
	    @Override
	    protected String doInBackground(String... sUrl) {
	        try {
	        	URL url = new URL(sUrl[0]);//建立url
	        	URLConnection connection = url.openConnection(); //获取连接
	        	connection.connect();//打开连接
	        	int FileLength = connection.getContentLength(); //获取文件总大小
	        	BufferedInputStream in = new BufferedInputStream(url.openStream());//建立输入流 放入从连接获取的输入流
	        	FileOutputStream out = new FileOutputStream("/sdcard/xiaohongshu.apk"); //获取 文件输出流 写入手机的具体位置  
	        	byte[] read = new byte[1024]; //准备byte
	        	int count;
	        	int total = 0;    //累加读取的数据  为 下面进度条计算百分比做准备
	        	while(( count= in.read(read)) != -1) {
	        		total += count;//累加读取到数据
	        		publishProgress((total *100)/FileLength);  //更新进度条
	        		out.write(read, 0, count);
	        	}
	        	 dialog.dismiss();
	             out.flush();
		         out.close();
		         in.close();
	        } catch (Exception e) {
	        }
	        return null;
	    }

	    @Override
	    protected void onPreExecute() {
	        super.onPreExecute();
	        dialog.show();
	    }

	    @Override
	    protected void onProgressUpdate(Integer... progress) {
	        super.onProgressUpdate(progress);
	        dialog.setProgress(progress[0]);
	    }
	}
}