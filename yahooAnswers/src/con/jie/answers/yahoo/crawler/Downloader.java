package con.jie.answers.yahoo.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by jie on 16-8-12.
 */
public class Downloader {
    //downloader content text
    public Document downLoader(String url) throws IOException {
        Document doc = Jsoup.connect(url)
                .userAgent("Mozilla")
                .get();
        return doc;
    }
}
