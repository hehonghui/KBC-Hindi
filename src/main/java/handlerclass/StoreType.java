package handlerclass;

public class StoreType {
    public static boolean AMAZON = false;
    public static boolean GOOGLE = true;
    public static boolean OTHERS = false;
    public static boolean SAMSUNG = false;
    public static boolean SLIDEME = false;
    public static String admob_id_large = "ca-app-pub-4227055763335873/1153087745";
    public static String admob_id_small = "ca-app-pub-4227055763335873/5722888145";
    public static String review_url = "";

    public StoreType() {
        if (SAMSUNG) {
            admob_id_small = " ca-app-pub-4227055763335873/5722888145";
            admob_id_large = "ca-app-pub-4227055763335873/1153087745";
        } else if (AMAZON) {
            admob_id_small = "ca-app-pub-4227055763335873/5722888145";
            admob_id_large = "ca-app-pub-4227055763335873/1153087745";
        } else if (SLIDEME) {
            admob_id_small = "ca-app-pub-4227055763335873/5722888145";
            admob_id_large = "ca-app-pub-4227055763335873/1153087745";
        }
    }

    public static String getPromoValue() {
        if (SAMSUNG) {
            return "SAMSUNG";
        }
        if (AMAZON) {
            return "AMAZON";
        }
        return "GOOGLE";
    }
}
