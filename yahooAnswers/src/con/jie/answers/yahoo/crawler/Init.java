package con.jie.answers.yahoo.crawler;

import con.jie.answers.yahoo.crawler.db.UrlManagerDB;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jie on 16-8-13.
 */
public class Init {
    public void init(){

        UrlManager urlManager = UrlManager.getInstance();
        //从数据库中初始化获取未爬取urls
        UrlManagerDB urlManagerDB = new UrlManagerDB();
        List<String> urls = new ArrayList<String>();
        try {
            urls = urlManagerDB.findUrlWithFlag(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //当urls == null时，初始化为首页
        if (urls != null) {
            urlManager.Init(urls);
        } else {
            try {
                urlManager.addOneUrl("https://answers.yahoo.com/");
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("init err!!");
                System.exit(1);
            }
        }

    }
}
