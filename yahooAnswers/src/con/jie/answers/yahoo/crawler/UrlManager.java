package con.jie.answers.yahoo.crawler;

import con.jie.answers.yahoo.crawler.db.JdbcPool;
import con.jie.answers.yahoo.crawler.db.UrlManagerDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

/**
 * Url管理器，全局仅存在一个url管理器
 */
public class UrlManager {
    static int OVER_PARSE=1;
    static int NOT_PARSE=0;
    static int ERR_PARSE=9;

    private static UrlManager ourInstance = new UrlManager();

    //downloader queue
    private static Queue<String> downloaderQueue = new LinkedList<String>();
    //db manager urls
    private static UrlManagerDB urlManagerDB = new UrlManagerDB();

    //获取url管理器
    public static UrlManager getInstance() {
        return ourInstance;
    }

    private UrlManager() {
    }


    /**
     * add one url
     * @param url url into UrlManager
     */
    public void addOneUrl(String url) throws SQLException {
        if (urlManagerDB.insertOneUrl(url, NOT_PARSE)) {
            downloaderQueue.add(url);
        }
    }


    /**
     * add many urls
     * @param urls urls into UrlManager
     */
    public void addUrls(List<String> urls) throws SQLException {
        List<String> addToQueue = urlManagerDB.insertUrls(urls,NOT_PARSE);
        addToQueue.addAll(addToQueue);
    }

    /**
     * get one url
     * @return the url will download and parse
     */
    public String GetOneUrl(){
        return downloaderQueue.poll();
    }

    /**
     * over parse
     * change the flag value of url to OVER_PARSE in the hashTables.
     * @return
     */
    public void OverParse(String url) throws SQLException {
        urlManagerDB.updateUrlFlag(url, OVER_PARSE);
    }


    public void ErrParse(String url) throws SQLException {
        urlManagerDB.updateUrlFlag(url, ERR_PARSE);
    }


    /**
     * the downloader queue is empty
     * @return if queue is empty return true, not return false;
     */
    public boolean IsEmpty() {
        if (downloaderQueue.size() > 1) {
            return false;
        } else {
            return true;
        }

    }

    public void Init(List<String> initUrls) {
        downloaderQueue.addAll(initUrls);
    }
}
