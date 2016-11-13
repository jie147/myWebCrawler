package con.jie.answers.yahoo.crawler;

import con.jie.answers.yahoo.crawler.parser.ParseUrl;
import con.jie.answers.yahoo.crawler.parser.Parser;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * 爬虫入口
 */
public class Main {

    public static void main(String[] args) {
        //init
        Init init = new Init();
        init.init();

        UrlManager urlManager = UrlManager.getInstance();

        //循环
        while (!urlManager.IsEmpty()) {
            Document document = null;
            // 1、获取url
            String url = urlManager.GetOneUrl();
            // 2、下载
            Downloader downloader = new Downloader();
            try {
                document = downloader.downLoader(url);
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    urlManager.ErrParse(url);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                continue;
            }
            // 3.1、解析内容
            Parser parser = new Parser();
            // 1.是否能为Question page，并且内容能被Parser解析
            if (document != null && parser.IsCanParse(document)) {
                // 2.解析
                parser.parseDocument(document);

                // 3.2、解析useful url
                ParseUrl parseUrl = new ParseUrl();
                List<String> urls = parseUrl.GetUsefulUrls(document);

                // 4、存储
                Saver saver = new Saver();
                // 1. 存储question 和 answers
                saver.saverContent(parser);
                // 2. 存储urls
                try {
                    urlManager.addUrls(urls);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                // 5、完成解析
                try {
                    urlManager.OverParse(url);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    urlManager.ErrParse(url);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                System.err.println("the url can't parse : "+url);
            }

        }

    }
}
