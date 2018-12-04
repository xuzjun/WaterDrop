package datagram;

import com.alibaba.fastjson.JSON;

import java.util.Map;

public class JsonTest {

    public static void main(String[] args) {
        TraderLoginDatagram d = new TraderLoginDatagram();

        String jsonString = JSON.toJSONString(d);

        System.out.println(jsonString);

        String traderLoginDatagramJSON = new String("{\"traderID\" : \"Yio3718\", \"seatID\": \"000121\", \"cmd\": 98}");
        TraderLoginDatagram tld = JSON.parseObject(traderLoginDatagramJSON, TraderLoginDatagram.class);

        Map m = JSON.parseObject(traderLoginDatagramJSON);

        System.out.println(m.get("traderID"));
        System.out.println(m.get("cmd"));
        Object o = m.get("cmd");
        Integer i = o instanceof Integer ? ((Integer) o) : null;
        if (i == null) {
            System.out.println("cmd is not a integer");
        } else {
            System.out.println("cmd is a integer");
        }
        Integer i2 = Integer.valueOf(o);
        System.out.println("i2 |" + i2 +"|");
        Class clazz = TraderLoginDatagram.class;
        System.out.println(tld.toString());
    }
}
