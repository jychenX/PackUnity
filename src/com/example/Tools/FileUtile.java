package com.example.Tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import javax.swing.JOptionPane;

import com.example.UIFrame.UIFrame;


public class FileUtile{
	
	/*
	 *调用此类的方发必现执行此方法 
	 **/
	public static void pathTofile(String oldPath, String newPath, String packName, Boolean isNeedChance) {
		try {
			File oldFile = new File(oldPath);
			File newFile = new File(newPath);
			if(isNeedChance){
				chancePackname(oldFile, newFile, packName);
			}else{
				copyFolder(oldFile, newFile);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 调用次方法来删除已经存在的目录的File对象
	 * **/
	public static void deldeFile(File del){
		
		if(!del.exists()){
			JOptionPane.showMessageDialog(UIFrame.fm, "需要删除的文件或者文件夹不存在！");
			return;
		}
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
	
	/*
	 * 此方法用来复制文件或者文件夹
	 * File oldFile：传入需要复制的文件或者文件夹的File对象
	 * File newFile：传入目标文件夹（即将要保存文件的目录）的File对象
	 **/
	public static void copyFolder(File oldFile, File newFile) throws Exception {
		if(newFile.exists()){
			deldeFile(newFile);
		}
		try  {   
			if(oldFile.isFile()){
				FileInputStream input = new FileInputStream(oldFile);
				FileOutputStream output = new FileOutputStream(newFile);
				byte[] b = new byte[1024 * 5];
				int len;
				while ((len = input.read(b)) != -1)
					{
						output.write(b, 0, len);
					}
					output.flush();
					output.close();
					input.close();
			}else{
			if(!newFile.exists()){
				newFile.mkdirs();
			}
			File[] fileList = oldFile.listFiles();
			for(int i=0; i<fileList.length; i++){
				File f = fileList[i];
				copyFolder(f, new File(newFile, f.getName()));
			}
		}
		}
		catch (Exception e)  {
			throw(e);
			
		}
	}
	
	/*
	 * 此方法用来修改ShareSDK里面微信、易信和支付宝的回调activity的package名字
	 */
	public static void chancePackname(File oldFile, File newFile, String packName){
		try {
			FileInputStream fis = new FileInputStream(oldFile);
		    BufferedReader br = new BufferedReader(new InputStreamReader(fis, "utf-8"));
		    FileOutputStream fos = new FileOutputStream(newFile);
		    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"));
		    String line = br.readLine();
		    while (line != null) {
		    	if (line.contains("package cn.sharesdk.demo.wxapi;")){
		    		line = "package " + packName + ".wxapi;";
		    	}else if (line.contains("package cn.sharesdk.demo.yxapi;")){
		    		line = "package " + packName + ".yxapi;";
		    	}else if (line.contains("package cn.sharesdk.demo.apshare;")) {
		    		line = "package " + packName + ".apshare;";
		    	}else if(line.contains("//")){
		    		line= "";
		    	}
		    	bw.write(line + "\n");
		    	line = br.readLine();
		    }
		    bw.flush();
		    br.close();
		    bw.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
