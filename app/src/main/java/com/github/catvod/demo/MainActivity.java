package com.github.catvod.demo;

import android.app.Activity;
import android.os.Bundle;

import com.github.catvod.R;
import com.github.catvod.crawler.Spider;
import com.github.catvod.crawler.SpiderDebug;
import com.github.catvod.spider.AppYsV2;
import com.github.catvod.spider.Init;
import com.github.catvod.spider.Kunyu77;
import com.github.catvod.spider.Paper;
import com.github.catvod.spider.UpYun;
import com.github.catvod.spider.Zhaozy;

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

//            Zhaozy zzy = new Zhaozy();
//            zzy.setUsername("yunshuche");
//            zzy.setPassword("11223344");
//            this.Alisearch(zzy, "他是谁", true);
//
//            Paper paper = new Paper();
//            this.Alisearch(paper, "他是谁", true);
//
//
//            Kunyu77 kunyu = new Kunyu77();
//            try {
//                this.Testspider(kunyu, "他是谁", "", false);
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
            AppYsV2 ying = new AppYsV2();
            try {
                this.Testspider(ying, "长月烬明", "http://kuying.kuyouk.top:9528/api.php/app/", false);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        }).start();
    }

    /*
     * 调试ALI搜索,返回搜索结果json
     * */
    public void Alisearch(Spider spider, String key, boolean quick) {
        String str;
        try {
            str = spider.searchContent(key, quick);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(spider.toString() + "返回值：" + str);

    }

    /*
     * 调试单个site,spider
     * */
    public void Testspider(Spider spider, String key, String extend, boolean quick) throws Exception {
        spider.init(MainActivity.this, extend);
        String json = spider.homeContent(true);
        SpiderDebug.log("homeContent返回值:" + json);  //

        String str = spider.searchContent(key, quick);
        System.out.println("searchContent返回值:" + str);  //

        String str1 = spider.homeVideoContent();
        System.out.println("homevideoContent返回值:" + str1);  //
        JSONObject homeContent = null;
        try {
            homeContent = new JSONObject(str);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println("categoryContent返回值:" + spider.categoryContent("1", "1", true, null));

        if (homeContent != null) {
            try {
                List<String> ids = new ArrayList<String>();
                JSONArray array = homeContent.getJSONArray("list");
                for (int i = 0; i < array.length() && i < 3; i++) {
                    try {
                        ids.clear();
                        ids.add(array.getJSONObject(i).getString("vod_id"));

                        System.out.println("detailContent返回值:" + spider.detailContent(ids));  //调用detailContent的输出结果

                        JSONObject detailContent = new JSONObject(spider.detailContent(ids)).getJSONArray("list").getJSONObject(0);
                        String[] playFlags = detailContent.getString("vod_play_from").split("\\$\\$\\$");
                        String[] playUrls = detailContent.getString("vod_play_url").split("\\$\\$\\$");
                        for (int j = 0; j < playFlags.length; j++) {
                            String pu = playUrls[j].split("#")[0].split("\\$")[1];
                            // System.out.println(pu);
                            System.out.println("playerContent返回值:" + spider.playerContent(playFlags[j], pu, new ArrayList<>()));  //调用playerContent返回结果
                        }
                    } catch (Throwable th) {

                    }
                }
            } catch (Throwable th) {

            }
        }


    }

}