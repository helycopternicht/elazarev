package ru.elazarev.database;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Factory class for entity manager.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 30.01.18
 */
public class ConnectionFactory {
    /**
     * Factory store.
     */
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("primary");

    /**
     * Returns entity manager factory.
     * @return factory.
     */
    public static EntityManagerFactory getFactory() {
        return factory;
    }

    /**
     * Private constructor.
     */
    private ConnectionFactory() {
    }
}
