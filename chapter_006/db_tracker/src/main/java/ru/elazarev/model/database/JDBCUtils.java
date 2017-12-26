package ru.elazarev.model.database;

/**
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 21.12.17
 */
public class JDBCUtils {

    public static void closeQuietly(AutoCloseable i) {
        try {
            i.close();
        } catch (Exception e) {}
    }

    public static void closeAllQuietly(AutoCloseable... list) {
        for (AutoCloseable i : list) {
            closeQuietly(i);
        }
    }
}
