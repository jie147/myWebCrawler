package com.jie.db;

import com.jie.bean.ThingMes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by jie on 16-7-24.
 */
public class SaveProdMes {
    private static final String SAVE_PRODDETAIL_TABLE = "thingMes";
    public int insertOneComment(ThingMes thing) throws SQLException {

        JdbcPool pool = new JdbcPool();
        Connection conn= pool.getConnection();
        String sql = "insert into " + SAVE_PRODDETAIL_TABLE+ " (thingId,thingName,classType,money,sumStars,details,keywords) values (?,?,?,?,?,?,?)";
        int i = 0;
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1,thing.getThingId());
            pstmt.setString(2, thing.getThingName());
            pstmt.setString(3, thing.getClassType());
            pstmt.setString(4, thing.getMoney());
            pstmt.setDouble(5, thing.getSumStars());
            pstmt.setString(6, thing.getProdDetails());
            pstmt.setString(7,thing.getKeywords());
//            pstmt.setString(8,thing.getContent());
            i = pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            conn.close();
        }
        return i;
    }
}
