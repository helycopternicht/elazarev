package ru.elazarev.sort;

/**
 * User class.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 21.05.17
 */
public class User implements Comparable<User> {
    /**
     * Name of user.
     */
    private String name;

    /**
     * Users age.
     */
    private int age;

    /**
     * Default constructor.
     * @param name - name of user
     * @param age - age of user
     */
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Younger users first.
     * @param o - other user
     * @return - integer
     */
    @Override
    public int compareTo(User o) {
        return this.getAge() - o.getAge();
    }

    /**
     * If name and age is equal then users are equal.
     * @param o - other user
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

        if (getAge() != user.getAge()) {
            return false;
        }
        return getName().equals(user.getName());
    }

    /**
     * Hash lined on age.
     * @return integer
     */
    @Override
    public int hashCode() {
        return getAge();
    }

    /**
     * Default toString method.
     * @return string representation of User
     */
    @Override
    public String toString() {
        return "User{"
                + "name='"
                + name
                + '\''
                + ", age="
                + age
                + '}';
    }

    /**
     * Getter for name field.
     * @return - name field
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name field.
     * @param name - new name of User
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for age field.
     * @return - age field
     */
    public int getAge() {
        return age;
    }

    /**
     * Setter for age field.
     * @param age - new age value
     */
    public void setAge(int age) {
        this.age = age;
    }
}
