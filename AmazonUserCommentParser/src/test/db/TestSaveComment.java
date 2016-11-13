package test.db;

import com.jie.db.ContentSaver;
import test.parser.TestAmazonOnePageParser;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by jie on 16-7-23.
 */
public class TestSaveComment {
    /**
     * 测试ok
     * @param args
     * @throws IOException
     * @throws SQLException
     */
    public static void main(String[] args) throws IOException, SQLException {
        ContentSaver contentSaver = new ContentSaver();
        contentSaver.insertComments(TestAmazonOnePageParser.GetAmazonUserComments());
    }
}
