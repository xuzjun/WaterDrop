package datagram;

/**
 * @author xuzjun
 */
public class TraderLoginDatagram extends Datagram {

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
    public String toString() {
        return new String("Trader id: " + traderID + ", Seat id: " + seatID);
    }

}
