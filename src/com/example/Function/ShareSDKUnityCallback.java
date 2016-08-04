package com.example.Function;

import java.io.File;

import javax.swing.JOptionPane;

import com.example.Tools.ComplierJaveCore;
import com.example.Tools.FileUtile;
import com.example.Tools.PackToJar;
import com.example.UIFrame.Main;
import com.example.UIFrame.UIFrame;


public class ShareSDKUnityCallback {
	
	
	public String newPath = Main.getMainClassPath()+"classFile";

	private String jarName = "DemoCallback.jar";
	
	public void UnityJAR(String pathName) throws Throwable{
		File file = new File(newPath);
		if(!file.exists()){
			file.mkdirs();
		}
		//编译
		ComplierJaveCore.initCompiler(pathName);
//		JOptionPane.showMessageDialog(UIFrame.fm, "编译成功");
		//打包成JAR
		PackToJar.generateJar(Main.getMainClassPath()+jarName, newPath);
//		JOptionPane.showMessageDialog(UIFrame.fm, "即将要删除的目录"+newPath);
		FileUtile.deldeFile(new File(newPath));
		JOptionPane.showMessageDialog(UIFrame.fm, "成功生成jar包，已替换旧的包！");
	}
}




