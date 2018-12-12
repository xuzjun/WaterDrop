package datagram;

import com.alibaba.fastjson.JSON;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Map;

/**
 * @author xuzjun
 */
public class DatagramDecoder {

    public Map decode(ByteBuffer buffer) {
        Charset charset = Charset.forName("gbk");
        CharsetDecoder decoder = charset.newDecoder();
        CharBuffer charBuffer = null;
        try {
            charBuffer = decoder.decode(buffer.asReadOnlyBuffer());
        } catch (CharacterCodingException e) {
            e.printStackTrace();
        }
        assert charBuffer != null;
        String s = charBuffer.toString();
        System.out.println(s);
        return JSON.parseObject(s);
//        Object o = m.get("cmd");
//        Integer i = o == null ? null : o instanceof Integer ? (Integer) o : null;
//        if (i == null) {
//            return null;
//        }
//        Class<Datagram> clazz = DatagramFactory.getDatagramClass(i);
//        return JSON.parseObject(s, clazz);
    }
}
