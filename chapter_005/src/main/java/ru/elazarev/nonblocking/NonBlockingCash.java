package ru.elazarev.nonblocking;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

/**
 * Nonblocking cash.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 26.07.17
 */
public class NonBlockingCash {
    /**
     * Map to storage models.
     */
    private Map<Integer, Model> storage;

    /**
     * Default constructor.
     */
    public NonBlockingCash() {
        storage = new ConcurrentHashMap<>();
    }

    /**
     * Adds model to cash.
     * @param key key for storage
     * @param m model for storage
     */
    public void add(int key, Model m) {
        storage.putIfAbsent(key, m);
    }

    /**
     * Removes model from cash by key.
     * @param key key to find model
     */
    public void remove(int key) {
        storage.remove(key);
    }

    /**
     * Updates model with key.
     * @param key key to find model
     * @param m new model
     */
    public void update(int key, Model m) {
        int version = m.getVersion();
        storage.computeIfPresent(key, new BiFunction<Integer, Model, Model>() {
            @Override
            public Model apply(Integer integer, Model model) {
                if (m.getVersion() != model.getVersion()) {
                    throw new OptimisticException("Incompatible version");
                } else {
                    storage.replace(key, m);
                    m.updateVersion();
                }
                return null;
            }
        });
    }

}
