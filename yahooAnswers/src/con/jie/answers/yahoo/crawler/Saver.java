package con.jie.answers.yahoo.crawler;

import con.jie.answers.yahoo.crawler.bean.Answer;
import con.jie.answers.yahoo.crawler.bean.Question;
import con.jie.answers.yahoo.crawler.db.AnswersDB;
import con.jie.answers.yahoo.crawler.db.QuestionDB;
import con.jie.answers.yahoo.crawler.parser.Parser;

import java.util.List;

/**
 * Created by jie on 16-8-13.
 */
public class Saver {
    public void saverContent(Parser parser){
        //get
        Question question = parser.getQuestion();
        List<Answer> answers = parser.getAnswers();
        //save
        if (question != null) {
            QuestionDB questionDB = new QuestionDB();
            questionDB.InsertOneQuestion(question);
        }
        if (answers != null) {
            AnswersDB answersDB =new AnswersDB();
            answersDB.InstertAnswers(answers);
        }

    }
}
