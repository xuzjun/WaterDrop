package service;

import datagram.Datagram;

import java.util.LinkedList;

/**
 * @author xuzjun
 */
public interface ServiceHandler {
    /**
     * ����ҵ��
     */
    void process();

    /**
     * ����Ӧ�����б�
     * @return Ӧ�����б�
     */
    LinkedList<Datagram> getSendData();
}
