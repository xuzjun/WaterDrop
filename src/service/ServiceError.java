package service;

import consts.ErrorsEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * @author len
 */
public class ServiceError {
    private int rspCode;
    private String rspMsg;

    ServiceError() {}

    ServiceError(int rspCode, String rspMsg) {
        this.rspCode = rspCode;
        this.rspMsg = rspMsg;
    }

    ServiceError(ErrorsEnum e) {
        rspCode = e.getCode();
        rspMsg = e.getMsg();
    }

    void setError(ErrorsEnum e) {
        rspCode = e.getCode();
        rspMsg = e.getMsg();
    }

    Map<String, Object> getMap() {
        Map<String, Object> m = new HashMap<>(2);
        m.put("rspCode", rspCode);
        m.put("rspMsg", rspMsg);
        return m;
    }

    int getRspCode() {
        return rspCode;
    }

    void setRspCode(int rspCode) {
        this.rspCode = rspCode;
    }

    String getRspMsg() {
        return rspMsg;
    }

    void setRspMsg(String rspMsg) {
        this.rspMsg = rspMsg;
    }
}
