package ru.elazarev.models;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.GenerationType;

/**
 * Question category java bean.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 24.01.18
 */
@Entity
@Table(name = "categorys")
public class Category {
    /**
     * Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private int id;
    /**
     * Category name.
     */
    @Column(name = "name")
    private String name;

    /**
     * Default constructor.
     */
    public Category() {
    }

    /**
     * Parametrized constructor.
     * @param name category name.
     */
    public Category(String name) {
        this.name = name;
    }

    /**
     * Getter for id field.
     * @return id.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for id field.
     * @param id new id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for name field.
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name field.
     * @param name new name.
     */
    public void setName(String name) {
        this.name = name;
    }
}
