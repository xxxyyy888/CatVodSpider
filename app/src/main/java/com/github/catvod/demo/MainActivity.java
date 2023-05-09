package com.github.catvod.demo;

import android.app.Activity;
import android.os.Bundle;

import com.github.catvod.R;
import com.github.catvod.spider.Init;
import com.github.catvod.spider.Paper;
import com.github.catvod.spider.UpYun;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init.init(getApplicationContext());

        new Thread(() -> {
            System.out.println("可以开始调试了哦！！！！！！！！！！");
            UpYun  yun = new UpYun();
            yun.init(MainActivity.this, "2d56a5a9834947568a5a1299971bb975");
            String str;
            try {
                str = yun.searchContent("人生之路", true);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            System.out.println(str);

            Paper paper = new Paper();
            paper.init(MainActivity.this, "2d56a5a9834947568a5a1299971bb975");
            String str1;
            try {
                str1 = paper.searchContent("人生之路", true);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            System.out.println(str1);

        }).start();
    }
}