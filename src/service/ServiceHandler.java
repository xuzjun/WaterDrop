package service;

import java.util.LinkedList;
import java.util.Map;

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
    LinkedList<Map> getSendData();
}
