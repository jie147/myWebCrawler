package con.jie.answers.yahoo.crawler.parser;

import con.jie.answers.yahoo.crawler.Util.MatchUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jie on 16-8-12.
 */
public class ParseUrl {
    public List<String> GetAllUrl(Document document){
        Elements links = document.select("a[href]");

        List<String> urls = new ArrayList<>();
        for (Element link:links
                ) {
            String linkStr = link.attr("href");
            urls.add(linkStr);
        }
        return urls;
    }

    /**
     * 筛选有用的url
     * @param document
     * @return
     */
    public List<String> GetUsefulUrls(Document document){
        Elements links = document.select("a[href]");

        //base url
        String baseUrl = "https://answers.yahoo.com/question/index?qid=";
        //用于匹配的规则
        String rule = ".*qid=\\w{21}.*";
        //用于提取的规则
        String ruleGroup = ".*qid=(\\w{21}).*";
        List<String> urls = new ArrayList<>();
        for (Element link:links
                ) {
            String linkStr = link.attr("href");
            if (MatchUtil.IsMatch(rule,linkStr)) {
                urls.add(baseUrl + MatchUtil.MatchGroup_1(ruleGroup, linkStr));
            }
        }
        return urls;
    }

}
