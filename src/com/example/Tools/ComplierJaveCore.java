package com.example.Tools;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

import javax.swing.JOptionPane;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import com.example.UIFrame.Main;
import com.example.UIFrame.UIFrame;


public class ComplierJaveCore {

	public static void initCompiler(String packPath) throws Throwable{
		
		JavaString javaString = new JavaString();
		String[] javaPath={javaString.ShareEntryActivity, javaString.WXEntryActivity, javaString.YXEntryActivity};
		String apshare = "ShareEntryActivity";
		String wxapi = "WXEntryActivity";
		String yxapi = "YXEntryActivity";
		String[] files={apshare, wxapi, yxapi};
		
		for(int i=0;i<3;i++){
			javaCompiler(javaPath[i],files[i]);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static void javaCompiler(String javaFile, String className) throws Throwable{
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
	    DiagnosticCollector<JavaFileObject> listent = new DiagnosticCollector<JavaFileObject>();
//	    JOptionPane.showMessageDialog(UIFrame.fm, "编译前");
	    StandardJavaFileManager fileManager = compiler.getStandardFileManager(listent, null, null);
//	    JOptionPane.showMessageDialog(UIFrame.fm, "编译后");

	    StringWriter writer = new StringWriter();
	    PrintWriter out = new PrintWriter(writer);
	    out.println(javaFile);
	    out.close();
	    JavaFileObject file = new JavaSourceFromString(className, writer.toString());

	    Iterable<String> savePathIterable = Arrays.asList("-d", Main.getMainClassPath()+"classFile", "-cp", SeachSystemJAR.getSystemPath());
	    
	    Iterable<? extends JavaFileObject> compilationUnits = Arrays.asList(file);
	    CompilationTask task = compiler.getTask(null, fileManager, listent, savePathIterable, null, compilationUnits);

	    boolean success = task.call();
	    for (Diagnostic diagnostic : listent.getDiagnostics()) {
	    	System.out.println(diagnostic.getCode());
	    	System.out.println(diagnostic.getKind());
	    	System.out.println(diagnostic.getPosition());
	    	System.out.println(diagnostic.getStartPosition());
	    	System.out.println(diagnostic.getEndPosition());
	    	System.out.println(diagnostic.getSource());
	    	System.out.println(diagnostic.getMessage(null));

	    }
	    System.out.println("Success: " + success);

	    if (success) {
	    	try {
	        	URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { new File("").toURI().toURL() });
	        	Class.forName(className, true, classLoader).getDeclaredMethod("main", new Class[] { String[].class }).invoke(null, new Object[] { null });
	    	} catch (ClassNotFoundException e) {
	    		System.err.println("Class not found: " + e);
	    	} catch (NoSuchMethodException e) {
	    		System.err.println("No such method: " + e);
	    	} catch (IllegalAccessException e) {
	    		System.err.println("Illegal access: " + e);
	    	} catch (InvocationTargetException e) {
	    		System.err.println("Invocation target: " + e);
	    	}
	  	}
	}
}


class JavaSourceFromString extends SimpleJavaFileObject {
	final String code;
	JavaSourceFromString(String name, String code) {
		super(URI.create("string:///" + name.replace('.','/') + Kind.SOURCE.extension),Kind.SOURCE);
		this.code = code;
	}

	public CharSequence getCharContent(boolean ignoreEncodingErrors) {
		return code;
	}
}
