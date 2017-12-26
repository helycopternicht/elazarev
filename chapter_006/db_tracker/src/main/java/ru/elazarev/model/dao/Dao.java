package ru.elazarev.model.dao;

import ru.elazarev.model.exceptions.CantCreateElementException;
import ru.elazarev.model.exceptions.NoSuchElementException;

import java.util.List;

/**
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 21.12.17
 */
public interface Dao<T> {

    T create(T e);

    T getById(int id) throws NoSuchElementException;

    void update(T e) throws NoSuchElementException;

    boolean delete(T e) throws NoSuchElementException;

    List<T> getAll();

}
