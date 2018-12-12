package datagram;

import com.alibaba.fastjson.JSON;
import consts.CommConst;

import java.nio.ByteBuffer;
import java.util.Map;

/**
 * @author xuzjun
 */
public class DatagramEncoder {

    public ByteBuffer encode(Map rsp) {
        String rspJSONString = JSON.toJSONString(rsp);
        ByteBuffer buffer = ByteBuffer.allocate(CommConst.INT_SIZE + rspJSONString.length());
        buffer.putInt(rspJSONString.length());
        buffer.put(rspJSONString.getBytes());
        return buffer;
    }
}
