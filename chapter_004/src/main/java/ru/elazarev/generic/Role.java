package ru.elazarev.generic;

/**
 * Role class.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 29.05.17
 */
public class Role extends Base {
    /**
     * Role id.
     */
    private String id;

    /**
     * Default constructor.
     * @param id - id of role
     */
    public Role(String id) {
        this.id = id;
    }

    /**
     * Id getter.
     * @return id value
     */
    @Override
    String getId() {
        return id;
    }

    /**
     * Id setter.
     * @param id - new id.
     */
    @Override
    void setId(String id) {
        this.id = id;
    }

    /**
     * Default equal method based on id.
     * @param o - object to test.
     * @return true or false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Role role = (Role) o;
        return getId() != null ? getId().equals(role.getId()) : role.getId() == null;
    }

    /**
     * HasCode based on id.
     * @return
     */
    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
