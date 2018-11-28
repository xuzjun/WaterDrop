package service;

import datagram.Datagram;
import datagram.DatagramDecoder;
import datagram.DatagramEncoder;
import network.SocketAndBuffer;

import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * @author xuzjun
 */
public class ProcessThread implements Runnable {

    private final LinkedBlockingDeque<SocketAndBuffer> receiveQueue;
    private final LinkedBlockingDeque<SocketAndBuffer> sendQueue;
    private final DatagramDecoder decoder;
    private final DatagramEncoder encoder;

    public ProcessThread(LinkedBlockingDeque<SocketAndBuffer> receiveQueue,
                         LinkedBlockingDeque<SocketAndBuffer> sendQueue,
                         DatagramDecoder decoder,
                         DatagramEncoder encoder) {
        this.receiveQueue = receiveQueue;
        this.sendQueue = sendQueue;
        this.decoder = decoder;
        this.encoder = encoder;
    }

    @Override
    public void run() {
        while (true) {
            SocketAndBuffer receiveSocketAndBuffer = null;
            try {
                receiveSocketAndBuffer = receiveQueue.poll(100, TimeUnit.MILLISECONDS);
                if (receiveSocketAndBuffer != null) {
                    ByteBuffer receiveBuffer = receiveSocketAndBuffer.getBuffer();
                    Datagram receiveData = decoder.decode(receiveBuffer);

                    ServiceHandler handler = ServiceFactory.getServiceHandler(receiveData);
                    handler.process();
                    LinkedList<Datagram> sendDataList = handler.getSendData();
                    Iterator<Datagram> it = sendDataList.iterator();
                    while (it.hasNext()) {
                        ByteBuffer sendBuffer = encoder.encode(it.next());
                        sendBuffer.flip();
                        sendQueue.add(new SocketAndBuffer(receiveSocketAndBuffer.getSocketChannel(), sendBuffer));
                    }
                }

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
