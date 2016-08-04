package com.example.Tools;

import com.example.UIFrame.UIFrame;


public class JavaString {
	public String packaName=UIFrame.packtf.getText();
	
	public String rt = "\r\n";
	public String ShareEntryActivity = 
            "package "+packaName+".apshare;" +  rt +
            "import cn.sharesdk.alipay.share.AlipayHandlerActivity;" + rt +
            "public class ShareEntryActivity extends AlipayHandlerActivity{" + rt +
            "}";
	
	public String WXEntryActivity = 
			"package "+packaName+".wxapi;" +  rt +
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
	
	public String YXEntryActivity = 
            "package "+packaName+".yxapi;" +  rt +
            "import cn.sharesdk.yixin.utils.YXMessage;" + rt +
            "import cn.sharesdk.yixin.utils.YixinHandlerActivity;" + rt +
            "public class YXEntryActivity extends YixinHandlerActivity {" + rt +
            "   public void onReceiveMessageFromYX(YXMessage msg) {" + rt +
            "   }" + rt +
            "}";
	
}