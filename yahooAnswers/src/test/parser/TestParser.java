package test.parser;

import con.jie.answers.yahoo.crawler.parser.Parser;
import con.jie.answers.yahoo.crawler.bean.Answer;
import con.jie.answers.yahoo.crawler.bean.Question;
import org.jsoup.nodes.Document;
import test.TestDownFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by jie on 16-8-12.
 */
public class TestParser {
    public static void main(String[] args) throws IOException {
        Document document = TestDownFile.GetDocument();
//        System.out.println(document);
        Parser parser = new Parser();
        parser.parseDocument(document);
        Question question = parser.getQuestion();
        question.PrintMes();
        List<Answer> answer = parser.getAnswers();
        for (Answer ans:answer
             ) {
            ans.PrintMes();
        }
    }
}
