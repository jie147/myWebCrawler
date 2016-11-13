package test.parser;

import con.jie.answers.yahoo.crawler.parser.ParseUrl;
import org.jsoup.nodes.Document;
import test.TestDownFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by jie on 16-8-13.
 */
public class TestUsefulUrl {
    public static void main(String[] args) throws IOException {
        Document document = TestDownFile.GetDocument();
        ParseUrl parseUrl = new ParseUrl();
        List<String> urls = parseUrl.GetUsefulUrls(document);
        for (String url :
                urls) {
            System.out.println(url);
        }
    }
}
