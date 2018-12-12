package service;

/**
 * @author len
 */
public class ServiceError {
    private int rspCode;
    private String rspMsg;

    ServiceError(int rspCode, String rspMsg) {
        this.rspCode = rspCode;
        this.rspMsg = rspMsg;
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
