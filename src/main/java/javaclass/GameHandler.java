package javaclass;

import android.app.Activity;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.Vector;

public class GameHandler {
    private String[] fileArray = new String[]{"loadHindiData.txt", "level2.txt", "level3.txt", "level4.txt", "level5.txt", "level6.txt", "level7.txt", "level8.txt", "level9.txt"};
    private BufferedReader input;
    private InputStreamReader inputStreamReader;
    private ReferenceWrapper refrenceWrapper;
    public boolean sound = true;

    public GameHandler(ReferenceWrapper refrenceWrapper) {
        this.refrenceWrapper = refrenceWrapper;
    }

    public void playSound() {
        if (this.sound) {
            this.refrenceWrapper.getSoundManager().playSound(1);
        }
    }

    public void loadHindiData(Activity activity, boolean isHindi)  {
        InputStream is = null ;
        try {
            is = activity.getAssets().open(isHindi ? "data/data.txt" : "data/english_data.txt");
            if (is != null) {
                Vector<String> dataLine = new Vector();
                this.inputStreamReader = new InputStreamReader(is);
                this.input = new BufferedReader(this.inputStreamReader);
                String line = "";
                // 读取数据
                while (true) {
                    line = this.input.readLine();
                    if (line == null) {
                        break;
                    }
                    dataLine.add(line);
                }
                is.read(new byte[is.available()]);
                is.close();
                // 解析数据
                this.refrenceWrapper.level1DataArray = (String[][]) Array.newInstance(String.class, new int[]{dataLine.size(), 6});
                int i1 = 0;
                Enumeration<String> data = dataLine.elements();
                while (data.hasMoreElements()) {
                    StringTokenizer st2;
                    String pp = data.nextElement();
                    if (isHindi) {
                        st2 = new StringTokenizer(pp, ":");
                    } else {
                        st2 = new StringTokenizer(pp, "|");
                    }
                    int j1 = 0;
                    while (st2.hasMoreElements()) {
                        this.refrenceWrapper.level1DataArray[i1][j1] = st2.nextToken();
                        j1++;
                    }
                    Log.e("tag count", "" + i1);
                    System.out.println("" + i1);
                    i1++;
                }
                System.out.println("" + i1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
