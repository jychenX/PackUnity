package com.example.Tools;

import java.io.File;
import javax.swing.JOptionPane;
import com.example.UIFrame.UIFrame;


public class FileUtile{
	
	/*
	 * 调用次方法来删除已经存在的目录的File对象
	 * **/
	public static void deldeFile(File del){
		
		if(!del.exists()){
			JOptionPane.showMessageDialog(UIFrame.fm, "需要删除的文件或者文件夹不存在！");
			return;
		}
		del.delete();
		if(del.isDirectory()){
			File[] file = del.listFiles();
			if(file != null){
				for(File f: file){
					if(f.isDirectory()){
						deldeFile(f);
						f.delete();
					}else if(f.isFile()){
						f.delete();
					}
					del.delete();
				}
			}
		}
	}
	
}
