package ru.elazarev.listeners;

import ru.elazarev.database.ConnectionFactory;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Listener close Entity manager factory when context destroy.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 30.01.18
 */
public class InitialDbListener implements ServletContextListener {
    /**
     * Empty method.
     * @param servletContextEvent servlet context.
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
    }

    /**
     * Close entity manegr factory.
     * @param servletContextEvent servlet context.
     */
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ConnectionFactory.getFactory().close();
    }
}
