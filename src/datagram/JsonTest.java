package datagram;

import com.alibaba.fastjson.JSON;

public class JsonTest {

    public static void main(String[] args) {
        TraderLoginDatagram d = new TraderLoginDatagram();

        String jsonString = JSON.toJSONString(d);

        System.out.println(jsonString);

        String traderLoginDatagramJSON = new String("{\"traderID\" : \"Yio3718\", \"seatID\": \"000121\"}");
        TraderLoginDatagram tld = JSON.parseObject(traderLoginDatagramJSON, TraderLoginDatagram.class);

        System.out.println(tld.toString());
    }
}
