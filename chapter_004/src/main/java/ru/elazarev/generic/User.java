package ru.elazarev.generic;

/**
 * User class.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 29.05.17
 */
public class User extends Base {
    /**
     * Default constructor.
     * @param id - user id.
     */
    public User(String id) {
        this.setId(id);
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

        User user = (User) o;
        return getId() != null ? getId().equals(user.getId()) : user.getId() == null;
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
