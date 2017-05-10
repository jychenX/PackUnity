package com.example.Function;

import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import com.example.Tools.ComplierJaveCore;
import com.example.Tools.FileUtile;
import com.example.Tools.PackToJar;
import com.example.UIFrame.Main;
import com.example.UIFrame.UIFrame;


public class ShareSDKUnityCallback {
	
	
	private String newPath = Main.getMainClassPath()+"classFile";
	private String saveJarPath = Main.getMainClassPath()+"CallbackJarFile"+File.separator;
	
	public void UnityJAR(String packName, JTextArea log, boolean isLast) throws Throwable{
		File file = new File(newPath);
		if(!file.exists()){
			file.mkdirs();
		}
		file = new File(saveJarPath);
		if(!file.exists()){
			file.mkdirs();
		}
		//编译
		ComplierJaveCore.initCompiler(packName);
		String jarName = packName+".jar";
		//JOptionPane.showMessageDialog(UIFrame.fm, "编译成功");
		//打包成JAR
		PackToJar.generateJar(saveJarPath+jarName, newPath);
		log.append("回调包："+packName+"   ---编译成功！\n");
		FileUtile.deldeFile(new File(newPath));
//		JOptionPane.showMessageDialog(UIFrame.fm, "即将要删除的目录"+newPath);
		if(isLast){
			log.append("---- 全部编译成功！----\n");
			JOptionPane.showMessageDialog(UIFrame.fm, "成功生成全部jar包，请查看同级的CallbackJarFile文件夹！");
		}
	}
}




