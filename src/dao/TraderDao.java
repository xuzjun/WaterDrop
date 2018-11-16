package dao;

import dbconfigutil.SqlStruct;
import entities.Trader;
import dbhandle.JDBCHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author len
 */
public class TraderDao {

    public static Trader getTraderByID(String traderID, JDBCHandler dbHandler, ConcurrentHashMap<String, SqlStruct> sqlStructMap) {
        Trader trader = null;

        SqlStruct ss = sqlStructMap.get("GET_TRADER");
        if (ss == null) {
            System.err.println("GET_TRADER from map error");
            return null;
        }

        try {
            Connection conn = dbHandler.getConn();
            PreparedStatement ptmt = conn.prepareStatement(ss.getSql());
            ptmt.setString(1, traderID);

            ResultSet rs = ptmt.executeQuery();

            // get the very first line
            if (rs.next()) {
                trader = new Trader(rs.getString("f_trader_id"),
                        rs.getString("f_seat_id"),
                        rs.getInt("f_state"),
                        rs.getString("f_password").toCharArray(),
                        rs.getString("f_ch_password").toCharArray());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return trader;
    }
}
