package datagram;

/**
 * @author xuzjun
 */
public class TraderLoginDatagram implements Datagram {

    private String traderID;
    private String seatID;

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

    @Override
    public int getCmd() {
        return 0;
    }
}
