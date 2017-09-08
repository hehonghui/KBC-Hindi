package handlerclass;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class ReviewHandler {
    private static String amazonReviewURL = "http://www.amazon.com/gp/mas/dl/android?p=";
    private static String googleReviewURL = "market://details?id=";
    private static String samsungReviewUrl = "samsungapps://ProductDetail/";

    public static void startReview(Context context) {
        Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse(getReviewURL(context)));
        browserIntent.setFlags(67108864);
        browserIntent.setFlags(3);
        context.startActivity(browserIntent);
    }

//    public static void downloadFreeApps(Activity activity) {
//        Intent browserIntent = null;
//        if (StoreType.GOOGLE || StoreType.OTHERS) {
//            browserIntent = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/developer?id=Addicto%20Games&hl=en"));
//        } else if (StoreType.SAMSUNG) {
//            browserIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://tinyurl.com/nem8sbx"));
//        } else if (StoreType.AMAZON) {
//            browserIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://tinyurl.com/m4sbvke"));
//        }
//        browserIntent.setFlags(67108864);
//        browserIntent.setFlags(3);
//        activity.startActivity(browserIntent);
//    }


    public static String getReviewURL(Context context) {
        if (StoreType.SAMSUNG) {
            return samsungReviewUrl + context.getPackageName();
        }
        if (StoreType.AMAZON) {
            return amazonReviewURL + context.getPackageName();
        }
        return googleReviewURL + context.getPackageName();
    }
}
