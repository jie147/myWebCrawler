package test.urlManager;

import com.jie.urlmanager.UrlManagerAmazon;
import com.jie.urlmanager.imp.UrlManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jie on 16-7-23.
 */
public class TestUrlManager {
    public static void main(String[] args) throws SQLException {
        UrlManager urlManager = UrlManagerAmazon.GetUrlManager();
        urlManager.addOneUrl("www.jietest.com");
        List<String> list = new ArrayList<>();
        list.add("www.baidu.com");
        list.add("www.bing.com");
        list.add("www.jie.com");
        urlManager.addUrls(list);
        String url = urlManager.getOneUrl();

    }
}
