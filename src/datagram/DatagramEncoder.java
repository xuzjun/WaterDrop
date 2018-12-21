package datagram;

import com.alibaba.fastjson.JSON;
import consts.CommConst;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Map;

/**
 * @author xuzjun
 */
public class DatagramEncoder {

    public ByteBuffer encode(Map rsp) {
        String rspJSONString = JSON.toJSONString(rsp);
        try {
            byte[] rspBytes = rspJSONString.getBytes("gbk");
            ByteBuffer buffer = null;
            buffer = ByteBuffer.allocate(CommConst.INT_SIZE + rspBytes.length);
            buffer.putInt(rspBytes.length);
            buffer.put(rspBytes);
            return buffer;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
