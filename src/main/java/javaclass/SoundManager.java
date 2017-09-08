package javaclass;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;
import java.util.HashMap;

public class SoundManager {
    static int playSound = 1;
    private AudioManager mAudioManager;
    private Context mContext;
    private SoundPool mSoundPool;
    private HashMap<Integer, Integer> mSoundPoolMap;

    public void initSounds(Context theContext) {
        this.mContext = theContext;
        this.mSoundPool = new SoundPool(2, 3, 0);
        this.mSoundPoolMap = new HashMap();
        this.mAudioManager = (AudioManager) this.mContext.getSystemService("audio");
    }

    public void addSound(int Index, int SoundID) {
        this.mSoundPoolMap.put(Integer.valueOf(Index), Integer.valueOf(this.mSoundPool.load(this.mContext, SoundID, 1)));
    }

    public void playSound(int index) {
        if (index != 8) {
            int streamVolume = this.mAudioManager.getStreamVolume(3);
            if (playSound == 1) {
                this.mSoundPool.play(((Integer) this.mSoundPoolMap.get(Integer.valueOf(index))).intValue(), (float) streamVolume, (float) streamVolume, 1, 0, 1.0f);
            }
        }
    }

    public void resumeSound(int index) {
        this.mSoundPool.resume(index);
    }

    public void pauseSound(int index) {
        this.mSoundPool.pause(index);
    }

    public void playLoopedSound(int index) {
        int streamVolume = this.mAudioManager.getStreamVolume(3);
        if (playSound == 1) {
            this.mSoundPool.play(((Integer) this.mSoundPoolMap.get(Integer.valueOf(index))).intValue(), (float) streamVolume, (float) streamVolume, 1, -1, 1.0f);
        }
    }

    public void stopSound(int index) {
        this.mSoundPool.stop(index);
    }

    public void release() {
        try {
            if (this.mSoundPool != null) {
                this.mSoundPool.release();
            }
        } catch (Exception e) {
            Log.e("exception sound pool release..", e + "");
        }
    }
}
