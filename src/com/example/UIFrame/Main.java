package com.example.UIFrame;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.Map;

import com.example.Tools.FileUtile;
import com.example.Tools.SeachSystemJAR;



public class Main{

	public static void main(String[] args) {
		UIFrame frame = new UIFrame();
		frame.initFrame(initTools());
	}
	
	/*
	 *获取当前Main类的绝对路径 
	 **/
	public static String getMainClassPath(){
		String path = Main.class.getClassLoader().getResource("").getPath();
		File file = new File(path);
		path = file.getAbsolutePath();
		try {
			path = URLDecoder.decode(path, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println(path+File.separator);
		return path+File.separator;
	}
	
	public static int initTools(){
		int i = 0;
		Map<String, String> map = System.getenv();
		for(Iterator<String> it = map.keySet().iterator();it.hasNext();){
			String key =it.next();

			if(key.equals("JAVA_HOME")){
				i++;
			}else if(key.equals("ANDROID_HOME")){
				i++;
			}
		}
		return i;
	}
}
