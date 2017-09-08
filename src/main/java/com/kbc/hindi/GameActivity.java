package com.kbc.hindi;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import java.util.Random;

import handlerclass.ReviewHandler;
import javaclass.ReferenceWrapper;

public class GameActivity extends Activity implements OnClickListener {
    public static MediaPlayer mp;
    boolean AUDIENCEPOLL = true;
    boolean FIFTY_FIFTY = true;
    boolean FRIEND_CALL = true;
    boolean SWAP_LIFELINE = true;
    private TextView aText;
    OnClickListener animClickListener = new OnClickListener() {
        public void onClick(View v) {
            GameActivity.this.refrenceWrapper.getGameHandler().playSound();
            if (v.getId() == R.id.moneyLayoutButton) {
                GameActivity.this.setMoneyLayout();
                GameActivity.this.lifeLineButtonAnimationOn();
            } else if (v.getId() == R.id.moneyLayoutButton2) {
                GameActivity.this.setMoneyLayout();
                GameActivity.this.lifeLineButtonAnimationOn();
            } else if (v.getId() == R.id.nextButton) {
                GameActivity.this.startSound();
                GameActivity gameScreen = GameActivity.this;
                gameScreen.rightAnswerCount++;
                if (GameActivity.this.rightAnswerCount == 11) {
                    GameActivity.this.rightAnswerCount = 0;
                }
                GameActivity.this.showGame();
                ScaleAnimation scal = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, 1, 0.5f, 1, 0.5f);
                scal.setDuration(300);
                ((LinearLayout) GameActivity.this.findViewById(R.id.winPopUp)).setAnimation(scal);
                ((LinearLayout) GameActivity.this.findViewById(R.id.winPopUp)).setVisibility(View.GONE);
                if (GameActivity.this.counter % 3 == 0) {
//                    AdLeadBoltClass.showleadBoltInterstitialAds(GameScreen.this.getApplicationContext());
                    if (GameActivity.this.getFirstTimevalue()) {
                        ScaleAnimation scalv = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, 1, 0.5f, 1, 0.5f);
                        scalv.setDuration(1000);
                        ((LinearLayout) GameActivity.this.findViewById(R.id.reviewLayout)).setAnimation(scalv);
                        ((LinearLayout) GameActivity.this.findViewById(R.id.reviewLayout)).setVisibility(View.VISIBLE);
                        GameActivity.this.stopTime();
                        GameActivity.stopSound();
                    }
                }
            } else if (v.getId() == R.id.reviewButton) {
                GameActivity.this.setFirstTimevalue(false);
                ReviewHandler.startReview(GameActivity.this);
            } else if (v.getId() == R.id.playAgain) {
                GameActivity.this.finish();
                ((LinearLayout) GameActivity.this.findViewById(R.id.TimeOutLayout)).setVisibility(View.GONE);
            } else if (v.getId() == R.id.reviewButtonNow) {
                GameActivity.this.timeSwap = 0;
                GameActivity.this.startTime();
                GameActivity.this.setFirstTimevalue(false);
                ReviewHandler.startReview(GameActivity.this);
                ((LinearLayout) GameActivity.this.findViewById(R.id.reviewLayout)).setVisibility(View.GONE);
            } else if (v.getId() == R.id.reviewButtonLater) {
                ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, 1, 0.5f, 1, 0.5f);
                scaleAnimation.setDuration(1000);
                ((LinearLayout) GameActivity.this.findViewById(R.id.reviewLayout)).setAnimation(scaleAnimation);
                ((LinearLayout) GameActivity.this.findViewById(R.id.reviewLayout)).setVisibility(View.GONE);
                GameActivity.this.timeSwap = 0;
                GameActivity.this.startTime();
                GameActivity.this.startSound();
            } else if (!GameActivity.this.popup()) {
                if (v.getId() == R.id.button50 && GameActivity.this.FIFTY_FIFTY) {
                    GameActivity.this.FIFTY_FIFTY = false;
                    ((Button) GameActivity.this.findViewById(R.id.button50)).setBackgroundResource(R.drawable.cross_fiftyfifty);
                    GameActivity.this.fiftyFiftyAnimation();
                } else if (v.getId() == R.id.audiencePoll && GameActivity.this.AUDIENCEPOLL) {
                    GameActivity.this.AUDIENCEPOLL = false;
                    ((Button) GameActivity.this.findViewById(R.id.audiencePoll)).setBackgroundResource(R.drawable.cross_auditionsbutton);
                    ((LinearLayout) GameActivity.this.findViewById(R.id.audiencePollLayout)).setVisibility(View.VISIBLE);
                    GameActivity.this.pollAnimation();
                } else if (v.getId() == R.id.phoneFriend && GameActivity.this.FRIEND_CALL) {
                    GameActivity.this.FRIEND_CALL = false;
                    ((Button) GameActivity.this.findViewById(R.id.phoneFriend)).setBackgroundResource(R.drawable.cross_phonefriend);
                    ((LinearLayout) GameActivity.this.findViewById(R.id.friendUpperLayout)).setVisibility(View.VISIBLE);
                } else if (v.getId() == R.id.swapQuestion && GameActivity.this.SWAP_LIFELINE) {
                    GameActivity.this.SWAP_LIFELINE = false;
                    ((Button) GameActivity.this.findViewById(R.id.swapQuestion)).setBackgroundResource(R.drawable.cross_replace);
                    GameActivity.this.swapQuestion();
                } else if (v.getId() == R.id.frnd1 || v.getId() == R.id.frnd2 || v.getId() == R.id.frnd3) {
                    ((LinearLayout) GameActivity.this.findViewById(R.id.friendImage)).setVisibility(View.GONE);
                    TextView textView = new TextView(GameActivity.this);
                    textView.setGravity(17);
                    textView.setTextColor(-1);
                    textView.setText("Answer is :- " + GameActivity.this.answer);
                    ((LinearLayout) GameActivity.this.findViewById(R.id.friendUpperLayout)).addView(textView);
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            ((LinearLayout) GameActivity.this.findViewById(R.id.friendUpperLayout)).setVisibility(View.GONE);
                        }
                    }, 1500);
                }
            }
        }
    };
    String answer = "";
    private AppBroadcastReceiver appBroadcastReceiver;
    private TextView bText;
    private TextView cText;
    int count;
    int counter = 0;
    private TextView dText;
    long finalTime = 0;
    private AnimationDrawable frameAnimation;
    boolean isExit = false;
//    private AdView mAdView;
    String[] moneyArray = new String[]{"INR 5000", "INR 10,000", "INR 20,000", "INR 40,000", "INR 80,000", "INR 1,60000", "INR 3,20000", "INR 6,40000", "INR 12,50000", "INR 25 LACK", "INR 50 LACK", "INR 1 CRORE"};
    int moneyCount;
    private Handler myHandler = new Handler();
    int myTime = 45;
    int quesCount = 2;
    private Random random;
    ReferenceWrapper refrenceWrapper;
    private String review;
    boolean rightAnswer;
    int rightAnswerCount = -1;
    SharedPreferences sharedpreferences;
    private long startTimesec = 0;
    private boolean status;
    int tag = 0;
    long timeInMillies = 0;
    long timeSwap = 0;
    private TextView timertext;
    private Runnable updateTimerMethod = new Runnable() {
        public void run() {
            GameActivity.this.timeInMillies = SystemClock.uptimeMillis() - GameActivity.this.startTimesec;
            GameActivity.this.finalTime = GameActivity.this.timeSwap + GameActivity.this.timeInMillies;
            int seconds = ((int) (GameActivity.this.finalTime / 1000)) % 60;
            GameActivity.this.timertext.setText("" + String.format("%02d", new Object[]{Integer.valueOf(GameActivity.this.myTime - seconds)}));
            GameActivity.this.myHandler.postDelayed(this, 45);
            if (String.format("%02d", new Object[]{Integer.valueOf(GameActivity.this.myTime - seconds)}).contains("00")) {
                GameActivity.this.timeuppopup();
            }
        }
    };
    String val;

    public class AppBroadcastReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equalsIgnoreCase("android.intent.action.USER_PRESENT")) {
                GameActivity.this.startSound();
                GameActivity.this.startTime();
            }
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        this.refrenceWrapper = ReferenceWrapper.getRefrenceWrapper(this);
        this.appBroadcastReceiver = new AppBroadcastReceiver();
        this.sharedpreferences = getSharedPreferences(this.review, 0);
        init();
        showGame();
    }


    public int getRandomNumber() {
        return this.random.nextInt(699);
    }

    private void setMoneyLayout() {
        int height = getResources().getDisplayMetrics().heightPixels / 12;
        LinearLayout moneylayout = (LinearLayout) findViewById(R.id.moneyLayout);
        moneylayout.removeAllViews();
        LayoutParams param = new LayoutParams(-2, height);
        for (int i = this.moneyArray.length - 1; i >= 0; i--) {
            TextView textView = new TextView(this);
            param.leftMargin = this.refrenceWrapper.getScreenSizeClass().margin;
            textView.setLayoutParams(param);
            textView.setTextColor(-1);
            textView.setTextSize(12.0f);
            if (i == this.rightAnswerCount) {
                textView.setText("" + this.moneyArray[i]);
                textView.setBackgroundResource(R.drawable.slider);
            } else {
                textView.setText("" + this.moneyArray[i]);
            }
            moneylayout.addView(textView);
        }
    }

    private void init() {
        this.random = new Random();
        this.aText = (TextView) findViewById(R.id.aButton);
        this.aText.setOnClickListener(this);
        this.bText = (TextView) findViewById(R.id.bButton);
        this.bText.setOnClickListener(this);
        this.cText = (TextView) findViewById(R.id.cButton);
        this.cText.setOnClickListener(this);
        this.dText = (TextView) findViewById(R.id.dButton);
        this.dText.setOnClickListener(this);
        this.timertext = (TextView) findViewById(R.id.timeText);
        ((Button) findViewById(R.id.button50)).setOnClickListener(this.animClickListener);
        ((Button) findViewById(R.id.audiencePoll)).setOnClickListener(this.animClickListener);
        ((Button) findViewById(R.id.phoneFriend)).setOnClickListener(this.animClickListener);
        ((Button) findViewById(R.id.swapQuestion)).setOnClickListener(this.animClickListener);
        ((Button) findViewById(R.id.moneyLayoutButton)).setOnClickListener(this.animClickListener);
        ((Button) findViewById(R.id.moneyLayoutButton2)).setOnClickListener(this.animClickListener);
        ((ImageView) findViewById(R.id.frnd1)).setOnClickListener(this.animClickListener);
        ((ImageView) findViewById(R.id.frnd2)).setOnClickListener(this.animClickListener);
        ((ImageView) findViewById(R.id.frnd3)).setOnClickListener(this.animClickListener);
        ((Button) findViewById(R.id.nextButton)).setOnClickListener(this.animClickListener);
        ((Button) findViewById(R.id.playAgain)).setOnClickListener(this.animClickListener);
        ((Button) findViewById(R.id.reviewButton)).setOnClickListener(this.animClickListener);
        ((Button) findViewById(R.id.reviewButtonNow)).setOnClickListener(this.animClickListener);
        ((Button) findViewById(R.id.reviewButtonLater)).setOnClickListener(this.animClickListener);
    }

    private void showGame() {
        AnimationText();
        this.counter++;
        this.timeSwap = 0;
        startTime();
        this.quesCount = getRandomNumber();
        this.aText.setBackgroundResource(R.drawable.abutton);
        this.bText.setBackgroundResource(R.drawable.bbutton);
        this.cText.setBackgroundResource(R.drawable.cbutton);
        this.dText.setBackgroundResource(R.drawable.dbutton);
        if (ReferenceWrapper.getRefrenceWrapper(this).level1DataArray == null && ReferenceWrapper.getRefrenceWrapper(this).level1DataArray.length == 0) {
            startActivity(new Intent(this, SplashActivity.class));
            finish();
        }
        ((TextView) findViewById(R.id.questionText)).setText("" + this.refrenceWrapper.level1DataArray[this.quesCount][0]);
        this.aText.setText("" + this.refrenceWrapper.level1DataArray[this.quesCount][1]);
        this.bText.setText("" + this.refrenceWrapper.level1DataArray[this.quesCount][2]);
        this.cText.setText("" + this.refrenceWrapper.level1DataArray[this.quesCount][3]);
        this.dText.setText("" + this.refrenceWrapper.level1DataArray[this.quesCount][4]);
        this.answer = this.refrenceWrapper.level1DataArray[this.quesCount][5];
        Log.e("answer", "-" + this.answer);
        clickButtonTrue();
    }

    private void AnimationText() {
        TranslateAnimation animy = new TranslateAnimation(-200.0f, 0.0f, 0.0f, 0.0f);
        animy.setDuration(700);
        ((TextView) findViewById(R.id.aButton)).setAnimation(animy);
        ((TextView) findViewById(R.id.cButton)).setAnimation(animy);
        ((Button) findViewById(R.id.button50)).setAnimation(animy);
        ((Button) findViewById(R.id.audiencePoll)).setAnimation(animy);
        TranslateAnimation animy1 = new TranslateAnimation(0.0f, 0.0f, -200.0f, 0.0f);
        animy1.setDuration(700);
        animy1.setFillAfter(true);
        ((LinearLayout) findViewById(R.id.timerlayout)).setAnimation(animy1);
        TranslateAnimation animleft = new TranslateAnimation(200.0f, 0.0f, 0.0f, 0.0f);
        animleft.setDuration(700);
        ((TextView) findViewById(R.id.bButton)).setAnimation(animleft);
        ((TextView) findViewById(R.id.dButton)).setAnimation(animleft);
        ((Button) findViewById(R.id.phoneFriend)).setAnimation(animleft);
        ((Button) findViewById(R.id.swapQuestion)).setAnimation(animleft);
        ScaleAnimation scal = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, 1, 0.5f, 1, 0.5f);
        scal.setDuration(700);
        scal.setFillAfter(true);
        ((TextView) findViewById(R.id.questionText)).setAnimation(scal);
    }

    public void onClick(View v) {
        if (!popup()) {
            stopSound();
            this.refrenceWrapper.getGameHandler().playSound();
            if (v.getId() == R.id.aButton) {
                clickButtonFalse();
                this.aText.setBackgroundResource(R.drawable.answer_a);
                this.frameAnimation = (AnimationDrawable) this.aText.getBackground();
                this.frameAnimation.start();
                exitAnimationA();
            } else if (v.getId() == R.id.bButton) {
                clickButtonFalse();
                this.bText.setBackgroundResource(R.drawable.answer_b);
                this.frameAnimation = (AnimationDrawable) this.bText.getBackground();
                this.frameAnimation.start();
                exitAnimationB();
            } else if (v.getId() == R.id.cButton) {
                clickButtonFalse();
                this.cText.setBackgroundResource(R.drawable.answer_c);
                this.frameAnimation = (AnimationDrawable) this.cText.getBackground();
                this.frameAnimation.start();
                exitAnimationC();
            } else if (v.getId() == R.id.dButton) {
                clickButtonFalse();
                this.dText.setBackgroundResource(R.drawable.answer_d);
                this.frameAnimation = (AnimationDrawable) this.dText.getBackground();
                this.frameAnimation.start();
                exitAnimationD();
            }
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    GameActivity.this.checkFinalAnswer();
                }
            }, 1500);
        }
    }

    private void checkFinalAnswer() {
        ScaleAnimation scal;
        if (!this.rightAnswer) {
            scal = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, 1, 0.5f, 1, 0.5f);
            scal.setDuration(1000);
            ((LinearLayout) findViewById(R.id.TimeOutLayout)).setAnimation(scal);
            ((LinearLayout) findViewById(R.id.TimeOutLayout)).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.timeUpText)).setText("You loss the game try again !!");
            ((TextView) findViewById(R.id.playAgainText)).setVisibility(View.GONE);
        } else if (this.rightAnswerCount < 12) {
            scal = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, 1, 0.5f, 1, 0.5f);
            scal.setDuration(1000);
            ((LinearLayout) findViewById(R.id.winPopUp)).setAnimation(scal);
            ((LinearLayout) findViewById(R.id.winPopUp)).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.winAmount)).setText("" + this.moneyArray[this.rightAnswerCount + 1]);
        } else {
            scal = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, 1, 0.5f, 1, 0.5f);
            scal.setDuration(1000);
            ((LinearLayout) findViewById(R.id.TimeOutLayout)).setAnimation(scal);
            ((LinearLayout) findViewById(R.id.TimeOutLayout)).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.timeUpText)).setText("Congratulation !! You Win ");
            ((TextView) findViewById(R.id.playAgainText)).setText("" + this.moneyArray[11]);
        }
    }

    private void exitAnimationD() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                GameActivity.this.frameAnimation.stop();
                if (GameActivity.this.answer.equalsIgnoreCase("D")) {
                    GameActivity.this.dText.setBackgroundResource(R.drawable.dright);
                    GameActivity.this.rightAnswer = true;
                } else if (GameActivity.this.answer.equalsIgnoreCase("B")) {
                    GameActivity.this.dText.setBackgroundResource(R.drawable.dwrong);
                    GameActivity.this.bText.setBackgroundResource(R.drawable.bright);
                    GameActivity.this.rightAnswer = false;
                } else if (GameActivity.this.answer.equalsIgnoreCase("C")) {
                    GameActivity.this.dText.setBackgroundResource(R.drawable.dwrong);
                    GameActivity.this.cText.setBackgroundResource(R.drawable.cright);
                    GameActivity.this.rightAnswer = false;
                } else if (GameActivity.this.answer.equalsIgnoreCase("A")) {
                    GameActivity.this.dText.setBackgroundResource(R.drawable.dwrong);
                    GameActivity.this.aText.setBackgroundResource(R.drawable.aright);
                    GameActivity.this.rightAnswer = false;
                }
            }
        }, 500);
    }

    private void exitAnimationC() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                GameActivity.this.frameAnimation.stop();
                if (GameActivity.this.answer.equalsIgnoreCase("C")) {
                    GameActivity.this.cText.setBackgroundResource(R.drawable.cright);
                    GameActivity.this.rightAnswer = true;
                } else if (GameActivity.this.answer.equalsIgnoreCase("B")) {
                    GameActivity.this.cText.setBackgroundResource(R.drawable.cwrong);
                    GameActivity.this.bText.setBackgroundResource(R.drawable.bright);
                    GameActivity.this.rightAnswer = false;
                } else if (GameActivity.this.answer.equalsIgnoreCase("A")) {
                    GameActivity.this.cText.setBackgroundResource(R.drawable.cwrong);
                    GameActivity.this.aText.setBackgroundResource(R.drawable.aright);
                    GameActivity.this.rightAnswer = false;
                } else if (GameActivity.this.answer.equalsIgnoreCase("D")) {
                    GameActivity.this.cText.setBackgroundResource(R.drawable.awrong);
                    GameActivity.this.dText.setBackgroundResource(R.drawable.dright);
                    GameActivity.this.rightAnswer = false;
                }
            }
        }, 500);
    }

    private void exitAnimationB() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                GameActivity.this.frameAnimation.stop();
                if (GameActivity.this.answer.equalsIgnoreCase("B")) {
                    GameActivity.this.bText.setBackgroundResource(R.drawable.bright);
                    GameActivity.this.rightAnswer = true;
                } else if (GameActivity.this.answer.equalsIgnoreCase("A")) {
                    GameActivity.this.bText.setBackgroundResource(R.drawable.bwrong);
                    GameActivity.this.aText.setBackgroundResource(R.drawable.aright);
                    GameActivity.this.rightAnswer = false;
                } else if (GameActivity.this.answer.equalsIgnoreCase("C")) {
                    GameActivity.this.bText.setBackgroundResource(R.drawable.bwrong);
                    GameActivity.this.cText.setBackgroundResource(R.drawable.cright);
                    GameActivity.this.rightAnswer = false;
                } else if (GameActivity.this.answer.equalsIgnoreCase("D")) {
                    GameActivity.this.bText.setBackgroundResource(R.drawable.bwrong);
                    GameActivity.this.dText.setBackgroundResource(R.drawable.dright);
                    GameActivity.this.rightAnswer = false;
                }
            }
        }, 500);
    }

    private void exitAnimationA() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                GameActivity.this.frameAnimation.stop();
                if (GameActivity.this.answer.equalsIgnoreCase("A")) {
                    GameActivity.this.aText.setBackgroundResource(R.drawable.aright);
                    GameActivity.this.rightAnswer = true;
                } else if (GameActivity.this.answer.equalsIgnoreCase("B")) {
                    GameActivity.this.aText.setBackgroundResource(R.drawable.awrong);
                    GameActivity.this.bText.setBackgroundResource(R.drawable.bright);
                    GameActivity.this.rightAnswer = false;
                } else if (GameActivity.this.answer.equalsIgnoreCase("C")) {
                    GameActivity.this.aText.setBackgroundResource(R.drawable.awrong);
                    GameActivity.this.cText.setBackgroundResource(R.drawable.cright);
                    GameActivity.this.rightAnswer = false;
                } else if (GameActivity.this.answer.equalsIgnoreCase("D")) {
                    GameActivity.this.aText.setBackgroundResource(R.drawable.awrong);
                    GameActivity.this.dText.setBackgroundResource(R.drawable.dright);
                    GameActivity.this.rightAnswer = false;
                }
            }
        }, 500);
    }

    private void lifeLineButtonAnimationOn() {
        int length = ((LinearLayout) findViewById(R.id.moneyLayout)).getWidth();
        if (this.tag == 0) {
            TranslateAnimation anim = new TranslateAnimation((float) length, -1.0f, 0.0f, 0.0f);
            anim.setDuration(1000);
            ((LinearLayout) findViewById(R.id.moneyLayout)).setAnimation(anim);
            ((LinearLayout) findViewById(R.id.moneyLayout)).startAnimation(anim);
            ((Button) findViewById(R.id.moneyLayoutButton)).setAnimation(anim);
            ((Button) findViewById(R.id.moneyLayoutButton)).startAnimation(anim);
            ((LinearLayout) findViewById(R.id.moneyLayout)).setVisibility(View.VISIBLE);
            ((Button) findViewById(R.id.moneyLayoutButton)).setVisibility(View.VISIBLE);
            ((Button) findViewById(R.id.moneyLayoutButton2)).setVisibility(View.GONE);
            this.tag = 1;
            anim.setAnimationListener(new AnimationListener() {
                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    GameActivity.this.closeMoney();
                }
            });
            return;
        }
        backmoneypopup(length);
    }

    private void backmoneypopup(int length) {
        this.tag = 0;
        TranslateAnimation anim = new TranslateAnimation(-1.0f, (float) length, 0.0f, 0.0f);
        anim.setDuration(1000);
        ((LinearLayout) findViewById(R.id.moneyLayout)).setAnimation(anim);
        ((LinearLayout) findViewById(R.id.moneyLayout)).startAnimation(anim);
        ((Button) findViewById(R.id.moneyLayoutButton)).setAnimation(anim);
        ((Button) findViewById(R.id.moneyLayoutButton)).startAnimation(anim);
        anim.setAnimationListener(new AnimationListener() {
            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                ((Button) GameActivity.this.findViewById(R.id.moneyLayoutButton)).setVisibility(View.INVISIBLE);
                ((LinearLayout) GameActivity.this.findViewById(R.id.moneyLayout)).setVisibility(View.INVISIBLE);
                ((Button) GameActivity.this.findViewById(R.id.moneyLayoutButton2)).setVisibility(View.VISIBLE);
            }
        });
    }

    private void closeMoney() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                GameActivity.this.backmoneypopup(((LinearLayout) GameActivity.this.findViewById(R.id.moneyLayout)).getWidth());
            }
        }, 1000);
    }

    public void setFirstTimevalue(boolean value) {
        Editor editor = getSharedPreferences("first_time_value", 0).edit();
        editor.putBoolean("FirstTimevalue", value);
        editor.commit();
    }

    public boolean getFirstTimevalue() {
        this.status = getSharedPreferences("first_time_value", 0).getBoolean("FirstTimevalue", true);
        return this.status;
    }

    private boolean popup() {
        LinearLayout win = (LinearLayout) findViewById(R.id.winPopUp);
        LinearLayout rev = (LinearLayout) findViewById(R.id.reviewLayout);
        if (((LinearLayout) findViewById(R.id.TimeOutLayout)).getVisibility() == View.VISIBLE || win.getVisibility() == View.VISIBLE || rev.getVisibility() == View.VISIBLE) {
            return true;
        }
        return false;
    }

    protected void fiftyFiftyAnimation() {
        if (this.answer.equalsIgnoreCase("A")) {
            this.bText.setVisibility(View.INVISIBLE);
            this.dText.setVisibility(View.INVISIBLE);
        } else if (this.answer.equalsIgnoreCase("B")) {
            this.aText.setVisibility(View.INVISIBLE);
            this.cText.setVisibility(View.INVISIBLE);
        } else if (this.answer.equalsIgnoreCase("C")) {
            this.aText.setVisibility(View.INVISIBLE);
            this.dText.setVisibility(View.INVISIBLE);
        } else if (this.answer.equalsIgnoreCase("D")) {
            this.bText.setVisibility(View.INVISIBLE);
            this.cText.setVisibility(View.INVISIBLE);
        }
    }

    protected void audiencePoll() {
        LinearLayout layoutA = (LinearLayout) findViewById(R.id.answerALayout);
        LinearLayout layoutB = (LinearLayout) findViewById(R.id.answerBLayout);
        LinearLayout layoutC = (LinearLayout) findViewById(R.id.answerCLayout);
        LinearLayout layoutD = (LinearLayout) findViewById(R.id.answerDLayout);
        if (this.answer.equalsIgnoreCase("A")) {
            fillStripLevel(4, layoutA);
            fillStripLevel(15, layoutB);
            fillStripLevel(20, layoutC);
            fillStripLevel(28, layoutD);
        } else if (this.answer.equalsIgnoreCase("B")) {
            fillStripLevel(4, layoutB);
            fillStripLevel(15, layoutD);
            fillStripLevel(20, layoutC);
            fillStripLevel(28, layoutA);
        } else if (this.answer.equalsIgnoreCase("C")) {
            fillStripLevel(4, layoutC);
            fillStripLevel(15, layoutA);
            fillStripLevel(20, layoutB);
            fillStripLevel(28, layoutD);
        } else if (this.answer.equalsIgnoreCase("D")) {
            fillStripLevel(4, layoutD);
            fillStripLevel(15, layoutC);
            fillStripLevel(20, layoutA);
            fillStripLevel(28, layoutB);
        }
        new Handler().postDelayed(new Runnable() {
            public void run() {
                ((LinearLayout) GameActivity.this.findViewById(R.id.audiencePollLayout)).setVisibility(View.GONE);
            }
        }, 3000);
    }

    private int getRndomForpoll() {
        return this.random.nextInt(30);
    }

    private void pollAnimation() {
        new CountDownTimer(5000, 200) {
            public void onTick(long millisUntilFinished) {
                GameActivity.this.fillStripLevel(GameActivity.this.getRndomForpoll(), (LinearLayout) GameActivity.this.findViewById(R.id.answerALayout));
                GameActivity.this.fillStripLevel(GameActivity.this.getRndomForpoll(), (LinearLayout) GameActivity.this.findViewById(R.id.answerBLayout));
                GameActivity.this.fillStripLevel(GameActivity.this.getRndomForpoll(), (LinearLayout) GameActivity.this.findViewById(R.id.answerCLayout));
                GameActivity.this.fillStripLevel(GameActivity.this.getRndomForpoll(), (LinearLayout) GameActivity.this.findViewById(R.id.answerDLayout));
            }

            public void onFinish() {
                GameActivity.this.audiencePoll();
            }
        }.start();
    }

    private void fillStripLevel(int no, LinearLayout linearLayout) {
        linearLayout.removeAllViews();
        linearLayout.setWeightSum(30.0f);
        LinearLayout[] layout = new LinearLayout[30];
        LayoutParams param = new LayoutParams(-1, -2);
        param.weight = 1.0f;
        for (int i = 0; i < no; i++) {
            layout[i] = new LinearLayout(this);
            layout[i].setLayoutParams(param);
            layout[i].setBackgroundResource(R.drawable.poll);
            linearLayout.addView(layout[i]);
        }
    }


    protected void swapQuestion() {
        stopTime();
        showGame();
    }

    private void startTime() {
        this.startTimesec = SystemClock.uptimeMillis();
        this.myHandler.postDelayed(this.updateTimerMethod, 45);
    }

    private void stopTime() {
        this.timeSwap += this.timeInMillies;
        this.myHandler.removeCallbacks(this.updateTimerMethod);
    }

    private void clickButtonFalse() {
        stopTime();
        this.aText.setClickable(false);
        this.bText.setClickable(false);
        this.cText.setClickable(false);
        this.dText.setClickable(false);
    }

    private void clickButtonTrue() {
        this.aText.setVisibility(View.VISIBLE);
        this.bText.setVisibility(View.VISIBLE);
        this.cText.setVisibility(View.VISIBLE);
        this.dText.setVisibility(View.VISIBLE);
        this.aText.setClickable(true);
        this.bText.setClickable(true);
        this.cText.setClickable(true);
        this.dText.setClickable(true);
    }

    private void startSound() {
        mp = MediaPlayer.create(this, R.raw.tic);
        mp.setLooping(true);
        if (!mp.isPlaying() && this.refrenceWrapper.getGameHandler().sound) {
            mp.start();
        }
    }

    public static void stopSound() {
        if (mp != null && mp.isPlaying()) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }

    protected void onPause() {
        stopSound();
        stopTime();
        unregisterReceiver(this.appBroadcastReceiver);
        super.onPause();
    }

    protected void onResume() {
        if (!(((KeyguardManager) getSystemService("keyguard")).inKeyguardRestrictedInputMode() || this.counter % 3 == 0)) {
            startSound();
            if (this.refrenceWrapper.getDataBaseClass().showGreenLogo1(this) != 0) {
                this.timeSwap = this.refrenceWrapper.getDataBaseClass().showGreenLogo1(this);
            }
            startTime();
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.USER_PRESENT");
        registerReceiver(this.appBroadcastReceiver, filter);
        super.onResume();
    }

    private void timeuppopup() {
        stopTime();
        stopSound();
        this.timertext.setText("00");
        ScaleAnimation scal = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, 1, 0.5f, 1, 0.5f);
        scal.setDuration(1000);
        scal.setFillAfter(true);
        ((LinearLayout) findViewById(R.id.TimeOutLayout)).setAnimation(scal);
        ((LinearLayout) findViewById(R.id.TimeOutLayout)).setVisibility(View.VISIBLE);
        ((TextView) findViewById(R.id.timeUpText)).setVisibility(View.VISIBLE);
        ((TextView) findViewById(R.id.playAgainText)).setVisibility(View.VISIBLE);
        ((TextView) findViewById(R.id.timeUpText)).setText("Your Time is Up !!");
        ((TextView) findViewById(R.id.playAgainText)).setText("Do you want to play again ?");
    }
}
