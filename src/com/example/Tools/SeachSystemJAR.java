package com.example.Tools;

import java.io.File;
import java.util.Iterator;
import java.util.Map;

import com.example.UIFrame.Main;

public class SeachSystemJAR {

	private static boolean isFoundJDK = false;
	private static boolean isFoundSDK = false;
	private static String sharesdkVersion = null;
	
	private static String[] jarList = {"ShareSDK-Alipay-", "ShareSDK-Wechat-Core-", "ShareSDK-Yixin-Core-"};
	
	public static String getSystemPath(){
		
		StringBuilder sb = new StringBuilder();
		Map<String, String> map = System.getenv();
		for(Iterator<String> it = map.keySet().iterator();it.hasNext();){
			String key =it.next();
			if(key.equals("JAVA_HOME")){
				sb.append(map.get(key).toString()+"\\jre\\lib\\rt.jar");
				sb.append(File.pathSeparatorChar);
				sb.append(map.get(key).toString()+"\\lib\\tools.jar");
				sb.append(File.pathSeparatorChar);
				isFoundJDK = true;
			}else if(key.equals("ANDROID_HOME")){
				sb.append(SeachAndroidSDK(map.get(key).toString()));
				sb.append(File.pathSeparatorChar);
				isFoundJDK = true;
			}
		}
		
		if(!isFoundJDK){
			
		}
		
		sharesdkVersion = getShaSDKVersion();
		if(sharesdkVersion != null){
			for(int i=0;i<3;i++){
				String sdkJarPath = "F:\\libs"+File.separator+jarList[i]+sharesdkVersion;
				sb.append(sdkJarPath);
				sb.append(File.pathSeparatorChar);
			}
		}
		
		return sb.toString();
	}
	
	private static String SeachAndroidSDK(String sdkPath){
		String path = null;
		path = sdkPath+"\\platforms\\";
		String[] file = new File(path).list();
		int i=0;
		for(;;){
			if(i == file.length-1){
				break;
			}else{
				if(new File(path+file[i]+"\\android.jar").exists()){
					path = path+file[i]+"\\android.jar";
					break;
				}
			}
			i++;
		}
		return path;
	}
	
	public static String getShaSDKVersion(){
		String str = null;
		String[] file = new File("F:\\libs").list();
		for(int i=0; i<=file.length-1;i++){
			if(file[i].toString().contains("ShareSDK-Core")){
				str = file[i].toString().substring(14);
				return str;
			}
			
		}
		return null;
	}
	
	
	//debug运行测试的依赖包方法
//	public static String getJARPath(String ... jarPath){
//		StringBuilder sb = new StringBuilder();
//		for(String str : jarPath){
//			
//			String[] filenames = new File(str).list();
//			for(int i = 0; i < filenames.length; i++) {
//				sb.append(str+"\\");//文件路径
//				sb.append(filenames[i]); //文件名
//			    sb.append(File.pathSeparatorChar); //文件间隔符
//			}
//		}
//		return sb.toString();
//	} 
}
