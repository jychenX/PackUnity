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
import javax.swing.JTextField;

import com.example.Function.ShareSDKANECallback;
import com.example.Function.ShareSDKUnityCallback;

/*
 *控制台程序的图形UI界面(窗体)
 */
public class UIFrame implements ActionListener, FocusListener  {

	public static JTextField packtf;
	public static JFrame fm;
	public static JTextField tfSdk;
	
	public void initFrame(String isValidPath){
		fm=new JFrame("ShareSDK辅助工具");
		fm.setLayout(new FlowLayout());
		JPanel pn=new JPanel();
		pn.setLayout(new FlowLayout(10,0,0));
		
		JLabel packName=new JLabel("项目包名: ");
		packName.setFont(new Font("宋体", 0, 16));

		packtf=new JTextField(40);
		packtf.setFont(new Font("宋体", 0, 16));
		packtf.setText("cn.sharesdk.demo");
		packtf.setForeground(Color.BLACK);
		
		pn.add(packName);
		pn.add(packtf);
		fm.add(pn);

		JPanel sdkPath=new JPanel();
		sdkPath.setLayout(new FlowLayout(10,0,0));
		tfSdk = new  JTextField(50);
		tfSdk.setFont(new Font("宋体", 0, 16));
		tfSdk.setForeground(Color.BLACK);
		tfSdk.setText("请输入AndroidSDK路径");

		if(isValidPath.equals("")){
			sdkPath.add(tfSdk);
			fm.add(sdkPath);
			tfSdk.addFocusListener(this);
		}
		
		JPanel pnSure=new JPanel();
		pnSure.setLayout(new FlowLayout(20,110,10));
		
		JButton UnitybtnOK=new JButton("导出Unity回调包");
		UnitybtnOK.setFont(new Font("宋体", 0, 16));
		UnitybtnOK.setActionCommand("UnityOK");
		UnitybtnOK.addActionListener(this);
		
		JButton ANEbtnOK=new JButton("导出ANE回调包");
		ANEbtnOK.setFont(new Font("宋体", 0, 16));
		ANEbtnOK.setActionCommand("ANEOK");
		ANEbtnOK.setEnabled(false);
		ANEbtnOK.addActionListener(this);
		
		pnSure.add(ANEbtnOK);
		pnSure.add(UnitybtnOK);
		fm.add(pnSure);
		
		fm.setSize(480, 180);
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
		ShareSDKANECallback ane =new ShareSDKANECallback();
		try {
			if("UnityOK".equals(e.getActionCommand())){
				//编译类文件以及打包成jar
				unity.UnityJAR(packtf.getText());
			}else if("ANEOK".equals(e.getActionCommand())){
				ane.openBat("c");
			}
			
		} catch (Throwable t) {       //异常信息捕获
			t.printStackTrace();
			 if(t.toString().equals("java.lang.NullPointerException")){
				 JOptionPane.showMessageDialog(fm, "请检查电脑JDK或者SDK的环境变量，或者键入路径");
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
