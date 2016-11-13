package test.db;

import con.jie.answers.yahoo.crawler.bean.Answer;
import con.jie.answers.yahoo.crawler.db.AnswersDB;

/**
 * Created by jie on 16-8-13.
 */
public class TestAnswerDB {
    public static void main(String[] args) {
        Answer answer = new Answer();
        answer.setQuestionId("123546");
        answer.setContent("test content");
        answer.setDate("1995-1-2");
        answer.setUserName("jie");
        answer.setThumbsUp(1);
        answer.setThumbsDown(21);
        answer.setBest(false);

        AnswersDB answersDB = new AnswersDB();
        int a = answersDB.InsertOneAnswer(answer);
        System.out.println(a);
    }
}
