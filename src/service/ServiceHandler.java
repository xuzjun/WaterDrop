package service;

import java.util.LinkedList;
import java.util.Map;

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
    LinkedList<Map> getSendData();
}
