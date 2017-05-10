package com.example.Tools;

import com.example.UIFrame.UIFrame;


public class JavaString {
	public String packName = "cn.sharesdk.demo";
	
	public String rt = "\r\n";

	public JavaString(String packName){
			this.packName = packName;
			System.out.println("类名字:"+packName);
	}

	public String getWXAPI(){
		String WXEntryActivity =
				"package "+ packName +".wxapi;" +  rt +
						"import android.content.Intent;" + rt +
						"import android.widget.Toast;" + rt +
						"import cn.sharesdk.wechat.utils.WXAppExtendObject;" + rt +
						"import cn.sharesdk.wechat.utils.WXMediaMessage;" + rt +
						"import cn.sharesdk.wechat.utils.WechatHandlerActivity;" + rt +
						"public class WXEntryActivity extends WechatHandlerActivity {" + rt +
						"	public void onGetMessageFromWXReq(WXMediaMessage msg) {" + rt +
						"		Intent iLaunchMyself = getPackageManager().getLaunchIntentForPackage(getPackageName());" + rt +
						"    	startActivity(iLaunchMyself);" + rt +
						"    }" + rt +
						""+rt +
						"	public void onShowMessageFromWXReq(WXMediaMessage msg) {" + rt +
						"    	if (msg != null && msg.mediaObject != null && (msg.mediaObject instanceof WXAppExtendObject)) {" + rt +
						"			WXAppExtendObject obj = (WXAppExtendObject) msg.mediaObject;" + rt +
						"       	Toast.makeText(this, obj.extInfo, Toast.LENGTH_SHORT).show();" + rt +
						"       }" + rt +
						"   }" + rt +
						"}";
		return  WXEntryActivity;
	}

	public String getYXAPI(){
		String YXEntryActivity =
				"package "+ packName +".yxapi;" +  rt +
						"import cn.sharesdk.yixin.utils.YXMessage;" + rt +
						"import cn.sharesdk.yixin.utils.YixinHandlerActivity;" + rt +
						"public class YXEntryActivity extends YixinHandlerActivity {" + rt +
						"   public void onReceiveMessageFromYX(YXMessage msg) {" + rt +
						"   }" + rt +
						"}";
		return  YXEntryActivity;
	}

	public String getAPSHARE(){
		String ShareEntryActivity =
				"package "+ packName +".apshare;" +  rt +
						"import cn.sharesdk.alipay.share.AlipayHandlerActivity;" + rt +
						"public class ShareEntryActivity extends AlipayHandlerActivity{" + rt +
						"}";
		return  ShareEntryActivity;
	}
	
}