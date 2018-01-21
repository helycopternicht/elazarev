package ru.elazarev.model.database;

/**
 * Class to hold app properties.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 21.12.17
 */
public class DBProperty {

    // i know what i should do this with property file
    /**
     * SQL Driver.
     */
    public static final String DB_CONNECTOR = "jdbc:postgresql://localhost:5432/";
    /**
     * Database name.
     */
    public static final String DB_NAME = "simple_tracker";
    /**
     * Database user name.
     */
    public static final String USER = "postgres";
    /**
     * Database user password.
     */
    public static final String PWD = "123";
}
