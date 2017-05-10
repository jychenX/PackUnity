package com.example.Tools;

import java.io.File;

import javax.swing.JOptionPane;

import com.example.UIFrame.Main;
import com.example.UIFrame.UIFrame;

public class SeachSystemJAR {

	private static String sharesdkVersion = null;
	
	private static String[] jarList = {"ShareSDK-Alipay-", "ShareSDK-Wechat-Core-", "ShareSDK-Yixin-Core-"};
	
	public static String getSystemPath() throws Throwable{
		String sdkPath = Main.initTools();
		if(sdkPath.equals("")){
			sdkPath = UIFrame.tfSdk.getText();
		}
		StringBuilder sb = new StringBuilder();
		sb.append(SeachAndroidSDK(sdkPath));
		sb.append(File.pathSeparatorChar);
		sharesdkVersion = getShaSDKVersion();
		if(sharesdkVersion != null){
			for(int i=0;i<3;i++){
				String sdkJarPath = Main.getMainClassPath()+File.separator+jarList[i]+sharesdkVersion;
				sb.append(sdkJarPath);
				sb.append(File.pathSeparatorChar);
			}
		}
//		JOptionPane.showMessageDialog(UIFrame.fm, "关联的包："+sb.toString());
		return sb.toString();
	}
	
	private static String SeachAndroidSDK(String sdkPath) throws Throwable{
		String path = null;
		path = sdkPath+"\\platforms\\";
		String[] file = null;
		file = new File(path).list();
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
		//JOptionPane.showMessageDialog(UIFrame.fm, "android.jar的路径："+path.toString());
		return path;
	}
	
	public static String getShaSDKVersion(){
		String str = null;
		String[] file = new File(Main.getMainClassPath()).list();
		for(int i=0; i<=file.length-1;i++){
			if(file[i].toString().contains("ShareSDK-Core")){
				str = file[i].toString().substring(14);
				return str;
			}
		}
		return null;
	}
}
