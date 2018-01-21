package ru.elazarev.model;

/**
 * Request category table model.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 20.12.17
 */
public class RequestCategory {
    /**
     * Category id.
     */
    private int id;
    /**
     * Category name.
     */
    private String name;

    /**
     * Default constructor.
     * @param id id.
     * @param name name.
     */
    public RequestCategory(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Getter.
     * @return id.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter.
     * @param id new id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter.
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter.
     * @param name new name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Default toString method.
     * @return string representation of category model.
     */
    @Override
    public String toString() {
        return "RequestCategory{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }
}
