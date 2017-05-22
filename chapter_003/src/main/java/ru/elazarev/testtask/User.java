package ru.elazarev.testtask;

/**
 * User of OnlineBank class.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 22.05.17
 */
public class User {
    /**
     * User name.
     */
    private String name;
    /**
     * User passport number.
     */
    private String passport;

    /**
     * Default constructor.
     * @param name - users name
     * @param passport - users passport
     */
    public User(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }

    /**
     * Default toString method.
     * @return - users string representation.
     */
    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", passport='" + passport + '\''
                + '}';
    }

    /**
     * If name and passport are equals that users are equals.
     * @param o - user to test
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

        if (getName() != null ? !getName().equals(user.getName()) : user.getName() != null) {
            return false;
        }
        return getPassport() != null ? getPassport().equals(user.getPassport()) : user.getPassport() == null;
    }

    /**
     * HashCode on name and passport field.
     * @return int
     */
    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getPassport() != null ? getPassport().hashCode() : 0);
        return result;
    }

    /**
     * Getter for name field.
     * @return name field
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name field.
     * @param name - new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for passport field.
     * @return passport value
     */
    public String getPassport() {
        return passport;
    }

    /**
     * Setter for passport field.
     * @param passport - new passport value
     */
    public void setPassport(String passport) {
        this.passport = passport;
    }
}
