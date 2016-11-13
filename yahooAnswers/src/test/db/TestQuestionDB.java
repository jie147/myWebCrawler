package test.db;

import con.jie.answers.yahoo.crawler.bean.Question;
import con.jie.answers.yahoo.crawler.db.QuestionDB;

/**
 * Created by jie on 16-8-13.
 */
public class TestQuestionDB {
    public static void main(String[] args) {
        Question question = new Question();
        question.setQuestionId("123456");
        question.setTitle("test tile");
        question.setDescription("description");
        question.setCategory("category");
        question.setAnswers(1);
        question.setFollower(12);

        QuestionDB questionDB = new QuestionDB();
        int a = questionDB.InsertOneQuestion(question);
        System.out.println(a);
    }
}
