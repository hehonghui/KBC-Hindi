package com.kbc.hindi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import handlerclass.ReviewHandler;
import handlerclass.StoreType;
import javaclass.ReferenceWrapper;

public class MainActivity extends Activity implements OnClickListener {
    private String PLAY_STORE_AMAZION = "http://www.amazon.com/gp/mas/dl/android?p=";
    private String PLAY_STORE_SAMSUNG = "samsungapps://ProductDetail/";
    private String PLAY_STORE_URL = "https://play.google.com/store/apps/details?id=";
    private String SHARE_MSG = "I am playing an awesome game KBC Hindi. Get your KBC Hindi on android now link ";
    OnClickListener exitClickListener = new OnClickListener() {
        public void onClick(View v) {
            ReferenceWrapper.getRefrenceWrapper(MainActivity.this).getGameHandler().playSound();
            if (v.getId() == R.id.homeButton) {
//                AdLeadBoltClass.showleadBoltInterstitialAds(MainActivity.this.getApplicationContext());
                MainActivity.this.finish();
            } else if (v.getId() == R.id.continueButton) {
//                AdLeadBoltClass.showLeadbolatVideoAds(MainActivity.this.getApplicationContext(), RefrenceWrapper.getRefrenceWrapper(MainActivity.this), false);
                ((LinearLayout) MainActivity.this.findViewById(R.id.exitLayout)).setVisibility(View.GONE);
                MainActivity.this.isExit = false;
            }
        }
    };
    LinearLayout exitLayout;
    boolean isExit = false;
    TextView movevalue;
    private ReferenceWrapper refrenceWrapper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.refrenceWrapper = ReferenceWrapper.getRefrenceWrapper(this);
        screenInit();
        init();
        exitInit();
    }


    private void screenInit() {
//        new ShowPopUp().showDefaultPopup(this, new FileRead().readingFile(this, "promodata"), StoreType.getPromoValue());
    }

    protected void onStart() {
        this.movevalue.setText("Move " + this.refrenceWrapper.getDataBaseClass().getFirstTime());
        animation();
        super.onStart();
    }

    private void animation() {
        TranslateAnimation animy = new TranslateAnimation(0.0f, 0.0f, 200.0f, 0.0f);
        animy.setDuration(700);
        animy.setFillAfter(true);
        ((Button) findViewById(R.id.playButton)).setAnimation(animy);
        TranslateAnimation animleft = new TranslateAnimation(-200.0f, 0.0f, 0.0f, 0.0f);
        animleft.setDuration(700);
        animleft.setFillAfter(true);
//        ((Button) findViewById(R.id.moreButton)).setAnimation(animleft);
        TranslateAnimation animright = new TranslateAnimation(200.0f, 0.0f, 0.0f, 0.0f);
        animright.setDuration(700);
        animright.setFillAfter(true);
        ((Button) findViewById(R.id.review)).setAnimation(animright);
        ScaleAnimation scal = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, 1, 0.5f, 1, 0.5f);
        scal.setDuration(500);
        scal.setFillAfter(true);
        ((ImageView) findViewById(R.id.logo)).setAnimation(scal);
        if (ReferenceWrapper.getRefrenceWrapper(this).AD_REFRESH_COUNT % 3 == 0) {
//            AdLeadBoltClass.showleadBoltInterstitialAds(getApplicationContext());
        }
        ReferenceWrapper refrenceWrapper = ReferenceWrapper.getRefrenceWrapper(this);
        refrenceWrapper.AD_REFRESH_COUNT++;
    }


    private void init() {
        ((Button) findViewById(R.id.playButton)).setOnClickListener(this);
//        ((Button) findViewById(R.id.moreButton)).setOnClickListener(this);
        ((Button) findViewById(R.id.review)).setOnClickListener(this);
        ((Button) findViewById(R.id.ShrearBtn)).setOnClickListener(this);
//        ((Button) findViewById(R.id.buttonfacebook)).setOnClickListener(this);
//        ((Button) findViewById(R.id.playVideoButton)).setOnClickListener(this);
        this.movevalue = (TextView) findViewById(R.id.textViewmove);
        this.movevalue.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
//                AdLeadBoltClass.showLeadbolatVideoAds(MainActivity.this.getApplicationContext(), RefrenceWrapper.getRefrenceWrapper(MainActivity.this), false);
            }
        });
        this.exitLayout = (LinearLayout) findViewById(R.id.exitLayout);
    }

    public void onClick(View v) {
        if (this.exitLayout.getVisibility() != View.VISIBLE) {
            ReferenceWrapper.getRefrenceWrapper(this).getGameHandler().playSound();
            if (v.getId() == R.id.playButton) {
                int move = this.refrenceWrapper.getDataBaseClass().getFirstTime();
                if (move > 0) {
                    this.refrenceWrapper.getDataBaseClass().setFirstTime(move - 1);
                    startActivity(new Intent(this, GameActivity.class));
                    return;
                }
//                AdLeadBoltClass.showLeadbolatVideoAds(getApplicationContext(), RefrenceWrapper.getRefrenceWrapper(this), false);
                ((LinearLayout) findViewById(R.id.ShearMovepopup)).setVisibility(View.VISIBLE);
            }

//            else if (v.getId() == R.id.playVideoButton) {
//                Toast.makeText(this, "play video", Toast.LENGTH_SHORT).show();
////                AdLeadBoltClass.showLeadbolatVideoAds(this, RefrenceWrapper.getRefrenceWrapper(this), true);
//            }

            else if (v.getId() == R.id.ShrearBtn) {
                ((LinearLayout) findViewById(R.id.ShearMovepopup)).setVisibility(View.GONE);
                this.refrenceWrapper.getDataBaseClass().setFirstTime(10);
                this.movevalue.setText("Move " + this.refrenceWrapper.getDataBaseClass().getFirstTime());
                shearvalueindata();
            }
//            else if (v.getId() == R.id.moreButton) {
////                ReviewHandler.downloadFreeApps(this);
//            }
            else if (v.getId() == R.id.review) {
                ReviewHandler.startReview(this);
            }
        }
    }

    private void shearvalueindata() {
        Intent share = new Intent("android.intent.action.SEND");
        share.setType("text/plain");
        share.addFlags(524288);
        share.putExtra("android.intent.extra.SUBJECT", "KBC Hindi");
        if (StoreType.SAMSUNG) {
            share.putExtra("android.intent.extra.TEXT", this.SHARE_MSG + "\n" + this.PLAY_STORE_SAMSUNG + getPackageName());
        } else if (StoreType.GOOGLE) {
            share.putExtra("android.intent.extra.TEXT", this.SHARE_MSG + "\n" + this.PLAY_STORE_URL + getPackageName());
        } else if (StoreType.AMAZON) {
            share.putExtra("android.intent.extra.TEXT", this.SHARE_MSG + "\n" + this.PLAY_STORE_AMAZION + getPackageName());
        }
        startActivity(Intent.createChooser(share, "Share link!"));
    }

    public void exitInit() {
        ((Button) findViewById(R.id.homeButton)).setOnClickListener(this.exitClickListener);
        ((Button) findViewById(R.id.continueButton)).setOnClickListener(this.exitClickListener);
    }

    public void exit() {
        if (this.isExit) {
            this.exitLayout.setVisibility(View.GONE);
            this.isExit = false;
            return;
        }
        this.exitLayout.setVisibility(View.VISIBLE);
        this.isExit = true;
    }
}
