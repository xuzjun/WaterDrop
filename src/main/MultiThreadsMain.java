package main;

import datagram.DatagramDecoder;
import datagram.DatagramEncoder;
import network.Server;
import network.TransferStation;
import service.ProcessThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author len
 */
public class MultiThreadsMain {

    public static void main(String[] args) {
        int port = Integer.valueOf(args[0]);
        System.out.println("port: " + port);

        ExecutorService threadPool = Executors.newCachedThreadPool();

        threadPool.execute(new Thread(new Server(port, TransferStation.receiveQueue, TransferStation.sendQueue)));
        threadPool.execute(new Thread(new ProcessThread(TransferStation.receiveQueue, TransferStation.sendQueue,
                new DatagramDecoder(),
                new DatagramEncoder())));
    }
}
