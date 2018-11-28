package service;

import datagram.Datagram;
import datagram.TraderLoginDatagram;

import java.util.LinkedList;

/**
 * @author xuzjun
 */
public class TraderLoginHandler implements ServiceHandler {

    private Datagram datagram;
    private LinkedList<Datagram> sendList;

    public TraderLoginHandler(Datagram data) {
        this.sendList = new LinkedList<>();
        this.datagram = data;
    }

    @Override
    public void process() {
        sendList.add(new TraderLoginDatagram());
        System.out.println("Trader login handler be called.");
    }

    @Override
    public LinkedList<Datagram> getSendData() {
        return sendList;
    }
}
