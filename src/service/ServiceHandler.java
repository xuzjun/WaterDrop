package service;

import datagram.Datagram;

import java.util.LinkedList;

/**
 * @author xuzjun
 */
public interface ServiceHandler {
    void process();

    LinkedList<Datagram> getSendData();
}
