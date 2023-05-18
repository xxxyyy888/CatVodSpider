package com.github.catvod.demo;

import android.app.Activity;
import android.os.Bundle;

import com.github.catvod.R;
import com.github.catvod.spider.AppYsV2;
import com.github.catvod.spider.Init;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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
  /*         Zhaozy zzy = new Zhaozy();
            zzy.setUsername("yunshuche");
            zzy.setPassword("11223344");

            String str3;
            try {
                str3 = zzy.searchContent("尘封十三载", true);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            System.out.println(str3);
*/
  /*          Tugou tg=new Tugou();
            String str4;
            try {
                str4=tg.searchContent("他是谁",true);

            }catch (Exception e) {
                throw new RuntimeException(e);
            }
            System.out.println(str4);
*/


            AppYsV2 kuying = new AppYsV2();
            kuying.init(MainActivity.this,"http://kuying.kuyouk.top:9528/api.php/app/");
            //      String json = aidi1.homeContent(true);
            //       System.out.println(json);
            String str4 = kuying.searchContent("他是谁", false);
            //   System.out.println(str4);

            JSONObject homeContent = null;
            try {
                homeContent = new JSONObject(str4);
            } catch (JSONException e) {
                e.printStackTrace();
            }
         //   System.out.println(aidi1.categoryContent("1", "1", false, null));
            if (homeContent != null) {
                try {
                    List<String> ids = new ArrayList<String>();
                    JSONArray array = homeContent.getJSONArray("list");
                    for (int i = 0; i < array.length() && i < 3; i++) {
                        try {
                            ids.clear();
                            ids.add(array.getJSONObject(i).getString("vod_id"));

                       //     System.out.println(aidi1.detailContent(ids));

                            JSONObject detailContent = new JSONObject(kuying.detailContent(ids)).getJSONArray("list").getJSONObject(0);

                            String[] playFlags = detailContent.getString("vod_play_from").split("\\$\\$\\$");

                            String[] playUrls = detailContent.getString("vod_play_url").split("\\$\\$\\$");
                            System.out.println(playUrls[0]);
                            for (int j = 0; j < playFlags.length; j++) {
                                String pu = playUrls[j].split("#")[0].split("\\$")[1];
                               // System.out.println(pu);
                               System.out.println(kuying.playerContent(playFlags[j], pu, new ArrayList<>()));
                            }
                        } catch (Throwable th) {

                        }
                    }
                } catch (Throwable th) {

                }
            }

        }).start();
    }
}