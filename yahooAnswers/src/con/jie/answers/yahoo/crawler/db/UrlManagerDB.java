package con.jie.answers.yahoo.crawler.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jie on 16-7-19.
 */
public class UrlManagerDB {

    /**
     * 向数据库中插入一条数据
     * @param conn 数据库连接
     * @param url 数据项URL
     * @param flag 数据标志位
     * @return 返回插入的位置
     */
    public int insertUrl(Connection conn, String url, int flag){

        String sql = "insert into urlmanager(url,urlflag) values (?,?)";
        int i = 0;
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, url);
            pstmt.setInt(2, flag);
            i = pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * 向urlmanager中插入一个url
     * @param url
     * @throws SQLException
     */
    public boolean insertOneUrl(String url,int urlFlag) throws SQLException {
        boolean flag = false;
        JdbcPool poll = new JdbcPool();
        Connection conn = null;
        try {
            conn = poll.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
        if (findWithUrl(conn,url) == 504){
            insertUrl(conn,url,urlFlag);
            flag = true;
        }
        conn.close();
        return flag;
    }

    /**
     * 向urlmanager中插入urls
     * @param urls
     * @throws SQLException
     */
    public List<String> insertUrls(List<String> urls,int urlFlag) throws SQLException {
        List<String> addToQueue=new ArrayList<String>();
        JdbcPool poll = new JdbcPool();
        Connection conn = null;
        try {
            conn = poll.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
        for (String url:urls
             ) {
            if (findWithUrl(conn,url) == 504){
                insertUrl(conn,url,urlFlag);
                addToQueue.add(url);
            }
        }
        conn.close();
        return addToQueue;
    }

    /**
     * 查找urlManager中是否存在此url
     * @param url
     * @return
     */
    public int findWithUrl(Connection conn,String url) throws SQLException {
        String sql = "select * from urlmanager where url = ? ";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int flag=-1;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, url);
            rs = pstmt.executeQuery();
            if (rs.next())
                flag = rs.getInt("urlflag");
            else
                flag=504;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            rs.close();
            pstmt.close();
        }
        return flag;
    }

    public int findWithUrl(String url) throws SQLException {
        JdbcPool poll = new JdbcPool();
        Connection conn = poll.getConnection();
        int flag = findWithUrl(conn, url);
        conn.close();
        return flag;
    }

    /**
     *  通过flag查找符合条件的url
     * @param flag
     * @return
     * @throws SQLException
     */
    public List<String> findUrlWithFlag(int flag) throws SQLException {
        List<String> urls = new ArrayList<>();
        JdbcPool poll = new JdbcPool();
        Connection conn = null;
        try {
            conn = poll.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
        String sql = "select url from urlmanager where urlflag=?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setInt(1,flag);
            rs = pstmt.executeQuery();
            while(rs.next())
                urls.add(rs.getString("url"));
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            rs.close();
            pstmt.close();
            conn.close();
        }
        return urls;
    }

    /**
     * 通过url更新urlflag标志位
     * @param url
     * @param flag
     * @return
     * @throws SQLException
     */
    public int updateUrlFlag(String url,int flag) throws SQLException {
        JdbcPool pool = new JdbcPool();
        Connection conn = pool.getConnection();
        String sql = "update urlmanager set urlflag="+flag+" where url=\""+url+"\"";
        int i = 0;
        PreparedStatement pstmt = null;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            i = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            pstmt.close();
            conn.close();
        }
        return i;
    }

}
