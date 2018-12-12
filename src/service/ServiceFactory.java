package service;

import consts.Cmds;
import consts.ErrorsEnum;
import exceptions.DatagramException;

import java.util.Map;

/**
 * @author xuzjun
 */
public class ServiceFactory {

    static ServiceHandler getServiceHandler(Map data) throws DatagramException {
        Object o = data.get("cmd");
        Integer i = o == null ? null : o instanceof Integer ? (Integer) o : null;
        if (i == null) {
            throw new DatagramException("Cmd not found or is not a Integer.");
        }

        switch (i) {
            case Cmds.TRADER_LOGIN_REQ:
                return new TraderLoginHandler(data);
            default:
                return new GeneralErrorHandler(new ServiceError(ErrorsEnum.CMD_ERROR.getCode(), ErrorsEnum.CMD_ERROR.getMsg()));
        }
    }
}
