package network;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author len
 */
public class SocketAndBuffer {
    private SocketChannel sc;
    private ByteBuffer buffer;

    public SocketAndBuffer(SocketChannel sc, ByteBuffer buffer) {
        this.sc = sc;
        this.buffer = buffer;
    }
    public SocketAndBuffer() {}

    public SocketChannel getSocketChannel() {
        return sc;
    }

    public ByteBuffer getBuffer() {
        return buffer;
    }
}
