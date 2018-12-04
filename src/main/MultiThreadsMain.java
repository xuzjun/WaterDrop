package main;

import datagram.DatagramDecoder;
import datagram.DatagramEncoder;
import network.Server;
import network.TransferStation;
import service.ProcessThread;

/**
 * @author len
 */
public class MultiThreadsMain {

    public static void main(String[] args) {
        int port = Integer.valueOf(args[0]);
        System.out.println("port: " + port);

        new Thread(new Server(port, TransferStation.receiveQueue, TransferStation.sendQueue)).start();

        new Thread(new ProcessThread(TransferStation.receiveQueue, TransferStation.sendQueue, new DatagramDecoder(), new DatagramEncoder())).start();
    }
}
