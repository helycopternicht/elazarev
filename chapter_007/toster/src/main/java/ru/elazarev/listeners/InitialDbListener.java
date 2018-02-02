package ru.elazarev.listeners;

import ru.elazarev.database.EntityManagerF;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 30.01.18
 */
public class InitialDbListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        EntityManagerFactory factory = EntityManagerF.getFactory();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        EntityManagerF.getFactory().close();
    }
}
