package con.jie.answers.yahoo.crawler.Util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by jie on 16-8-12.
 */
public class DateUtil {
    /**
     * 当前系统时间，减去 @param days
     * @param days 需要减去的天数
     * @return 当前时间减去 @param days 天后的时间
     */
    public static String DateMutil(int days){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, days);
        return (new SimpleDateFormat("yyyy-MM-dd")).format(cal.getTime());
    }

}
