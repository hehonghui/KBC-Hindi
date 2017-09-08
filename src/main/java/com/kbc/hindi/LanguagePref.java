package com.kbc.hindi;

import android.content.Context;

/**
 * Created by mrsimple on 7/9/17.
 */

public class LanguagePref {

    private LanguagePref() {
    }

    public static final String CONFIG = "config";

    public static void saveLanguage(Context context, String language) {
        context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE).edit().putString("lang", language).apply();
    }

    public static String getLanguage(Context context) {
        return context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE).getString("lang", "") ;
    }
}
