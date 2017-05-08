package com.example.Function;

import java.io.File;

import javax.swing.JOptionPane;

import com.example.Tools.ComplierJaveCore;
import com.example.Tools.FileUtile;
import com.example.Tools.PackToJar;
import com.example.UIFrame.Main;
import com.example.UIFrame.UIFrame;


public class ShareSDKUnityCallback {
	
	//测试使用的路径
//	public String oldPath="F:"+File.separator+"Unity快速修改包名工具"+
//			File.separator+"New-Unity-For-ShareSDK-master"+
//			File.separator+"Android_Java_Demo"+
//			File.separator+"src"+
//			File.separator+"cn"+
//			File.separator+"sharesdk"+
//			File.separator+"demo"+File.separator;
	
	public String oldPath=Main.getMainClassPath().substring(0, Main.getMainClassPath().lastIndexOf("\\libs\\"))+
			File.separator+"src"+
			File.separator+"cn"+
			File.separator+"sharesdk"+
			File.separator+"demo"+File.separator;
	
	public String newPath = Main.getMainClassPath()+"customer"+File.separator;
	
	private String apsharePath = "apshare";
	private String wxapiPath = "wxapi";
	private String yxapiPath = "yxapi";
	public String[] paths={apsharePath, wxapiPath, yxapiPath};
	
	private String apshare = "ShareEntryActivity.java";
	private String wxapi = "WXEntryActivity.java";
	private String yxapi = "YXEntryActivity.java";
	public String[] files={apshare, wxapi, yxapi};
	
	private String jarName = "DemoCallback.jar";
	
	public void UnityFileCatalog(String pathName){
		for(int i=0; i<paths.length; i++){
			//新建和复制文件
			FileUtile.pathTofile(oldPath+paths[i], 
					newPath+paths[i], pathName, false);
			//修改代码里面的包名
			FileUtile.pathTofile(oldPath+paths[i]+File.separator+files[i], 
					newPath+paths[i]+File.separator+files[i], pathName, true);
		}
	}
	
	public void UnityJAR(String pathName) throws Throwable{
		for(int i=0; i<paths.length; i++){
			ComplierJaveCore.complier(newPath+paths[i], newPath+"CLASS"+File.separator);
		}
		//打包成JAR
		PackToJar.generateJar(Main.getMainClassPath()+jarName,newPath+"CLASS");
		JOptionPane.showMessageDialog(UIFrame.fm, "即将要删除的目录"+newPath.substring(0, newPath.lastIndexOf("\\")));
		FileUtile.deldeFile(new File(newPath.substring(0, newPath.lastIndexOf("\\"))));
		
	}
}




