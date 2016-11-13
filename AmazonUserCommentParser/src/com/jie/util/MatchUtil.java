package com.jie.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jie on 16-7-19.
 */
public class MatchUtil {
    public List<String> selectProductUrl(List<String> oUrls){
        List<String> usefulUrls = new ArrayList<>();
        //   /dp/B01DXRKLCS/....

        String rule = ".*/dp/(\\w{10})/.*";
        Pattern r = Pattern.compile(rule);
        for (String url:oUrls
             ) {
            if (url.length()>999 && !url.contains("http://www.amazon.cn/"))
                continue;
            Matcher m = r.matcher(url);
            if (m.find()) {
//                usefulUrls.add(BuildUrl.buildNewAmazonCommentUrl(m.group(1)));
            } else {
                usefulUrls.add(url);
            }
        }

        return usefulUrls;
    }

    public static String MatchGroup_1(String rule,String url){
//        String rule = ".*/product-reviews/(\\w{10}).*";
        Pattern r = Pattern.compile(rule);
            Matcher m = r.matcher(url);
        if (m.find()) {
            return m.group(1);
        } else {
            return null;
        }
    }


    public static boolean IsMatch(String rule, String con) {
        Pattern pattern = Pattern.compile(rule);
        Matcher m = pattern.matcher(con);
        return m.find();
    }
}
