package com.example.androidfrist.tool;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.androidfrist.ChatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.AsyncTask;
import android.os.Build;

public class Tool {
	public static  boolean checkUserName(String isCheck_name) {
		Pattern userName = Pattern.compile("^[\\\\u4e00-\\\\u9fa5_a-zA-Z0-9-]{1,16}$");
		Matcher m_userName = userName.matcher(isCheck_name);
		return m_userName.matches();
	}

	public static boolean checkUserPwd(String isCheck_pwd) {
		Pattern userPwd = Pattern.compile("^([A-Z]|[a-z]|[0-9]|[`~!@#$%^&*()+=|{}':;',\\\\\\\\[\\\\\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]){6,20}$");
		Matcher m_userPwd = userPwd.matcher(isCheck_pwd);
		return m_userPwd.matches();
	}
	
	public static void showDialog(Context context) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("系统通知");
		builder.setMessage("正在登录请稍候...");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {

			}
		});
		AlertDialog dialog = builder.create();
		dialog.show();
	}

	public static void showSuccessDialog(Context context) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("系统通知");
		builder.setMessage("登陆成功！");
		builder.setPositiveButton("我知道了", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {

			}
		});
		AlertDialog dialog = builder.create();
		dialog.show();
	}
	
	public static void showDownLoadDialog(Context context) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("系统通知");
		builder.setMessage("开始下载");
		builder.setPositiveButton("我知道了", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {

			}
		});
		AlertDialog dialog = builder.create();
		dialog.show();
	}

	public static void showFaildDialog(Context context) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("登录信息");
		builder.setMessage("用户名或密码错误 请检查您填写是否正确！");
		builder.setPositiveButton("我知道了", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {

			}
		});
		AlertDialog dialog = builder.create();
		dialog.show();
	}
	
	public static void showRegDialog(Context context) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("系统通知");
		builder.setMessage("正在注册请稍候...");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {

			}
		});
		AlertDialog dialog = builder.create();
		dialog.show();
	}

	public static void showRegSuccessDialog(Context context) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("系统通知");
		builder.setMessage("注册成功！");
		builder.setPositiveButton("我知道了", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {

			}
		});
		AlertDialog dialog = builder.create();
		dialog.show();
	}
	

	
	/**
	 * 弹框显示进度条
	 */
	public static void showProgressDialogs(Context context) {
		 final ProgressDialog downLoadDialog = new ProgressDialog(context);
		 downLoadDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL); //设置进度条样式
		 downLoadDialog.setCanceledOnTouchOutside(false); //设置点击Dialog外部是否取消Dialog进度条
		 downLoadDialog.setTitle("提示");
		 downLoadDialog.setMax(100);
		 downLoadDialog.show();
		 new Thread(new Runnable() {
			@Override
			public void run() {
				int i=0;
				 while(i<100) {
					 try {
						Thread.sleep(100);
						downLoadDialog.incrementProgressBy(1);
						i++; //每循环一次 i自增;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				 }
				 downLoadDialog.dismiss();
			}
		}).start();
	}
	
	
	/**
	 * @ param上下文用来检查设备的版本和DownloadManager信息
	 * @如果下载管理器可以用，则返回true
	 */
	public static boolean isDownloadManagerAvailable(Context context) {
	   try {
	       if (Build.VERSION.SDK_INT< Build.VERSION_CODES.GINGERBREAD) {
	           return false;
	       }
	       Intent intent= new Intent(Intent.ACTION_MAIN);
	        intent.addCategory(Intent.CATEGORY_LAUNCHER);
	        intent.setClassName("com.android.providers.downloads.ui", "com.android.providers.downloads.ui.DownloadList");
	       List<ResolveInfo> list= context.getPackageManager().queryIntentActivities(intent,
	               PackageManager.MATCH_DEFAULT_ONLY);
	       return list.size() > 0;
	   } catch (Exception e) {
	       return false;
	   }
	}
}
