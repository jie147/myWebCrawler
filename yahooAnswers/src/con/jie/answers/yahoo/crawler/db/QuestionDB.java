package con.jie.answers.yahoo.crawler.db;

import con.jie.answers.yahoo.crawler.bean.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by jie on 16-8-13.
 */
public class QuestionDB {
    public int InsertOneQuestion(Question question){
        JdbcPool pool = new JdbcPool();
        Connection conn = null;
        try {
            conn = pool.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
        String sql = "insert into questions(questionId,title,description,category,follower,answers) values (?,?,?,?,?,?)";
        int i = 0;
        PreparedStatement pstmt = null;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, question.getQuestionId());
            pstmt.setString(2, question.getTitle());
            pstmt.setString(3, question.getDescription());
            pstmt.setString(4, question.getCategory());
            pstmt.setInt(5, question.getFollower());
            pstmt.setInt(6, question.getAnswers());
            i = pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }
}
