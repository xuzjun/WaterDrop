package entities;

/**
 * @author len
 */

public enum DestroyFlag {
    NORMAL(1),
    LOGOUT(2),
    PAUSE(3);

    private int destroyFlag;
    DestroyFlag(int i) {
        destroyFlag = i;
    }

    int getDestroyFlag() {
        return destroyFlag;
    }
}
