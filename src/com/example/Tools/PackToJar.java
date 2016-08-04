package com.example.Tools;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import com.example.UIFrame.Main;


public class PackToJar {

	
	/*
	 此方法用于打包编译好的class文件，打包成jar；
	 String jarPath：传打包好的jar路径具体到打包好的jar文件名；
	 String javaPath：传需要打包的class文件存放目录
	 */
	public static void generateJar(String jarPath, String classPath) throws FileNotFoundException, IOException {

	    String targetDirPath = jarPath.substring(0, jarPath.lastIndexOf("\\"));
	    System.out.println("targetDirPath----"+targetDirPath);
	    File targetDir = new File(targetDirPath);
	    if (!targetDir.exists()) {
	    	targetDir.mkdirs();
	    }
	    Manifest manifest = new Manifest();
	    manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.0");

	    JarOutputStream target = new JarOutputStream(new FileOutputStream(jarPath), manifest);
	    writeClassFile(new File(classPath), target, classPath);
	    target.close();
	  }
	
	private static void writeClassFile(File file, JarOutputStream target, String javaPath) throws IOException {
		BufferedInputStream in = null;
		if(!file.exists()){
			file.mkdirs();
		}
	    try {
	    	if (file.isDirectory()) {
	    		String name = file.getPath().replace("\\", "/");
	    		if (!name.isEmpty() && !name.equals(Main.getMainClassPath().replace("\\", "/")+"classFile")) {
	    			if (!name.endsWith("/")) {
	    				name += "/";
	    			}
	    			name = name.substring(javaPath.length()+1);
	    			JarEntry entry = new JarEntry(name);
	    			entry.setTime(file.lastModified());
	    			target.putNextEntry(entry);
	    			target.closeEntry();
	    		}
	    		for (File nestedFile : file.listFiles()){
	    			writeClassFile(nestedFile, target, javaPath);
	    		}
	    		return;
	    	}
	      String middleName = file.getPath().replace("\\", "/").substring(javaPath.length()+1);
	      JarEntry entry = new JarEntry(middleName);
	      entry.setTime(file.lastModified());
	      target.putNextEntry(entry);
	      in = new BufferedInputStream(new FileInputStream(file));

	      byte[] buffer = new byte[1024];
	      while (true) {
	    	  int count = in.read(buffer);
	    	  if (count == -1){
	    		  break;
	    	  }
	          target.write(buffer, 0, count);
	      }
	      target.closeEntry();
	    } finally {
	    	if (in != null)
	        in.close();
	    }
	  }
}
