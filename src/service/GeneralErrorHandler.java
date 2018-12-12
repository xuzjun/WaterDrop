package service;

import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author len
 */
public class GeneralErrorHandler implements ServiceHandler {

    private ServiceError error;
    private LinkedList<Map> sendList;

    public GeneralErrorHandler(ServiceError error) {
        sendList = new LinkedList<Map>();
        this.error = error;
    }

    @Override
    public void process() {
        Map<String, Object> m = new ConcurrentHashMap<String, Object>(2);
        m.put("rspCode", error.getRspCode());
        m.put("rspMsg", error.getRspMsg());
        sendList.add(m);
    }

    @Override
    public LinkedList<Map> getSendData() {
        return sendList;
    }
}
