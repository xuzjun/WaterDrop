package service;

import datagram.Datagram;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author xuzjun
 */
public class ProcessThread implements Runnable {

    private final ConcurrentLinkedQueue<Datagram> receiveQueue;
    private final ConcurrentLinkedQueue<Datagram> sendQueue;

    public ProcessThread(ConcurrentLinkedQueue<Datagram> receiveQueue,
                         ConcurrentLinkedQueue<Datagram> sendQueue) {
        this.receiveQueue = receiveQueue;
        this.sendQueue = sendQueue;
    }

    @Override
    public void run() {
        Datagram receiveData = receiveQueue.poll();
        ServiceHandler handler = ServiceFactory.getServiceHandler(receiveData);
        handler.process();
        LinkedList<Datagram> sendDataList = handler.getSendData();
        Iterator<Datagram> it = sendDataList.iterator();
        while (it.hasNext()) {
            sendQueue.add(it.next());
        }
    }
}
