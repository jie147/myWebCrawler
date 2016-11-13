package test.db;


import con.jie.answers.yahoo.crawler.db.UrlManagerDB;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jie on 16-7-23.
 */
public class TestUrlManagerDB {
    /**
     * 测试ok
     * @param args
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException {
        UrlManagerDB urlManagerDB = new UrlManagerDB();
//        urlManagerDB.insertOneUrl("www.gogle.com",2);
        List<String> list = new ArrayList<>();
        list.add("www.baidu.com");
        list.add("www.bing.com");
        list.add("www.jie.com");
//        urlManagerDB.insertUrls(list,1);
        System.out.println("update result :"+urlManagerDB.updateUrlFlag("www.jie.com", 0));
        System.out.println(urlManagerDB.findUrlWithFlag(0).toString());

    }
}
