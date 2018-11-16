package entities;

public class Seat {
    private String seatId;
    private int traderNumber;
    private int destroyFlag;

    public Seat(String seatId, int traderNumber, int destroyFlag) {
        this.seatId = seatId;
        this.traderNumber = traderNumber;
        this.destroyFlag = destroyFlag;
    }

    public Seat() {}

    public String getSeatId() {
        return seatId;
    }

    public void setSeatId(String seatId) {
        this.seatId = seatId;
    }

    public int getTraderNumber() {
        return traderNumber;
    }

    public void setTraderNumber(int traderNumber) {
        this.traderNumber = traderNumber;
    }

    public int getDestroyFlag() {
        return destroyFlag;
    }

    public void setDestroyFlag(int destroyFlag) {
        this.destroyFlag = destroyFlag;
    }
}
