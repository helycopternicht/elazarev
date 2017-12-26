package ru.elazarev.model;

/**
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 20.12.17
 */
public class RequestCategory {

    private int id;

    private String name;

    public RequestCategory(int id, String name) {
        this.id = id;
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

    @Override
    public String toString() {
        return "RequestCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
