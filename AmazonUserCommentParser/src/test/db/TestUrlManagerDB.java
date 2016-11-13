package test.db;

import com.jie.db.UrlManagerDB;

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
        System.out.println(urlManagerDB.findUrlWithFlag(0).toString());
        System.out.println("update result :"+urlManagerDB.updateUrlFlag("www.jie.com", 0));

    }
}
