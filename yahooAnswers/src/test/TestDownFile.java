package test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;

/**
 * Created by jie on 16-8-13.
 */
public class TestDownFile {
    public static Document GetDocument() throws IOException {
        File input = new File("resource/test3.html");
        Document document = Jsoup.parse(input, "UTF-8", "http://example.com/");
        return document;
    }
}
