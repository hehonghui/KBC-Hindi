package javaclass;

import android.app.Activity;
import android.content.SharedPreferences.Editor;

import com.kbc.hindi.R;

import handlerclass.FacebookLike;

public class ReferenceWrapper {
    public static ReferenceWrapper refrenceWrapper;
    public int AD_REFRESH_COUNT = 0;
    private Activity activity;
    DataBaseClass dataBaseClass;
    FacebookLike facebook;
    GameHandler gameHandler;
    public String[][] level1DataArray = ((String[][]) null);
    ScreenSizeClass screenSizeClass;
    SoundManager soundManager;

    public ReferenceWrapper(Activity _activity) {
        this.activity = _activity;
        getSoundManager().initSounds(this.activity.getApplicationContext());
        getSoundManager().addSound(1, R.raw.click_on);
        getScreenSizeClass();
        getfacebook();
    }

    public FacebookLike getfacebook() {
        if (this.facebook == null) {
            this.facebook = new FacebookLike(this, this.activity);
        }
        return this.facebook;
    }

    public ScreenSizeClass getScreenSizeClass() {
        if (this.screenSizeClass == null) {
            this.screenSizeClass = new ScreenSizeClass(this, this.activity);
        }
        return this.screenSizeClass;
    }

    public static ReferenceWrapper getRefrenceWrapper(Activity _activity) {
        if (refrenceWrapper == null) {
            refrenceWrapper = new ReferenceWrapper(_activity);
        }
        return refrenceWrapper;
    }

    public SoundManager getSoundManager() {
        if (this.soundManager == null) {
            this.soundManager = new SoundManager();
        }
        return this.soundManager;
    }

    public GameHandler getGameHandler() {
        if (this.gameHandler == null) {
            this.gameHandler = new GameHandler(this);
        }
        return this.gameHandler;
    }

    public DataBaseClass getDataBaseClass() {
        if (this.dataBaseClass == null) {
            this.dataBaseClass = new DataBaseClass(this, this.activity);
        }
        return this.dataBaseClass;
    }

    public String getRecordStore(Activity activity, String key) {
        return activity.getSharedPreferences(activity.getPackageName(), 0).getString(key, null);
    }

    public void addRecordStore(Activity activity, String key, String value) {
        Editor editor = activity.getSharedPreferences(activity.getPackageName(), 0).edit();
        editor.putString(key, value);
        editor.commit();
    }
}
