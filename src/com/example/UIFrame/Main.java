package com.example.UIFrame;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.Map;

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
		return path+File.separator;
	}
	
	public static String initTools(){
		String path = "";
		Map<String, String> map = System.getenv();
		for(Iterator<String> it = map.keySet().iterator();it.hasNext();){
			String key =it.next();
			System.out.println(key+" ： "+map.get(key));
			if(key.equals("ANDROID_HOME")){
				path = map.get(key).toString();
			}
		}
		return path;
	}
}
