//package handlerclass;
//
//import android.app.Activity;
//import android.widget.LinearLayout;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdRequest.Builder;
//import com.google.android.gms.ads.AdSize;
//import com.google.android.gms.ads.AdView;
//import com.google.android.gms.ads.MobileAds;
//
//public class AdsHandler {
//    public static void showSmallAd(Activity context, AdView mAdView) {
//        MobileAds.initialize(context, "ca-app-pub-4227055763335873~7100059741");
//        mAdView.loadAd(new Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());
//    }
//
//    public static void showLargeAd(Activity context, LinearLayout adFrame) {
//        AdView adView = new AdView(context);
//        adView.setAdSize(AdSize.MEDIUM_RECTANGLE);
//        adView.setAdUnitId(StoreType.admob_id_small);
//        adFrame.addView(adView);
//        adView.loadAd(new Builder().addTestDevice("INSERT_YOUR_HASHED_DEVICE_ID_HERE").build());
//    }
//}
