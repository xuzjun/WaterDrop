package datagram;

/**
 * @author xuzjun
 */
public class TraderLoginDatagram implements Datagram {

    private int cmd;
    private String traderID;
    private String seatID;
    private int rspCode;
    private String rspMsg;

    public String getTraderID() {
        return traderID;
    }

    public void setTraderID(String traderID) {
        this.traderID = traderID;
    }

    public String getSeatID() {
        return seatID;
    }

    public void setSeatID(String seatID) {
        this.seatID = seatID;
    }

    public void setCmd(int cmd) {
        this.cmd = cmd;
    }

    public int getRspCode() {
        return rspCode;
    }

    public void setRspCode(int rspCode) {
        this.rspCode = rspCode;
    }

    public String getRspMsg() {
        return rspMsg;
    }

    public void setRspMsg(String rspMsg) {
        this.rspMsg = rspMsg;
    }


    @Override
    public String toSendString() {
        return null;
    }

    @Override
    public int getCmd() {
        return 0;
    }
}
