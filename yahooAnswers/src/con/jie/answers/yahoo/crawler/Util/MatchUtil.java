package con.jie.answers.yahoo.crawler.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jie on 16-7-19.
 */
public class MatchUtil {

    /**
     * 选择第一个group
     * @param rule
     * @param url
     * @return
     */
    public static String MatchGroup_1(String rule,String url){
        Pattern r = Pattern.compile(rule);
            Matcher m = r.matcher(url);
        if (m.find()) {
            return m.group(1);
        } else {
            return null;
        }
    }


    /**
     * 能否被匹配上
     * @param rule
     * @param con
     * @return
     */
    public static boolean IsMatch(String rule, String con) {
        Pattern pattern = Pattern.compile(rule);
        Matcher m = pattern.matcher(con);
        return m.find();
    }
}
