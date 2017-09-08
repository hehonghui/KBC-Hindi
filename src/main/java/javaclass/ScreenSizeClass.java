package javaclass;

import android.app.Activity;
import android.support.v4.media.TransportMediator;
import android.util.DisplayMetrics;

public class ScreenSizeClass {
    private Activity activity;
    public int margin;
    private ReferenceWrapper refrenceWrapper;
    public int starSize;
    public int textBoxSize;
    public int textSize;

    public ScreenSizeClass(ReferenceWrapper ref, Activity _activity) {
        this.refrenceWrapper = ref;
        this.activity = _activity;
        getScreenSize(this.activity);
    }

    public void getScreenSize(Activity activity) {
        int screenSize = activity.getResources().getConfiguration().screenLayout & 15;
        DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();
        switch (screenSize) {
            case 1:
                switch (getScreenRes()) {
                    case 1:
                        setValues(displayMetrics, 11, 15, 12);
                        return;
                    case 2:
                        setValues(displayMetrics, 15, 20, 12);
                        return;
                    case 3:
                        setValues(displayMetrics, 21, 30, 18);
                        return;
                    case 4:
                        setValues(displayMetrics, 26, 37, 24);
                        return;
                    case 5:
                        setValues(displayMetrics, 23, 35, 24);
                        return;
                    default:
                        return;
                }
            case 2:
                switch (getScreenRes()) {
                    case 1:
                        setValues(displayMetrics, 14, 25, 15);
                        return;
                    case 2:
                        setValues(displayMetrics, 17, 27, 15);
                        return;
                    case 3:
                        setValues(displayMetrics, 25, 40, 23);
                        return;
                    case 4:
                        setValues(displayMetrics, 34, 54, 75);
                        return;
                    case 5:
                        setValues(displayMetrics, 32, 54, 30);
                        return;
                    default:
                        return;
                }
            case 3:
                switch (getScreenRes()) {
                    case 1:
                        setValues(displayMetrics, 14, 28, 18);
                        return;
                    case 2:
                        setValues(displayMetrics, 20, 37, 18);
                        return;
                    case 3:
                        setValues(displayMetrics, 24, 57, 27);
                        return;
                    case 4:
                        setValues(displayMetrics, 33, 67, 40);
                        return;
                    case 5:
                        setValues(displayMetrics, 28, 65, 33);
                        return;
                    default:
                        return;
                }
            case 4:
                switch (getScreenRes()) {
                    case 1:
                        setValues(displayMetrics, 28, 49, 30);
                        return;
                    case 2:
                        setValues(displayMetrics, 36, 65, 30);
                        return;
                    case 3:
                        setValues(displayMetrics, 55, 97, 45);
                        return;
                    case 4:
                        setValues(displayMetrics, 75, TransportMediator.KEYCODE_MEDIA_RECORD, 60);
                        return;
                    case 5:
                        setValues(displayMetrics, 65, 110, 60);
                        return;
                    default:
                        return;
                }
            default:
                return;
        }
    }

    private int getScreenRes() {
        DisplayMetrics metrics = new DisplayMetrics();
        this.activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int density = metrics.densityDpi;
        if (density == 240) {
            return 3;
        }
        if (density == 160) {
            return 2;
        }
        if (density == 120) {
            return 1;
        }
        if (density == 320) {
            return 5;
        }
        return 4;
    }

    private void setValues(DisplayMetrics displayMetrics, int _textSize, int _textboxsize, int _margin) {
        this.textSize = (int) (((double) (((float) _textSize) / displayMetrics.density)) + 0.5d);
        this.textBoxSize = (int) (((double) (((float) _textboxsize) / displayMetrics.density)) + 0.5d);
        this.margin = (int) (((double) (((float) _margin) / displayMetrics.density)) + 0.5d);
    }
}
