package network;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author len
 */
public class TransferStation {

    public static LinkedBlockingDeque<SocketAndBuffer> receiveQueue;
    public static LinkedBlockingDeque<SocketAndBuffer> sendQueue;

    static {
        receiveQueue = new LinkedBlockingDeque<SocketAndBuffer>();
        sendQueue = new LinkedBlockingDeque<SocketAndBuffer>();
    }
}
