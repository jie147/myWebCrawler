package test.db;

import com.jie.bean.ThingMes;
import com.jie.db.SaveProdMes;

import java.sql.SQLException;

/**
 * Created by jie on 16-7-24.
 */
public class TestSaveProd {
    public static void main(String[] args) throws SQLException {
        ThingMes thingMes = new ThingMes();
        thingMes.setThingId("132456");
        thingMes.setThingName("123456");
        thingMes.setClassType("test type");
        thingMes.setMoney("46");
        thingMes.setSumStars(2.3);
        thingMes.setProdDetails("test detail");
        SaveProdMes saveProdMes = new SaveProdMes();
        int i = saveProdMes.insertOneComment(thingMes);
        System.out.println(i);
    }
}
