package com.example.Function;


public class ShareSDKANECallback {
	String batPath = "F:\\ANE快速修改包名工具\\New-ANE-For-ShareSDK-master\\"
				+ "ANESample\\package\\ane_build_android_only.bat";
	
	
	public void openBat(String batPath) throws Throwable{
		Runtime.getRuntime().exec("cmd /k start F:\\ANE快速修改包名工具\\New-ANE-For-ShareSDK-master\\"
				+ "ANESample\\package\\ane_build_android_only.bat");
	}
}
