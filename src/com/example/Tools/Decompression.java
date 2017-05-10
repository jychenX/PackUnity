package com.example.Tools;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.UIFrame.Main;


public class Decompression {

	
	@SuppressWarnings("resource")
	public static void unZipFile(String jarPath) throws Throwable{
		JarFile jarFile = new JarFile(jarPath);
		Enumeration<JarEntry> jarEntrys = jarFile.entries();
		while(jarEntrys.hasMoreElements()){
			JarEntry jarEntry = jarEntrys.nextElement();
			String outFileName = Main.getMainClassPath()+"classFile\\" + jarEntry.getName(); 
			File f = new File(outFileName); 
			fileDir(outFileName); 
			if(jarEntry.isDirectory()){
				continue;
			}
			writeFile(jarFile.getInputStream(jarEntry), f);
		}
	}
	
	private static void fileDir(String outFileName) { 
		Pattern p = Pattern.compile("[/\\" + File.separator + "]"); 
		Matcher m = p.matcher(outFileName);
		System.out.println("             ："+outFileName.toString());
		while (m.find()) { 
			int index = m.start(); 
			String subDir = outFileName.substring(0, index); 
			File subDirFile = new File(subDir); 
			if (!subDirFile.exists()) 
				System.out.println("进去"+index+":"+subDirFile.getAbsolutePath().toString());
				subDirFile.mkdir();
		}
	} 
	
	private static void writeFile(InputStream ips, File outputFile) throws IOException{
		OutputStream ops = new BufferedOutputStream(new FileOutputStream(outputFile));
		try{ 
			byte[] buffer = new byte[1024]; 
			int nBytes = 0; 
			while ((nBytes = ips.read(buffer)) > 0){ 
				ops.write(buffer, 0, nBytes); 
			} 
		}catch (IOException ioe){ 
			throw ioe; 
		} finally { 
			try { 
				if (null != ops){ 
					ops.flush(); 
					ops.close(); 
				} 
			} catch (IOException ioe){ 
				throw ioe; 
			} finally{ 
				if (null != ips){ 
					ips.close(); 
				} 
			} 
		}
	}
	
}
