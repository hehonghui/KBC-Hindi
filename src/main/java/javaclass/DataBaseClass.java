package javaclass;

import android.app.Activity;
import android.content.SharedPreferences.Editor;

public class DataBaseClass {
    Activity activity;
    ReferenceWrapper refrenceWrapper;
    int status;

    public DataBaseClass(ReferenceWrapper ref, Activity act) {
        this.refrenceWrapper = ref;
        this.activity = act;
    }

    public void setFirstTime(int value) {
        Activity activity = this.activity;
        Editor editor = this.activity.getSharedPreferences("first_time", 0).edit();
        editor.putInt("FirstTime", value);
        editor.commit();
    }

    public int getFirstTime() {
        Activity activity = this.activity;
        this.status = this.activity.getSharedPreferences("first_time", 0).getInt("FirstTime", 0);
        return this.status;
    }

    public String getGreenLogoString1(Activity activity) {
        String countString = this.refrenceWrapper.getRecordStore(activity, "TIME");
        if (countString != null) {
            return countString;
        }
        countString = "0";
        setGreenLogoString1(activity, countString);
        return countString;
    }

    public void setGreenLogoString1(Activity activity, String countString) {
        this.refrenceWrapper.addRecordStore(activity, "TIME", countString);
    }

    public long showGreenLogo1(Activity activity) {
        return Long.parseLong("" + getGreenLogoString1(activity));
    }

    public void updateGreenValue1(String time, Activity activity) {
        setGreenLogoString1(activity, time);
    }
}
