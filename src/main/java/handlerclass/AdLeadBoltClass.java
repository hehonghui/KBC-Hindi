//package handlerclass;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.widget.Toast;
//import com.apptracker.android.listener.AppModuleListener;
//import com.apptracker.android.track.AppTracker;
//import javaclass.RefrenceWrapper;
//
//public class AdLeadBoltClass {
//    private static boolean IS_HINT_INCREASE = false;
//    private static final String YOUR_APP_API_KEY = "tPDxDgw6Pbpm7dOesftQ5F2zvqVmjSiX";
////    private static AppModuleListener leadboltListener = new AppModuleListener() {
////        public void onModuleLoaded(String location) {
////        }
////
////        public void onModuleFailed(String location, String error, boolean isCache) {
////        }
////
////        public void onModuleClosed(String location) {
////        }
////
////        public void onModuleClicked(String location) {
////        }
////
////        public void onModuleCached(String location) {
////        }
////
////        public void onMediaFinished(boolean viewCompleted) {
////            if (viewCompleted) {
////                if (AdLeadBoltClass.mRefrenceWrapper != null && AdLeadBoltClass.IS_HINT_INCREASE) {
////                    AdLeadBoltClass.mRefrenceWrapper.getDataBaseClass().setFirstTime(10);
////                }
////            } else if (AdLeadBoltClass.mContext != null) {
////                Toast.makeText(AdLeadBoltClass.mContext, "Please watch full video for increase the 10 moves", 0).show();
////            }
////        }
////    };
//    private static Context mContext = null;
//    private static RefrenceWrapper mRefrenceWrapper = null;
//
//    public static void initialisedInterstitialLeadBoalt(Bundle savedInstanceState, Context context) {
//        if (savedInstanceState == null) {
//            AppTracker.startSession(context, YOUR_APP_API_KEY, AppTracker.ENABLE_AUTO_CACHE);
//        }
//        AppTracker.loadModuleToCache(context, "inapp");
//    }
//
//    public static void showleadBoltInterstitialAds(Context context) {
//        if (AppTracker.isAdReady("inapp")) {
//            AppTracker.loadModule(context, "inapp");
//        }
//    }
//
//    public static void initialVideoAds(Bundle savedInstanceState, Context context) {
//        if (savedInstanceState == null) {
//            AppTracker.setModuleListener(leadboltListener);
//            AppTracker.startSession(context, YOUR_APP_API_KEY, AppTracker.ENABLE_AUTO_CACHE);
//        }
//        AppTracker.loadModuleToCache(context, "video");
//    }
//
//    public static void showLeadbolatVideoAds(Context context, RefrenceWrapper refrenceWrapper, boolean isHintIncrease) {
//        mContext = context;
//        mRefrenceWrapper = refrenceWrapper;
//        if (AppTracker.isAdReady("video")) {
//            AppTracker.loadModule(context, "video");
//        }
//    }
//}
