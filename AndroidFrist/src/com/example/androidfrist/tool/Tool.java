package com.example.androidfrist.tool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

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
}
