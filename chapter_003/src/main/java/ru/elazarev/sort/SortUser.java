package ru.elazarev.sort;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Comparator;

/**
 * Class contains some sort methods.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 21.05.17
 */
public class SortUser {
    /**
     * Constructor without parameters.
     */
    public SortUser() {
    }

    /**
     * Method to sort list of Users.
     * @param list - list to sort
     * @return sorted Set of Users
     */
    public Set<User> sort(List<User> list) {
        return new TreeSet<>(list);
    }

    /**
     * Method sort list by elements hashes.
     * @param list - list to sort
     * @return sorted by hash list
     */
    public List<User> sortHash(List<User> list) {

        list.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.hashCode() - o2.hashCode();
            }
        });

        return list;
    }

    /**
     * Method sorts list by users name length.
     * Smallest names first.
     * @param list - list to sort
     * @return - sorted by names length list
     */
    public List<User> sortLength(List<User> list) {

        list.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getName().length() - o2.getName().length();
            }
        });

        return list;
    }
}
