package datagram;

import java.nio.ByteBuffer;

/**
 * @author xuzjun
 */
public class DatagramEncoder {

    public ByteBuffer encode(Datagram data) {
        ByteBuffer buffer = ByteBuffer.allocate(7);
        buffer.putInt(3);
        buffer.put(new String("POI").getBytes(), 0, 3);
        return buffer;
    }
}
