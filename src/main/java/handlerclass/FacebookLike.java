package handlerclass;

import android.app.Activity;
import java.net.HttpURLConnection;
import javaclass.ReferenceWrapper;

public class FacebookLike extends Thread {
    Activity activity;
    private ReferenceWrapper refrenceWrapper;
    private HttpURLConnection urlConnection;

    public void getLikeCount() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0045 in list []
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:42)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
*/
        /*
        r14 = this;
        r0 = 0;
        r8 = new java.net.URL;	 Catch:{ Exception -> 0x0038, all -> 0x008d }
        r9 = "https://api.facebook.com/method/fql.query?format=JSON&query=SELECT%20fan_count%20from%20page%20where%20page_id=335719276580956";	 Catch:{ Exception -> 0x0038, all -> 0x008d }
        r8.<init>(r9);	 Catch:{ Exception -> 0x0038, all -> 0x008d }
        r9 = r8.openConnection();	 Catch:{ Exception -> 0x0038, all -> 0x008d }
        r9 = (java.net.HttpURLConnection) r9;	 Catch:{ Exception -> 0x0038, all -> 0x008d }
        r14.urlConnection = r9;	 Catch:{ Exception -> 0x0038, all -> 0x008d }
        r2 = new java.io.BufferedInputStream;	 Catch:{ Exception -> 0x0038, all -> 0x008d }
        r9 = r14.urlConnection;	 Catch:{ Exception -> 0x0038, all -> 0x008d }
        r9 = r9.getInputStream();	 Catch:{ Exception -> 0x0038, all -> 0x008d }
        r2.<init>(r9);	 Catch:{ Exception -> 0x0038, all -> 0x008d }
        r6 = new java.io.BufferedReader;	 Catch:{ Exception -> 0x0038, all -> 0x008d }
        r9 = new java.io.InputStreamReader;	 Catch:{ Exception -> 0x0038, all -> 0x008d }
        r9.<init>(r2);	 Catch:{ Exception -> 0x0038, all -> 0x008d }
        r6.<init>(r9);	 Catch:{ Exception -> 0x0038, all -> 0x008d }
        r7 = new java.lang.StringBuffer;	 Catch:{ Exception -> 0x0038, all -> 0x008d }
        r9 = "";	 Catch:{ Exception -> 0x0038, all -> 0x008d }
        r7.<init>(r9);	 Catch:{ Exception -> 0x0038, all -> 0x008d }
        r5 = "";	 Catch:{ Exception -> 0x0038, all -> 0x008d }
    L_0x002e:
        r5 = r6.readLine();	 Catch:{ Exception -> 0x0038, all -> 0x008d }
        if (r5 == 0) goto L_0x0046;	 Catch:{ Exception -> 0x0038, all -> 0x008d }
    L_0x0034:
        r7.append(r5);	 Catch:{ Exception -> 0x0038, all -> 0x008d }
        goto L_0x002e;
    L_0x0038:
        r1 = move-exception;
        r1.printStackTrace();	 Catch:{ Exception -> 0x0038, all -> 0x008d }
        r9 = r14.urlConnection;
        if (r9 == 0) goto L_0x0045;
    L_0x0040:
        r9 = r14.urlConnection;
        r9.disconnect();
    L_0x0045:
        return;
    L_0x0046:
        if (r6 == 0) goto L_0x007a;
    L_0x0048:
        r3 = new org.json.JSONArray;	 Catch:{ Exception -> 0x0088 }
        r9 = r7.toString();	 Catch:{ Exception -> 0x0088 }
        r3.<init>(r9);	 Catch:{ Exception -> 0x0088 }
        r9 = 0;	 Catch:{ Exception -> 0x0088 }
        r4 = r3.get(r9);	 Catch:{ Exception -> 0x0088 }
        r4 = (org.json.JSONObject) r4;	 Catch:{ Exception -> 0x0088 }
        r9 = "fan_count";	 Catch:{ Exception -> 0x0088 }
        r0 = r4.getString(r9);	 Catch:{ Exception -> 0x0088 }
        r9 = r14.refrenceWrapper;	 Catch:{ Exception -> 0x0088 }
        r10 = r14.activity;	 Catch:{ Exception -> 0x0088 }
        r11 = "FACEBOOK_LIKE1";	 Catch:{ Exception -> 0x0088 }
        r12 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0088 }
        r12.<init>();	 Catch:{ Exception -> 0x0088 }
        r13 = "";	 Catch:{ Exception -> 0x0088 }
        r12 = r12.append(r13);	 Catch:{ Exception -> 0x0088 }
        r12 = r12.append(r0);	 Catch:{ Exception -> 0x0088 }
        r12 = r12.toString();	 Catch:{ Exception -> 0x0088 }
        r9.addRecordStore(r10, r11, r12);	 Catch:{ Exception -> 0x0088 }
    L_0x007a:
        if (r0 != 0) goto L_0x007e;
    L_0x007c:
        r0 = "0";	 Catch:{ Exception -> 0x0038, all -> 0x008d }
    L_0x007e:
        r9 = r14.urlConnection;
        if (r9 == 0) goto L_0x0045;
    L_0x0082:
        r9 = r14.urlConnection;
        r9.disconnect();
        goto L_0x0045;
    L_0x0088:
        r1 = move-exception;
        r1.printStackTrace();	 Catch:{ Exception -> 0x0038, all -> 0x008d }
        goto L_0x007a;
    L_0x008d:
        r9 = move-exception;
        r10 = r14.urlConnection;
        if (r10 == 0) goto L_0x0097;
    L_0x0092:
        r10 = r14.urlConnection;
        r10.disconnect();
    L_0x0097:
        throw r9;
        */
        throw new UnsupportedOperationException("Method not decompiled: handlerclass.FacebookLike.getLikeCount():void");
    }

    public FacebookLike(ReferenceWrapper refrenceWrapper, Activity activity) {
        this.refrenceWrapper = refrenceWrapper;
        this.activity = activity;
        refrenceWrapper.addRecordStore(this.activity, "FACEBOOK_LIKE1", "50K");
    }

    public void run() {
    }
}
