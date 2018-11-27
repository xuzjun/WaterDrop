package network;

import java.util.concurrent.ConcurrentLinkedQueue;

public class TransferStation {

    public static ConcurrentLinkedQueue receiveQueue = new ConcurrentLinkedQueue();
    public static ConcurrentLinkedQueue sendQueue = new ConcurrentLinkedQueue();
}
