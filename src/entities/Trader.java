package entities;

/**
 * @author len
 */
public class Trader {

    private String traderID;
    private String seatID;
    private Seat seat;
    private char[] password;
    private int destroyFlag;
    private char[] chPassword;

    public Trader(String traderID, Seat seat, int destroyFlag, char[] password, char[] chPassword) {
        this.traderID = traderID;
        this.seatID = seat.getSeatId();
        this.seat = seat;
        this.destroyFlag = destroyFlag;
        this.password = new char[password.length];
        System.arraycopy(password, 0, this.password, 0, password.length);
        this.chPassword = new char[password.length];
        System.arraycopy(chPassword, 0, this.chPassword, 0, chPassword.length);
    }

    public Trader(String traderID, String seatID, int destroyFlag, char[] password, char[] chPassword) {
        this.traderID = traderID;
        this.seatID = seatID;
        this.destroyFlag = destroyFlag;
        this.password = new char[password.length];
        System.arraycopy(password, 0, this.password, 0, password.length);
        this.chPassword = new char[password.length];
        System.arraycopy(chPassword, 0, this.chPassword, 0, chPassword.length);
    }

    @Override
    public String toString() {
        return String.format("trader_id: [%s]  seat_id: [%s]  destroy_flag: [%d]", traderID, seatID, destroyFlag);
    }

    public Trader() {}

    public String getTraderID() {
        return traderID;
    }

    public void setTraderID(String traderID) {
        this.traderID = traderID;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = new char[password.length];
        System.arraycopy(password, 0, this.password, 0, password.length);
    }

    public int getDestroyFlag() {
        return destroyFlag;
    }

    public void setDestroyFlag(int destroyFlag) {
        this.destroyFlag = destroyFlag;
    }

    public char[] getChPassword() {
        return chPassword;
    }

    public void setChPassword(char[] chPassword) {
        this.chPassword = new char[chPassword.length];
        System.arraycopy(chPassword, 0, this.chPassword, 0, chPassword.length);
    }

    public String getSeatID() {
        return seatID;
    }

    public void setSeatID(String seatID) {
        this.seatID = seatID;
    }
}
