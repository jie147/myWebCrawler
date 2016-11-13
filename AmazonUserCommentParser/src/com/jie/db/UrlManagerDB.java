package com.jie.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class UrlManagerDB {
    Connection conn;


    public void OpenConnection(){
        JdbcPool pool = new JdbcPool();
        try {
           conn = pool.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 鍚戞暟鎹簱涓彃鍏ヤ竴鏉℃暟鎹�
     * @param url 鏁版嵁椤筓RL
     * @param flag 鏁版嵁鏍囧織浣�
     * @return 杩斿洖鎻掑叆鐨勪綅缃�
     */
    private int insertUrl( String url, int flag){

        String sql = "insert into urlmanager(url,urlflag) values (?,?);";
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
     * 鍚憉rlmanager涓彃鍏ヤ竴涓猽rl
     * @param url
     * @throws SQLException
     */
    public void insertOneUrl(String url,int urlFlag) {
        if (findWithUrl(url) == 504){
            insertUrl(url,urlFlag);
        }
        insertUrl(url,urlFlag);
    }

    /**
     * 鍚憉rlmanager涓彃鍏rls
     * @param urls
     * @throws SQLException
     */
    public void insertUrls(List<String> urls,int urlFlag) {
        for (String url:urls
             ) {
            if (findWithUrl(url) == 504){
                insertUrl(url,urlFlag);
            }
        }
    }

    /**
     * 鏌ユ壘urlManager涓槸鍚﹀瓨鍦ㄦurl
     * @param url
     * @return
     */
    public int findWithUrl(String url) {
        String sql = "select * from urlmanager where url = ? ;";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int flag=-1;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, url);
            rs = pstmt.executeQuery();
            if (rs.next())
                return rs.getInt("urlflag");
            else
                return 504;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                rs.close();
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    /**
     *  閫氳繃flag鏌ユ壘绗﹀悎鏉′欢鐨剈rl
     * @param flag
     * @return
     * @throws SQLException
     */
    public List<String> findUrlWithFlag(int flag) {
        List<String> urls = new ArrayList<>();
        String sql = "select url from urlmanager where urlflag=?;";
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
            try {
                rs.close();
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return urls;
    }

    /**
     * 閫氳繃url鏇存柊urlflag鏍囧織浣�
     * @param url
     * @param flag
     * @return
     * @throws SQLException
     */
    public int updateUrlFlag(String url,int flag) {
        String sql = "update urlmanager set urlflag="+flag+" where url=\""+url+"\";";
        int i = 0;
        PreparedStatement pstmt = null;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            i = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    public void closeConnection(){
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("database close fail!!");
            e.printStackTrace();
        }
    }
}
