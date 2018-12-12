package service;

import datagram.Datagram;
import datagram.TraderLoginDatagram;

import java.util.LinkedList;
import java.util.Map;

/**
 * @author xuzjun
 */
public class TraderLoginHandler implements ServiceHandler {

    private Datagram datagram;
    private LinkedList<Map> sendList;
    private Map data;

    public TraderLoginHandler(Datagram data) {
        this.sendList = new LinkedList<>();
        this.datagram = data;
    }

    public TraderLoginHandler(Map data) {
        this.data = data;
    }

    @Override
    public void process() {
        sendList.add(null);
        System.out.println("Trader login handler be called.");
    }

    @Override
    public LinkedList<Map> getSendData() {
        return sendList;
    }
}
