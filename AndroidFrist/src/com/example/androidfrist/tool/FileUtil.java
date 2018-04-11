package com.example.androidfrist.tool;

import java.io.File;
import java.io.IOException;

import com.example.androidfrist.ChatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.widget.Toast;

/**
 * 文件工具
 * 
 * @author huir316
 *
 */
public class FileUtil {
	private String SDCardRoot;
	private String sprit = File.separator;

	public FileUtil() {
		// 得到当前外部存储设备的目录
		SDCardRoot = Environment.getExternalStorageDirectory() + sprit;
	}

	/**
	 * 创建文件
	 * 
	 * @param fileName
	 * @param dir
	 * @return
	 * @throws IOException
	 */
	public File createFlie(String fileName) throws IOException {
		File file = new File(SDCardRoot  + sprit + fileName);
		file.createNewFile();
		return file;
	}

	/**
	 * 创建目录
	 * 
	 * @param dir
	 * @return
	 */
	public File createDir(String dir) {
		File file = new File(SDCardRoot + dir);
		file.mkdir();
		return file;
	}

	// 判断文件是否存在
	public boolean isFileExist(String fileName, String dir) {
		File file = new File(SDCardRoot + dir + sprit + fileName);
		return file.exists();
	}

	

	
}
