package ru.elazarev.database;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 30.01.18
 */
public class EntityManagerF {
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("primary");

    public static EntityManagerFactory getFactory() {
        return factory;
    }

    private EntityManagerF() {
    }
}
