package ru.elazarev.nonblocking;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Model fro non blocking cash.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 26.07.17
 */
public class Model {
    /**
     * Model version.
     */
    private AtomicInteger version;
    /**
     * Name.
     */
    private String name;

    /**
     * Default constructor.
     * @param name name of model
     */
    public Model(String name) {
        this.name = name;
        this.version = new AtomicInteger();
    }

    /**
     * Version getter.
     * @return int
     */
    public int getVersion() {
        return version.get();
    }

    /**
     * Name getter.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Name setter.
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Increments model version.
     */
    public void updateVersion() {
        this.version.getAndIncrement();
    }
}
