package service;

import java.util.LinkedList;
import java.util.Map;

/**
 * @author xuzjun
 */
public interface ServiceHandler {


    /**
     * 请求域检查
     * @return 检查成功或者失败
     */
    boolean fieldCheck();

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
