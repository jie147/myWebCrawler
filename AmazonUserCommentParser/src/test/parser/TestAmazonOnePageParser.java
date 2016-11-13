package test.parser;

import com.jie.parser.AmazonParserUrls;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.jie.bean.AmazonUserComment;
import com.jie.parser.ParseUserCommentsPage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by jie on 16-7-23.
 */
public class TestAmazonOnePageParser {
    public static void main(String[] args) throws IOException {
//        DownLoader downLoader = new DownLoaderImp();
//        String content = downLoader.downLoad("https://www.amazon.cn/product-reviews/B019PZ7SR8/");
//        Document doc = Jsoup.parse(content);

//        List<String> urls = GetPageUrls();
//        System.out.println(urls.size());
//        for (String url:urls
//             ) {
//            System.out.println(url);
//        }
        List<AmazonUserComment> list = GetAmazonUserComments();
        for (AmazonUserComment ac:list) {
            ac.printMess();
        }
    }

    public static List<AmazonUserComment> GetAmazonUserComments() throws IOException {
//        File input = new File("/workspace/tmp/amazonHtml/test1-1.html");
//        Document document = Jsoup.parse(input, "UTF-8", "http://www.amazon.com");

    	Document document = Jsoup.connect("https://www.amazon.cn/product-reviews/B0116GWQ58").userAgent("Mozilla").get();
//        System.out.println(document.toString());

        ParseUserCommentsPage aParser = new ParseUserCommentsPage();
        System.out.println("the pages of user comment :"+aParser.getCommentsPageNum(document));
        return aParser.parserUserComment(document);
    }

    public static List<String> GetPageUrls() throws IOException {
    	Document doc = Jsoup.connect("https://www.amazon.cn/product-reviews/B0116GWQ58").userAgent("Mozilla").get();

    	String str = doc.toString();
        AmazonParserUrls au = new AmazonParserUrls();
        List<String> list=  au.GetAllUsefulPageUrl(str);
        System.out.println(list.toString());
        return list;
    }



}
