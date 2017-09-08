package com.kbc.hindi;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import javaclass.ReferenceWrapper;

public class SplashActivity extends Activity {
    private ReferenceWrapper refrenceWrapper;
    private boolean statusval;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        this.refrenceWrapper = ReferenceWrapper.getRefrenceWrapper(this);
        if (getTime()) {
            this.refrenceWrapper.getDataBaseClass().setFirstTime(10);
            setTime(false);
        }
        startTimer();
    }

    private void startTimer() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                String lang = LanguagePref.getLanguage(getApplicationContext()) ;
                if (TextUtils.isEmpty(lang)) {
                    SplashActivity.this.showLanguageScreen();
                } else {
                    gotoGameActivity(lang);
                }
            }
        }, 100);
    }

    private void showLanguageScreen() {
        findViewById(R.id.languageScreen).setVisibility(View.VISIBLE);
        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        findViewById(R.id.submitButton).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioSexButton = (RadioButton) SplashActivity.this.findViewById(selectedId);
                if (selectedId == -1) {
                    Toast.makeText(SplashActivity.this, "Please Select at least one Language", Toast.LENGTH_LONG).show();
                }
                gotoGameActivity(radioSexButton.getText().toString());
            }
        });
    }

    private void gotoGameActivity(String language) {
        if (TextUtils.isEmpty(language)) {
            Toast.makeText(SplashActivity.this, "Please Select at least one Language", Toast.LENGTH_LONG).show();
            return;
        }
        // save language
        LanguagePref.saveLanguage(this, language);
        if (language.equalsIgnoreCase("hindi")) {
            SplashActivity.this.refrenceWrapper.getGameHandler().loadHindiData(SplashActivity.this, true);
            SplashActivity.this.launchmainScreen();
        } else {
            SplashActivity.this.refrenceWrapper.getGameHandler().loadHindiData(SplashActivity.this, false);
            SplashActivity.this.launchmainScreen();
        }
    }

    private void launchmainScreen() {
        startActivity(new Intent(getBaseContext(), MainActivity.class));
        finish();
    }

    public void setTime(boolean value) {
        Editor editor = getSharedPreferences("first", 0).edit();
        editor.putBoolean("Time", value);
        editor.commit();
    }

    public boolean getTime() {
        this.statusval = getSharedPreferences("first", 0).getBoolean("Time", true);
        return this.statusval;
    }

    protected void onDestroy() {
        super.onDestroy();
    }
}
