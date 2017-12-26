package ru.elazarev.model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 21.12.17
 */
public class ConnectionManager {

    private static Connection conn;

    public static Connection getConnection() {

        if (conn == null) {

            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.exit(1);
            }

            try {
                conn = DriverManager.getConnection(DBProperty.DB_CONNECTOR + DBProperty.DB_NAME, DBProperty.USER, DBProperty.PWD);
            } catch (SQLException e) {
                e.printStackTrace();
                System.exit(1);
            }
            return conn;

        } else {
            boolean closed = false;
            try {
                closed = conn.isClosed();
            } catch (SQLException e) {
                e.printStackTrace();
                System.exit(1);
            }

            if (closed) {
                conn = null;
            } else {
                return conn;
            }

            return getConnection();
        }
    }
}
