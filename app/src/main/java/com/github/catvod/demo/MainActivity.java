package com.github.catvod.demo;

import android.app.Activity;
import android.os.Bundle;

import com.github.catvod.R;
import com.github.catvod.ali.API;
import com.github.catvod.spider.Init;
import com.github.catvod.spider.Paper;
import com.github.catvod.spider.UpYun;
import com.github.catvod.spider.YiSo;
import com.github.catvod.spider.Zhaozy;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity {
/*
* onCreate的方法是在Activity创建时被系统调用，是一个Activity生命周期的开始。
* getApplicationContext(): 返回应用的上下文，生命周期是整个应用，应用摧毁，它才摧毁。
* setContentView的作用就是把自己的布局文件放在Activity中显示
* */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init.init(getApplicationContext());

        new Thread(() -> {
            System.out.println("可以开始调试了哦！！！！！！！！！！");

            //********************************
      /*      UpYun yun = new UpYun();

            String str;
            try {
                str = yun.searchContent("他是谁", true);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            System.out.println(str);

            //********************************
            Paper paper = new Paper();

            String str1;
            try {
                str1 = paper.searchContent("人生之路", true);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            System.out.println(str1);


            //********************************
            YiSo yiSo = new YiSo();

            String str2;
            try {
                str2 = yiSo.searchContent("长月烬明", true);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            System.out.println(str2);
*/
            //********************************
            Zhaozy zzy = new Zhaozy();
            zzy.setUsername("yunshuche");
            zzy.setPassword("11223344");

            String str3;
            try {
                str3 = zzy.searchContent("尘封十三载", true);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            System.out.println(str3);




        }).start();
    }
}