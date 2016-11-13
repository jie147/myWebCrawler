package com.jie.urlmanager.imp;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by jie on 16-7-19.
 */
public interface UrlManager {

    /**
     * 添加一个url到urlManager中
     * @param url
     */
    public void addOneUrl(String url) throws SQLException;

    /**
     * 添加一组url到UrlManager中
     * @param urls
     */
    public void addUrls(List<String> urls) throws SQLException;

    /**
     * 从urlManager中获取一个url
     * @return url
     */
    public String getOneUrl();

    /**
     * 返回urlManager中的下载队列是否为空
     * @return queue_is_empty
     */
    public boolean isEmpty();
}
