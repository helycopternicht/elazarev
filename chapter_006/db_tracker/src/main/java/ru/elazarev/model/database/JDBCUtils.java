package ru.elazarev.model.database;

/**
 * Utils for manage data base objects.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 21.12.17
 */
public class JDBCUtils {
    /**
     * Close single entity.
     * @param i entity to close.
     */
    public static void closeQuietly(AutoCloseable i) {
        try {
            i.close();
        } catch (Exception e) { /*NOP*/ }
    }

    /**
     * Close list of entities.
     * @param list list of entities to close.
     */
    public static void closeAllQuietly(AutoCloseable... list) {
        for (AutoCloseable i : list) {
            closeQuietly(i);
        }
    }
}
