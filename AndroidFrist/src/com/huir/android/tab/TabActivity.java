package com.huir.android.tab;

import java.util.ArrayList;
import java.util.List;

import com.example.androidfrist.R;
import com.huir.android.tool.KeyboardUtil;

import android.app.Activity;
import android.os.Bundle;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class TabActivity extends Activity implements OnClickListener {
	private ViewPager viewPager; //viewPager
	private List<View> vlist = new ArrayList<View>();
	private PagerAdapter pageAdapter; //pager适配器
	private LinearLayout messageTab;
	private LinearLayout friendTab;
	private LinearLayout appTab;
	private LinearLayout settingsTab;
	private ImageButton chatImage;
	private ImageButton friImage;
	private ImageButton appImage;
	private ImageButton setImage;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//设置窗口没有标题栏
		if(VERSION.SDK_INT>= VERSION_CODES.KITKAT) {  
	          getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);  //透明状态栏  
	          getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);   //透明导航栏  
	     }
		View contentView = getLayoutInflater().inflate(R.layout.activity_main_tab, null);
		setContentView(contentView);
		new KeyboardUtil(this, contentView); 
		initView();
		
		initEvents();
	}
	
	private void initEvents() {
		/**导航栏点击事件**/
		messageTab.setOnClickListener(this);
		friendTab.setOnClickListener(this);
		appTab.setOnClickListener(this);
		settingsTab.setOnClickListener(this);
		
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				int pageSize = viewPager.getCurrentItem();
				resetImage();
				switch (pageSize) {
				case 0:
					chatImage.setImageResource(R.drawable.chat_iamge_press);
					break;
				case 1:
					friImage.setImageResource(R.drawable.friend_iamge_press);
					break;
				case 2:
					appImage.setImageResource(R.drawable.app_iamge_press);
					break;
				case 3:
					setImage.setImageResource(R.drawable.settings_image_press);
					break;
				}
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});
	}

	/**
	 * 初始化控件
	 */
	public void initView() {
		viewPager =(ViewPager) findViewById(R.id.tab_view_pager);
		messageTab = (LinearLayout) findViewById(R.id.navigation_chat);
		friendTab = (LinearLayout) findViewById(R.id.navigation_friend);
		appTab = (LinearLayout) findViewById(R.id.navigation_app);
		settingsTab= (LinearLayout) findViewById(R.id.navigation_settings);
		
		chatImage =  (ImageButton) findViewById(R.id.tab_navigation_chat);
		friImage = (ImageButton) findViewById(R.id.tab_navigation_friend);
		appImage =(ImageButton) findViewById(R.id.tab_navigation_app);
		setImage = (ImageButton) findViewById(R.id.tab_navigation_settings);
		
		LayoutInflater inflater = LayoutInflater.from(this);
		/**ViewPager的页面切换**/
		View message = inflater.inflate(R.layout.tab_message, null);
		View friend = inflater.inflate(R.layout.tab_friend, null);
		View app = inflater.inflate(R.layout.tab_app, null);
		View settings = inflater.inflate(R.layout.tab_settings, null);
		vlist.add(message);
		vlist.add(friend);
		vlist.add(app);
		vlist.add(settings);
		
		/**页面适配器**/
		pageAdapter = new PagerAdapter() {
			@Override
			public void destroyItem(ViewGroup container, int position, Object object) {
				container.removeView(vlist.get(position));
			}
		
			
			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				View view = vlist.get(position);
				container.addView(view);
				return view;
			}
			
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0==arg1;
			}
			
			@Override
			public int getCount() {
				return vlist.size();
			}
		};
		
		viewPager.setAdapter(pageAdapter);
	}

	@Override
	public void onClick(View v) {
		resetImage();
		switch (v.getId()) {
		case R.id.navigation_chat:
			viewPager.setCurrentItem(0);
			chatImage.setImageResource(R.drawable.chat_iamge_press);
			break;
		case R.id.navigation_friend:
			viewPager.setCurrentItem(1);
			friImage.setImageResource(R.drawable.friend_iamge_press);
			break;
		case R.id.navigation_app:
			viewPager.setCurrentItem(2);
			appImage.setImageResource(R.drawable.app_iamge_press);
			break;
		case R.id.navigation_settings:
			viewPager.setCurrentItem(3);
			setImage.setImageResource(R.drawable.settings_image_press);
			break;
		}
	}

	/**
	 * 导航栏图片切换暗色
	 */
	private void resetImage() {
		chatImage.setImageResource(R.drawable.chat_iamge_normal);
		friImage.setImageResource(R.drawable.friend_iamge_normal);
		appImage.setImageResource(R.drawable.app_iamge_normal);
		setImage.setImageResource(R.drawable.settings_image_normal);
	}
}
