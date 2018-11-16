package main;

import dao.TraderDao;
import dbconfigutil.ParseSqlConfig;
import dbconfigutil.SqlStruct;
import entities.Trader;
import dbhandle.DBConfigProperties;
import dbhandle.JDBCHandler;
import exceptions.TagAttributeNotFoundException;
import org.dom4j.DocumentException;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author len
 */
public class WaterDropMain {

    private static final String DB_CONFIG_FILE_NAME = "db_config.properties";
    private static final String QTE_SQL_FILE_NAME = "qte_sql.xml";
    private static final String SYS = "qte";

    public static void main(String[] args) {
        URL url = null;
        ConcurrentHashMap<String, SqlStruct> sqlStructMap = null;
        url = Thread.currentThread().getContextClassLoader().getResource(QTE_SQL_FILE_NAME);
        if (url == null) {
            System.err.println(String.format("get resource file {} error", DB_CONFIG_FILE_NAME));
            return;
        }
        try {
            sqlStructMap = ParseSqlConfig.parseConfig(url);
            if (sqlStructMap == null) {
                System.err.println(String.format("get {%s} sql struct map error.", SYS));
                return;
            }
        } catch (DocumentException | TagAttributeNotFoundException e) {
            e.printStackTrace();
        }

        url = Thread.currentThread().getContextClassLoader().getResource(DB_CONFIG_FILE_NAME);
        if (url == null) {
            System.err.println(String.format("get resource file {%s} error", DB_CONFIG_FILE_NAME));
            return;
        }
        DBConfigProperties dbConfigProperties = new DBConfigProperties(Thread.currentThread().getContextClassLoader().getResource(DB_CONFIG_FILE_NAME));
        try {
            dbConfigProperties.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JDBCHandler dbHandler = new JDBCHandler(SYS, dbConfigProperties);
        try {
            dbHandler.create();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            dbHandler.close();
        }

        Trader t = TraderDao.getTraderByID("bly4004   ", dbHandler, sqlStructMap);
        System.out.println(t);

        dbHandler.close();
    }
}
