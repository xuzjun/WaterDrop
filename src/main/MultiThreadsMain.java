package main;

import datagram.DatagramDecoder;
import datagram.DatagramEncoder;
import network.Server;
import network.TransferStation;

public class MultiThreadsMain {

    public static void main(String[] args) {
        int port = Integer.valueOf(args[1]);
        new Server(port, TransferStation.receiveQueue, TransferStation.sendQueue, new DatagramDecoder(), new DatagramEncoder()).run();
    }
}
