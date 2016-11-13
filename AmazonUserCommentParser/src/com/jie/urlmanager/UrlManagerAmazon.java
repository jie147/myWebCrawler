package com.jie.urlmanager;


import com.jie.db.UrlManagerDB;
import com.jie.urlmanager.imp.UrlManager;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 使用数据库管理url
 */
public class UrlManagerAmazon implements UrlManager {
    private static final int NOT_PARSE_FLAG=1;
    private static final int PARSE_OVER_FLAG=0;
    private static final int EXCEPTION_FLAG = 3;

    public void initUrlManager(){
        UrlManagerDB urlManagerDB = new UrlManagerDB();
        urlManagerDB.OpenConnection();
        List<String> initUrls = urlManagerDB.findUrlWithFlag(1);
        urlQueue.addAll(initUrls);
        urlManagerDB.closeConnection();
    }

    private static Queue<String> urlQueue;
    private static UrlManagerAmazon urlManagerAzDB;

    private UrlManagerAmazon() {
        urlQueue = new LinkedList<>();
    }

    public static UrlManagerAmazon GetUrlManager() {
        if (urlManagerAzDB == null) {
            urlManagerAzDB = new UrlManagerAmazon();
        }
        return urlManagerAzDB;
    }

    @Override
    public void addOneUrl(String url) {
        UrlManagerDB urlManagerDB = new UrlManagerDB();
        urlManagerDB.OpenConnection();
        urlManagerDB.insertOneUrl(url, NOT_PARSE_FLAG);
        urlQueue.add(url);
        urlManagerDB.closeConnection();
    }

    @Override
    public void addUrls(List<String> urls) {
        if (urls == null) {
            return;
        }
        UrlManagerDB urlManagerDB = new UrlManagerDB();
        urlManagerDB.OpenConnection();
        urlManagerDB.insertUrls(urls,NOT_PARSE_FLAG);
        urlQueue.addAll(urls);
        urlManagerDB.closeConnection();
    }

    @Override
    public String getOneUrl() {
        String url = urlQueue.poll();
        return url;
    }

    public void parserOver(String url){
        UrlManagerDB urlManagerDB = new UrlManagerDB();
        urlManagerDB.OpenConnection();
        urlManagerDB.updateUrlFlag(url, PARSE_OVER_FLAG);
        urlManagerDB.closeConnection();
    }

    public void haveSomeException(String url){
        UrlManagerDB urlManager = new UrlManagerDB();
        urlManager.OpenConnection();
        urlManager.updateUrlFlag(url, EXCEPTION_FLAG);
        urlManager.closeConnection();
    }

    @Override
    public boolean isEmpty() {
        if (urlQueue.size() > 0)
            return false;
        else
            return true;
    }
}
