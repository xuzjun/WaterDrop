package datagram;

import consts.Cmds;

/**
 * @author len
 */
public class DatagramFactory {

    public static Class getDatagramClass(int cmd) {
        switch (cmd) {
            case Cmds
                    .TRADER_LOGIN_REQ:
                return TraderLoginDatagram.class;
            default:
                return null;
        }
    }
}
