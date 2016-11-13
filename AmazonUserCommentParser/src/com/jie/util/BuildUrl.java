package com.jie.util;

/**
 * Created by jie on 16-7-20.
 */
public class BuildUrl {
    public static String buildAmazonCommentPageUrl(String proId){
        return "https://www.amazon.cn/product-reviews/"+proId+"ref=cm_cr_dp_see_all_summary?ie=UTF8&showViewpoints=1&sortBy=helpful";
    }

    public static String addAmazonHeaderUrl(String url) {
        return "https://www.amazon.cn" + url;
    }
}
