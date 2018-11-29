package service;

import datagram.Datagram;

/**
 * @author xuzjun
 */
public class ServiceFactory {

    static ServiceHandler getServiceHandler(Datagram data) {

        int cmd = data.getCmd();
        return new TraderLoginHandler(data);
    }
}
