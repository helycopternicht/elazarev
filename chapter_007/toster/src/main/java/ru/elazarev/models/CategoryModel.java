package ru.elazarev.models;

/**
 * Model for question categorys.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 24.01.18
 */
public class CategoryModel {

    private int id;

    private String name;

    public CategoryModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryModel(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
