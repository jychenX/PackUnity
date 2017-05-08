package com.example.Tools;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileManager.Location;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;

import com.example.UIFrame.UIFrame;


public class ComplierJaveCore {

	/*
	 * 此方法用来编译把java文件编译成class文件，
	 * String javaPath：需要编译的java文件存放的目录；
	 * String classPath：需要存放的类文件目录，在此目录下生成的文件夹，根据package路径来自动生成；
	 */
	@SuppressWarnings({ "rawtypes", "static-access" })
	public static void complier(String javaPath, String classPath) throws  Throwable{
		File fileN = new File(classPath);
		if(!fileN.exists()){
			fileN.mkdirs();
		}
		
		//参与编译的组件
		JavaCompiler.CompilationTask task = null;
		Iterable<? extends JavaFileObject> javaFileIterable;
		List<String> javaSourceList = getFileList(javaPath);
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		// 建立异常日志监听
		DiagnosticCollector<JavaFileObject> listent = new DiagnosticCollector<JavaFileObject>();
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(listent, null, null);
		
		//编译存放的路径和编译需要的jar包
		Iterable<String> savePathIterable = Arrays.asList("-d", classPath, "-cp", SeachSystemJAR.getSystemPath());//

		//循环编译
		for (int i = 0; i < javaSourceList.size(); i++) {
			File file = new File(javaSourceList.get(i));

			javaFileIterable = fileManager.getJavaFileObjectsFromFiles(Arrays.asList(file));

			task = compiler.getTask(null, fileManager, listent, savePathIterable, null, javaFileIterable);
		}
		
		boolean isSuccess = task.call();
		fileManager.close();
		for (Diagnostic diagnostic : listent.getDiagnostics()){
			System.out.printf(
					"Code: %s%n" +
					"Kind: %s%n" +
					"Position: %s%n" +
					"Start Position: %s%n" +
					"End Position: %s%n" +
					"Source: %s%n" +
					"Message: %s%n",
					diagnostic.getCode(), diagnostic.getKind(),
					diagnostic.getPosition(), diagnostic.getStartPosition(),
					diagnostic.getEndPosition(), diagnostic.getSource(),
					diagnostic.getMessage(null));
//			JOptionPane.showMessageDialog(UIFrame.fm, "异常的信息监听"+"Code: %s%n" +diagnostic.getKind()+
//					"Kind: %s%n" +diagnostic.getKind()+
//					"Position: %s%n" +diagnostic.getPosition()+
//					"Start Position: %s%n" +diagnostic.getStartPosition()+
//					"End Position: %s%n" +diagnostic.getEndPosition()+
//					"Source: %s%n" +diagnostic.getSource()+
//					"Message: %s%n"+diagnostic.getMessage(null));
		}
			
		
	}
	
	//读取需要编译的java文件List
	private static List<String> getFileList(String javaPath) throws IOException {	
		File file = new File(javaPath);
		List<String> fileList = new ArrayList<String>();
		if (!file.exists()) {
			file.mkdirs();
		}
		if (file.isDirectory()) {
	    	File[] files = file.listFiles();
	    	for (int i = 0; i < files.length; i++) {
	    		if (files[i].isDirectory()) {
	    			getFileList(files[i].getAbsolutePath());
	    		}else {
	    			fileList.add(files[i].getPath());
	    		}
	    	}
		}
		return fileList;
	}
}
