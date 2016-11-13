package test.parser;

import com.jie.bean.ThingMes;
import com.jie.db.SaveProdMes;
import com.jie.parser.ParserProductMess;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

/**
 * Created by jie on 16-7-24.
 */
public class TestProdPage {
    public static void main(String[] args) throws IOException, SQLException {
        String url = "https://www.amazon.cn/Oldenburger%E6%AC%A7%E5%BE%B7%E5%A0%A1%E8%B6%85%E9%AB%98%E6%B8%A9%E5%A4%84%E7%90%86%E9%83%A8%E5%88%86%E8%84%B1%E8%84%82%E7%89%9B%E5%A5%B61L-12-%E7%AE%B1/dp/B006R1SEI6/ref=lp_43247071_1_15?s=grocery&ie=UTF8&qid=1469352893&sr=1-15";
        Document document = Jsoup.connect(url).userAgent("Mozilla").timeout(3000).get();

//        System.out.println(document.toString());
        System.out.println("------------------------------");

        ParserProductMess parserProductMess = new ParserProductMess();
        ThingMes thing = parserProductMess.parseThingMes(url, document);
        SaveProdMes saveProdMes = new SaveProdMes();
        int i = saveProdMes.insertOneComment(thing);
        System.out.println(i);
        thing.printMes();
    }
}
