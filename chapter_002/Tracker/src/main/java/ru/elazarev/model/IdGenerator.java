package ru.elazarev.model;

/**
 * Class to autoincrement id's for Item class.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 13.04.17
 */
public class IdGenerator {

    /**
     * Field to store next ID.
     */
    private int nextId = 1;

    /**
     * Return generated id and increment.
     * @return - current id
     */
    public int generate() {
        return this.nextId++;
    }
}
