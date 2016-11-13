package com.jie.db;


import com.jie.bean.AmazonUserComment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by jie on 16-7-20.
 */
public class ContentSaver {
    static String SAVE_COMMENTS_TABLE = "userComment";


    public int insertOneComment(AmazonUserComment auc,Connection conn) throws SQLException {
//        String sqlAUC = "insert into "+SAVE_COMMENTS_TABLE+"(userID,content,time,helpMess,thingsID,thingsName,stars,contentSummary) values ();";
//        statement.executeUpdate(sqlAUC);

        String sql = "insert into "+SAVE_COMMENTS_TABLE+" (userId,userName,content,time,helpPersons,thingId,thingName,stars,contentSummary) values (?,?,?,?,?,?,?,?,?)";
        int i = 0;
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, auc.getUserId());
            pstmt.setString(2, auc.getUserName());
            pstmt.setString(3, auc.getContent());
            pstmt.setString(4, auc.getTime());
            pstmt.setInt(5, auc.getHelpPersons());
            pstmt.setString(6, auc.getThingId());
            pstmt.setString(7, auc.getThingName());
            pstmt.setFloat(8, auc.getStars());
            pstmt.setString(9, auc.getContentSummary());
            i = pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public void insertComments(List<AmazonUserComment> aucs) throws SQLException {
        JdbcPool pool = new JdbcPool();
        Connection conn= pool.getConnection();

        for (AmazonUserComment auc:aucs
             ) {
            try {
                insertOneComment(auc,conn);
            } catch (SQLException e) {
                e.printStackTrace();
                continue;
            }
        }

        conn.close();
    }



}
