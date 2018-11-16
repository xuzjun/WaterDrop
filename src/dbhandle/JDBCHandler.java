package dbhandle;

import java.sql.*;

/**
 * @author len
 */
public class JDBCHandler {

    private final String sys;
    private Connection conn;
    private Statement stmt;
    DBConfigProperties dbConfigProperties;

    public JDBCHandler(String sys, DBConfigProperties dbConfigProperties) {
        this.sys = sys;
        this.dbConfigProperties = dbConfigProperties;
    }

    public void create() throws ClassNotFoundException, SQLException {
        Class.forName(dbConfigProperties.getDriver());

        System.out.println("connecting to database ......");
        conn = DriverManager.getConnection(dbConfigProperties.getUrl(this.sys),
                dbConfigProperties.getUserName(this.sys),
                dbConfigProperties.getPassword(this.sys));

        System.out.println("Creating statement ......");
        stmt = conn.createStatement();
    }

    public void close() {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public String getSys() {
        return sys;
    }

    public Connection getConn() {
        return conn;
    }

    public Statement getStmt() {
        return stmt;
    }

}
