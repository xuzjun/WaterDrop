package client;

import com.alibaba.fastjson.JSON;
import consts.Cmds;
import consts.CommConst;
import datagram.TraderLoginDatagram;

import java.nio.ByteBuffer;

/**
 * @author xuzjun
 */
public class DatagramMaker {

    static ByteBuffer makeTraderLoginReqDatagram() {
        TraderLoginDatagram data = new TraderLoginDatagram();
        data.setCmd(Cmds.TRADER_LOGIN_REQ);
        data.setSeatID("000121");
        data.setTraderID("byd0123");

        String dataJson = JSON.toJSONString(data);
        ByteBuffer buffer = ByteBuffer.allocate(CommConst.INT_SIZE + dataJson.length());
        buffer.putInt(dataJson.length());
        buffer.put(dataJson.getBytes());

        return buffer;
    }
}
