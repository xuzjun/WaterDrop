package service;

import datagram.Datagram;

import java.util.LinkedList;

/**
 * @author xuzjun
 */
public interface ServiceHandler {
    /**
     * 处理业务
     */
    void process();

    /**
     * 返回应答报文列表
     * @return 应答报文列表
     */
    LinkedList<Datagram> getSendData();
}
