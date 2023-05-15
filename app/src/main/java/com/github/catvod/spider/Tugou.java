package com.github.catvod.spider;

import android.content.Context;

import com.github.catvod.bean.Result;
import com.github.catvod.bean.Vod;
import com.github.catvod.net.OkHttp;
import com.github.catvod.utils.Utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.ByteArrayInputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class Tugou extends Ali{
    private final Pattern regexVid = Pattern.compile("(\\S+)");

    @Override
    public void init(Context context, String extend) {
        super.init(context, extend);
    }



    @Override
    public String searchContent(String key, boolean quick) throws Exception {
      String url="https://www.tugousou.com/search" ;
        LinkedHashMap<String,String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("User-Agent", Utils.CHROME);
        linkedHashMap.put("Origin", "https://www.tugousou.com");
        linkedHashMap.put("Referer", "https://www.tugousou.com");

        LinkedHashMap<String,String> linkedHashMap2 = new LinkedHashMap<>();
        linkedHashMap2.put("action", "search");
        linkedHashMap2.put("from", "web");
        linkedHashMap2.put("keyword", key);
        String poststr = OkHttp.post(url, linkedHashMap2, linkedHashMap);

        Document doc=Jsoup.parse(poststr);

        List<Vod> list = new ArrayList<>();
        for (Element element : doc.select("div.layui-container div.layui-row")) {
            String href = element.select("div.layui-col-lg8 a").attr("href");
            Matcher matcher = regexVid.matcher(href);
            if (!matcher.find()) continue;
            String name = element.select("div.layui-col-lg8 p a").text();
            if (!name.contains(key)) continue;    //如果name中不包括key，直接进行下次循环
        //    String remark = element.select("div.news_text a p").text().split("\\|")[1].split("：")[1];
            Vod vod = new Vod();
            vod.setVodPic("https://inews.gtimg.com/newsapp_bt/0/13263837859/1000");
            vod.setVodId(matcher.group(1));
        //    vod.setVodRemarks(remark);
            vod.setVodName(name);
            list.add(vod);
        }
            return Result.string(list);






    }
}
