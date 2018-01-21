package ru.elazarev.model.database;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class for creating data base if it not exists.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 21.12.17
 */
public class CreateDataBase {
    /**
     * Create db and tables.
     */
    public static void createDbIfNotExist() {

        Connection conn = null;
        Statement st = null;

        try {
            conn = DriverManager.getConnection(DBProperty.DB_CONNECTOR, DBProperty.USER, DBProperty.PWD);

            st = conn.createStatement();
            st.executeUpdate("CREATE DATABASE " + DBProperty.DB_NAME);

            conn.close();

            conn = DriverManager.getConnection(DBProperty.DB_CONNECTOR + DBProperty.DB_NAME, DBProperty.USER, DBProperty.PWD);
            st = conn.createStatement();

            st.addBatch("CREATE TABLE users (\n"
                    + "\tid serial PRIMARY KEY,\n"
                    + "    login varchar(255) NOT NULL,\n"
                    + "    password varchar(255) NOT NULL,\n"
                    + "    is_admin boolean NOT NULL,\n"
                    + "    create_date timestamp DEFAULT now()\n"
                    + ");");

            st.addBatch("CREATE TABLE request_categorys (\n"
                    + "\tid serial PRIMARY KEY,\n"
                    + "    name varchar(255)\n"
                    + ");");

            st.addBatch("CREATE TABLE requests (\n"
                    + "\tid serial PRIMARY KEY,\n"
                    + "    title varchar(2000) NOT NULL,\n"
                    + "    description text,\n"
                    + "    date timestamp NOT NULL,\n"
                    + "    author_id integer REFERENCES users (id),\n"
                    + "    category_id integer REFERENCES request_categorys (id),\n"
                    + "    status varchar(50)\n"
                    + ");");

            st.addBatch("CREATE TABLE comments  (\n"
                    + "\tid serial PRIMARY KEY,\n"
                    + "    author_id integer REFERENCES users (id),\n"
                    + "    comment text,\n"
                    + "    request_id integer REFERENCES requests (id) ON DELETE CASCADE,\n"
                    + "    create_date timestamp DEFAULT now()\n"
                    + ");");

            st.addBatch("CREATE TABLE files (\n"
                    + "\tid serial PRIMARY KEY,\n"
                    + "    url varchar(1000)\n"
                    + ");");

            st.addBatch("CREATE TABLE requests_files (\n"
                    + "\trequest_id integer REFERENCES requests (id) ON DELETE CASCADE,\n"
                    + "    file_id integer REFERENCES files (id)\n"
                    + ");");

            st.executeBatch();

        } catch (SQLException e) {
            if (e.getErrorCode() != 0) {
                e.printStackTrace();
                System.exit(1);
            }
        } finally {
            JDBCUtils.closeAllQuietly(st, conn);
        }

    }

}
