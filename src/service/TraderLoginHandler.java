package service;

import consts.ErrorsEnum;
import datagram.Datagram;

import java.lang.reflect.Array;
import java.rmi.ServerError;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xuzjun
 */
public class TraderLoginHandler implements ServiceHandler {

    private Datagram datagram;
    private LinkedList<Map> sendList;
    private Map<String, Object> req;
    private static ServiceError error;

    private String traderID;
    private String seatID;
    private byte[] password;

    static {
        error = new ServiceError();
    }

    public TraderLoginHandler(Datagram data) {
        this.sendList = new LinkedList<Map>();
        this.datagram = data;
    }

    public TraderLoginHandler(Map<String, Object> data) {
        this.sendList = new LinkedList<Map>();
        this.req = data;
    }

    @Override
    public boolean fieldCheck() {


        return true;
    }

    @Override
    public void process() {
        if (!fieldCheck()) { return; }
        if (!passwordCheck()) { return; }

        ConcurrentHashMap<String, Object> rsp = new ConcurrentHashMap<>(16);

        // TODO:

        error.setError(ErrorsEnum.PROC_SUCCESS);

        rsp.putAll(req);
        rsp.putAll(error.getMap());

        sendList.add(rsp);
        System.out.println("Trader login handler be called.");
    }

    private boolean passwordCheck() {

        error.setError(ErrorsEnum.PASSWORD_ERROR);
        return true;
    }

    @Override
    public LinkedList<Map> getSendData() {
        return sendList;
    }
}
