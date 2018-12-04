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

    static ByteBuffer makeTraderLoginReqDatagram(String traderID, String seatID) {
        TraderLoginDatagram data = new TraderLoginDatagram();
        data.setCmd(Cmds.TRADER_LOGIN_REQ);
        data.setSeatID(seatID);
        data.setTraderID(traderID);

        String dataJson = JSON.toJSONString(data);
        ByteBuffer buffer = ByteBuffer.allocate(CommConst.INT_SIZE + dataJson.length());
        buffer.putInt(dataJson.length());
        buffer.put(dataJson.getBytes());

        return buffer;
    }
}
