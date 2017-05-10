package com.example.UIFrame;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.example.Function.ShareSDKUnityCallback;
import com.example.Tools.SplitPackageName;

/*
 *控制台程序的图形UI界面(窗体)
 */
public class UIFrame implements ActionListener, FocusListener  {

	public static JTextField packtf;
	public static JFrame fm;
	public static JTextField tfSdk;
	public static JTextArea logText;
	
	public void initFrame(String isValidPath){
		fm=new JFrame("ShareSDK辅助工具");
		fm.setLayout(new FlowLayout());

		JPanel pn=new JPanel();
		pn.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		
		JLabel packName=new JLabel("项目包名: ");
		packName.setFont(new Font("宋体", 0, 16));

		packtf=new JTextField(40);
		packtf.setFont(new Font("宋体", 0, 16));
		packtf.setText("cn.sharesdk.demo");
		packtf.setForeground(Color.BLACK);

		JPanel notoPanel = new JPanel();
		JLabel note = new JLabel("（可同时输入多个包名，包名之间使用英文逗号隔开，不可以有空格！）");
		note.setFont(new Font("宋体", 0, 12));
		notoPanel.add(note);

		pn.add(packName);
		pn.add(packtf);
		fm.add(pn);
		fm.add(notoPanel);

		JPanel sdkPanel=new JPanel();
		sdkPanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		tfSdk = new  JTextField(50);
		tfSdk.setFont(new Font("宋体", 0, 16));
		tfSdk.setForeground(Color.BLACK);
		tfSdk.setText("请输入AndroidSDK路径");

		JPanel noteSDKPanel=new JPanel();
//		JLabel noteSDK = new JLabel("<html>（在系统环境变量里面配置名为“ANDROID_HOME”，<br/>值为AndroidSDK的路径，就不需要每次都输入）</html>");
		JLabel noteSDK = new JLabel("（在系统环境变量里面配置名为“ANDROID_HOME”，值为AndroidSDK的路径）");
		noteSDK.setFont(new Font("宋体", 0, 12));
		noteSDKPanel.add(noteSDK);
		
		JPanel pnSure=new JPanel();
		pnSure.setLayout(new FlowLayout(FlowLayout.LEFT,110,10));
		
		JButton UnitybtnOK=new JButton("导出Unity回调包");
		UnitybtnOK.setFont(new Font("宋体", 0, 16));
		UnitybtnOK.setActionCommand("UnityOK");
		UnitybtnOK.addActionListener(this);
		
		JButton setBtn = new JButton("如何使用？");
		setBtn.setFont(new Font("宋体", 0, 16));
		setBtn.setActionCommand("ANEOK");
//		setBtn.setEnabled(false);
		setBtn.addActionListener(this);
		
		pnSure.add(setBtn);
		pnSure.add(UnitybtnOK);
		fm.add(pnSure);

		if(isValidPath.equals("")){
			sdkPanel.add(tfSdk);
			fm.add(sdkPanel);
			fm.add(noteSDKPanel);
			tfSdk.addFocusListener(this);
		}else{

		}

		JPanel logPanel = new JPanel();
		logPanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));

		logText = new JTextArea(4,43);
		logText.setLineWrap(true);
		logText.setWrapStyleWord(true);
		logText.setEditable(false);
		logText.setText("日志：\n");

		logPanel.add(new JScrollPane(logText));
		fm.add(logPanel);

		fm.setSize(480, 220);
		fm.setResizable(false);

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		int x = (int)(toolkit.getScreenSize().getWidth()-fm.getWidth())/2;
		int y = (int)(toolkit.getScreenSize().getHeight()-fm.getHeight())/2;
		fm.setLocation(x, y);
		fm.setVisible(true);
		fm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ShareSDKUnityCallback unity = new ShareSDKUnityCallback();
		try {
			if("UnityOK".equals(e.getActionCommand())){
				//编译类文件以及打包成jar
				String[] nameArray = SplitPackageName.packNameArray(packtf.getText());
				boolean isLast = false;
				if(nameArray != null){
					for(int i = 0;i<nameArray.length;i++){
						unity.UnityJAR(nameArray[i], logText, i+2>nameArray.length ? true :false);
					}
				}

			}else if("ANEOK".equals(e.getActionCommand())){
				JOptionPane.showConfirmDialog(null,
						"此工具支持同时生成多个渠道的回调包，多个包名拼接时，包名之间使用英文逗号隔开，不可以有空格\n\n"+
						"如何配置：\n"+
						"1、把jdk的libs目录下面的tools.jar包，复制到jre（注意：不是jdk目录下面的jre）的libs目录下面；\n"+
						"2、在系统的环境变量中添加名为“ANDROID_HOME”,值为Android SDK路径的变量，否则每次使用都要输入SDK路径；",
						"说明",JOptionPane.CLOSED_OPTION);
			}
			
		} catch (Throwable t) {       //异常信息捕获
			t.printStackTrace();
			 if(t.toString().equals("java.lang.NullPointerException")){
				 JOptionPane.showMessageDialog(fm, "请检查电脑JDK或者SDK的环境变量，或者键入路径"+t.toString());
			 }
		}
	}

	public void focusGained(FocusEvent e) {
		if(tfSdk == e.getSource()){
			tfSdk.setText("");
		}
	}

	public void focusLost(FocusEvent e) {
		if(tfSdk == e.getSource() && tfSdk.getText().equals("")){
			tfSdk.setText("请输入AndroidSDK路径");
		}
	}
}
