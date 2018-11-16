package entities;

/**
 * @author len
 */
public class TraderPermission {
    private String traderID;
    private int traderRight;
    private int destroyFlag;

    public TraderPermission(String traderID, int traderRight, int destroyFlag) {
        this.traderID = traderID;
        this.traderRight = traderRight;
        this.destroyFlag = destroyFlag;
    }

    public TraderPermission() {}

    public String getTraderID() {
        return traderID;
    }

    public void setTraderID(String traderID) {
        this.traderID = traderID;
    }

    public int getTraderRight() {
        return traderRight;
    }

    public void setTraderRight(int traderRight) {
        this.traderRight = traderRight;
    }

    public int getDestroyFlag() {
        return destroyFlag;
    }

    public void setDestroyFlag(int destroyFlag) {
        this.destroyFlag = destroyFlag;
    }
}
