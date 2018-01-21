package ru.elazarev.model.dao;

import ru.elazarev.model.exceptions.NoSuchElementException;

import java.util.List;

/**
 * Base interface for dao classes.
 * @param <T> type of dao class.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 21.12.17
 */
public interface Dao<T> {
    /**
     * For create entity in db.
     * @param e model of object to create.
     * @return updated entity with real data.
     */
    T create(T e);

    /**
     * Returns entity from db by id.
     * @param id if of entity.
     * @return entity model.
     * @throws NoSuchElementException if no such entity in bd.
     */
    T getById(int id) throws NoSuchElementException;

    /**
     * Updates entity.
     * @param e entity model.
     * @throws NoSuchElementException if no such entity in bd.
     */
    void update(T e) throws NoSuchElementException;

    /**
     * Deletes entity from db.
     * @param e entity model.
     * @return true if success.
     * @throws NoSuchElementException if no such entity in db.
     */
    boolean delete(T e) throws NoSuchElementException;

    /**
     * Returns list of all entities in table.
     * @return list of models.
     */
    List<T> getAll();

}
