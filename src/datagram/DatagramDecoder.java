package datagram;

import com.alibaba.fastjson.JSON;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * @author xuzjun
 */
public class DatagramDecoder {

    public Datagram decode(ByteBuffer buffer) {
        // buffer.flip();
        Charset charset = Charset.forName("gbk");
        CharsetDecoder decoder = charset.newDecoder();
        CharBuffer charBuffer = null;
        try {
            charBuffer = decoder.decode(buffer.asReadOnlyBuffer());
        } catch (CharacterCodingException e) {
            e.printStackTrace();
        }
        String s = charBuffer.toString();
        System.out.println(s);
        Datagram datagramObject = JSON.parseObject(s, TraderLoginDatagram.class);
        return datagramObject;
    }
}
