package con.jie.answers.yahoo.crawler.db;

import con.jie.answers.yahoo.crawler.bean.Answer;
import con.jie.answers.yahoo.crawler.bean.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by jie on 16-8-13.
 */
public class AnswersDB {
    public int InsertOneAnswer(Answer answer){
        JdbcPool pool = new JdbcPool();
        Connection conn = null;
        try {
            conn = pool.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
        String sql = "insert into answers(questionId,content,userName,answerdate,thumbsUp,thumbsDown,isBest,commentNum) values (?,?,?,?,?,?,?,?)";
        int i = 0;
        PreparedStatement pstmt = null;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, answer.getQuestionId());
            pstmt.setString(2, answer.getContent());
            pstmt.setString(3, answer.getUserName());
            pstmt.setString(4, answer.getDate());
            pstmt.setInt(5, answer.getThumbsUp());
            pstmt.setInt(6, answer.getThumbsDown());
            pstmt.setBoolean(7, answer.isBest());
            pstmt.setInt(8, answer.getCommentNum());
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

    public void InstertAnswers(List<Answer> answers){
        if (answers == null) {
            return;
        }
        for (Answer answer:answers
             ) {
            InsertOneAnswer(answer);
        }
    }
}
