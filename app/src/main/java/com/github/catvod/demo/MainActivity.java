package com.github.catvod.demo;

import android.app.Activity;
import android.os.Bundle;

import com.github.catvod.R;
import com.github.catvod.spider.Init;
import com.github.catvod.spider.Paper;
import com.github.catvod.spider.UpYun;
import com.github.catvod.spider.YiSo;
import com.github.catvod.spider.Zhaozy;

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