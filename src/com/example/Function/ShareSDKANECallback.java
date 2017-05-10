package com.example.Function;

import java.io.File;

import javax.swing.JOptionPane;

import com.example.Tools.ComplierJaveCore;
import com.example.Tools.Decompression;
import com.example.Tools.FileUtile;
import com.example.Tools.PackToJar;
import com.example.UIFrame.Main;
import com.example.UIFrame.UIFrame;


public class ShareSDKANECallback {
	
	public String newPath = Main.getMainClassPath()+"classFile";
	private String apsharePath = "\\cn\\sharesdk\\ane\\apshare";
	private String wxapiPath = "\\cn\\sharesdk\\ane\\wxapi";
	private String yxapiPath = "\\cn\\sharesdk\\ane\\yxapi";
	public String[] paths={newPath + apsharePath, newPath + wxapiPath, newPath + yxapiPath};

	private String jarName = "ShareSDKANE.jar";
	
	private String ANEjatPath = newPath.substring(0, newPath.lastIndexOf("Android"))+"package\\Android-ARM\\"+jarName;
	
	public void openBat() throws Throwable{
		String batPath = newPath.substring(0, newPath.lastIndexOf("Android")) + "package\\ane_build_android_only.bat";
		
		Runtime.getRuntime().exec("cmd /k start " + batPath);
	}
	
	public void AneJAR(String pathName) throws Throwable{
		File file = new File(newPath);
		if(!file.exists()){
			file.mkdirs();
		}
		//解压jar
		Decompression.unZipFile(ANEjatPath);
		
		//编译
		ComplierJaveCore.initCompiler(pathName);
		JOptionPane.showMessageDialog(UIFrame.fm, "编译成功");
		
		//删除旧的回调包
		for(int i=0;i<paths.length; i++){
			FileUtile.deldeFile(new File(paths[i]));
		}
		FileUtile.deldeFile(new File(newPath+"\\META-INF"));
		
		//打包成JAR
		PackToJar.generateJar(ANEjatPath, newPath);
////		JOptionPane.showMessageDialog(UIFrame.fm, "即将要删除的目录"+newPath);
		FileUtile.deldeFile(new File(newPath));
		openBat();
		JOptionPane.showMessageDialog(UIFrame.fm, "成功生成ANE文件！");
	}
}
