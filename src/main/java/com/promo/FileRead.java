package com.promo;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

public class FileRead {
    private final String PROMO_DB_NAME = "promo_time_counter";
    private final String PROMO_LAST_VALUE = "promo_last_value";
    private SharedPreferences sharedpreferences;

    public String[] readingFile(Context context, String fileName) {
        Throwable th;
        Vector<String[]> promoVector = new Vector();
        this.sharedpreferences = context.getSharedPreferences("promo_time_counter", 0);
        if (!timeHandler(context)) {
            return null;
        }
        BufferedReader reader = null;
        try {
            BufferedReader reader2 = new BufferedReader(new InputStreamReader(context.getAssets().open("promo/" + fileName + ".txt"), "UTF-8"));
            try {
                for (String mLine = reader2.readLine(); mLine != null; mLine = reader2.readLine()) {
                    promoVector.add(mLine.split(";"));
                }
                if (reader2 != null) {
                    try {
                        reader2.close();
                        reader = reader2;
                    } catch (IOException e) {
                        e.printStackTrace();
                        reader = reader2;
                    }
                }
            } catch (IOException e2) {
                reader = reader2;
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                return (String[]) promoVector.get(saveUpdatedValueInSharedPref(context, promoVector.size()));
            } catch (Throwable th2) {
                th = th2;
                reader = reader2;
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e32) {
                        e32.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (IOException e4) {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return (String[]) promoVector.get(saveUpdatedValueInSharedPref(context, promoVector.size()));
        } catch (Throwable th3) {
            th = th3;
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
//            throw th;
        }
        return (String[]) promoVector.get(saveUpdatedValueInSharedPref(context, promoVector.size()));
    }

    private int saveUpdatedValueInSharedPref(Context _context, int _size) {
        int lastValue = this.sharedpreferences.getInt("promo_last_value", 0);
        if (lastValue >= _size - 1) {
            lastValue = 0;
        } else {
            lastValue++;
        }
        Editor editor = this.sharedpreferences.edit();
        editor.putInt("promo_last_value", lastValue);
        editor.commit();
        Log.e("" + _size, " " + lastValue);
        return lastValue;
    }

    private boolean timeHandler(Context context) {
        return true;
    }
}
