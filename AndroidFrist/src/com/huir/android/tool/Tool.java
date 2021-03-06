package com.huir.android.tool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * 弹框工具类
 * @author huir316
 *
 */
public class Tool {
	/**
	 * 用户名是否符合规则...
	 * @param isCheck_name
	 * @return boolean
	 */
	public static  boolean checkUserName(String isCheck_name) {
		Pattern userName = Pattern.compile("^[\\\\u4e00-\\\\u9fa5_a-zA-Z0-9-]{1,16}$");
		Matcher m_userName = userName.matcher(isCheck_name);
		return m_userName.matches();
	}

	/**
	 * 检查密码是否符合规则
	 * @param isCheck_pwd
	 * @return boolean
	 */
	public static boolean checkUserPwd(String isCheck_pwd) {
		Pattern userPwd = Pattern.compile("^([A-Z]|[a-z]|[0-9]|[`~!@#$%^&*()+=|{}':;',\\\\\\\\[\\\\\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]){6,20}$");
		Matcher m_userPwd = userPwd.matcher(isCheck_pwd);
		return m_userPwd.matches();
	}
	
	/**
	 * 登录弹框
	 * @param context
	 */
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

	/**
	 * 登录成功弹框
	 * @param context
	 */
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
	
	/**
	 * 登录失败弹框
	 * @param context
	 */
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

	/**
	 * 注册成功弹框
	 * @param context
	 */
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
	
	
	 // 显示输入法  
    public static void showInputMethod(View view,Context context) {  
        InputMethodManager inputMethodManager = (InputMethodManager) context.getApplicationContext()  
                .getSystemService(Context.INPUT_METHOD_SERVICE);  
        //同时再使用该方法之前，view需要获得焦点，可以通过requestFocus()方法来设定。  
        view.requestFocus();  
        inputMethodManager.showSoftInput(view, inputMethodManager.SHOW_FORCED);  
    }  
}
